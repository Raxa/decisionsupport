/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dss.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.ContextAuthenticationException;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.token.TokenService;
import org.openmrs.module.dss.hibernateBeans.Rule;
import org.openmrs.module.dss.service.DssRmiService;
import org.openmrs.module.dss.service.DssService;


/**
 * Implementation class to expose DSS functionality to other consumers via RMI.
 *
 * @author Steve McKee
 */
public class DssRmiServiceImpl implements DssRmiService {
	
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Constructor method
	 * @throws RemoteException
	 */
	protected DssRmiServiceImpl() throws RemoteException {
	    super();
    }

	/**
	 * @see org.openmrs.module.dss.service.DssRmiService#getPatient(java.lang.String, java.lang.String, java.lang.String)
	 */
    public Patient getPatient(String username, String password, String mrn) throws RemoteException {
    	Context.openSession();
    	try {
    		log.info("getPatient RMI service method accessed.");
    		authenticate(username, password);
		    PatientService patientService = Context.getPatientService();
		    List<PatientIdentifierType> types = new ArrayList<PatientIdentifierType>();
		    types.add(patientService.getPatientIdentifierTypeByName("MRN_OTHER"));
		    List<Patient> patients = patientService.getPatients(null, mrn, types, true);
		    if (patients.size() > 0) {
		    	Patient patient = patients.get(0);
		    	Hibernate.initialize(patient);
		    	return patient;
		    }
		    
		    // Patient not found by MRN.  Try spotting a dash.
		    int dashIndex = mrn.indexOf("-");
		    if (dashIndex < 0) {
		    	// Place a dash in the next-to-last character position and ask again.
		    	int length = mrn.length();
		    	int position = length - 1;
		    	if (position >= 0) {
		    		String firstPart = mrn.substring(0, position);
		    		String lastPart = mrn.substring(position, length);
		    		String newMrn = firstPart + "-" + lastPart;
		    		patients = patientService.getPatients(null, newMrn, types, true);
				    if (patients.size() > 0) {
				    	Patient patient = patients.get(0);
				    	Hibernate.initialize(patient);
				    	return patient;
				    }
		    	}
		    }
		    
		    return null;
    	} catch (ContextAuthenticationException e) {
    		log.error("Unauthorized access attempted on web service method: getPatient", e);
    		throw new RemoteException("Unauthorized access attempted on web service method: getPatient", e);
    	} catch (Throwable e) {
    		log.error("Error retrieving patient", e);
    		throw new RemoteException("Error retrieving patient", e);
    	} finally {
    		Context.closeSession();
    	}
    }

    /**
     * @see org.openmrs.module.dss.service.DssRmiService#testConnection()
     */
    public boolean testConnection() throws RemoteException {
	    return true;
    }

    /**
     * @see org.openmrs.module.dss.service.DssRmiService#getEncounters(java.lang.String, java.lang.String, org.openmrs.Patient, java.util.Date, java.util.Date)
     */
    public List<Encounter> getEncounters(String username, String password, Patient patient, Date fromDate, Date toDate) 
    throws RemoteException {
    	Context.openSession();
    	try {
    		log.info("getEncounters RMI service method accessed.");
    		authenticate(username, password);
    		List<Encounter> encounters = Context.getEncounterService().getEncounters(
    			patient, null, fromDate, toDate, null, null, null, false);
    		for (Encounter encounter : encounters) {
    			initializeEncounter(encounter);
    		}
    		
    		return encounters;
    	} catch (ContextAuthenticationException e) {
    		log.error("Unauthorized access attempted on web service method: getEncounters", e);
    		throw new RemoteException("Unauthorized access attempted on web service method: getEncounters", e);
    	} catch (Throwable e) {
    		log.error("Error retrieving encounters", e);
    		throw new RemoteException("Error retrieving encounters", e);
    	} finally {
    		Context.closeSession();
    	}
    }
    
    /**
     * @see org.openmrs.module.dss.service.DssRmiService#runRule(java.lang.String, java.lang.String, org.openmrs.Patient, java.lang.String, java.lang.Integer, java.lang.String)
     */
    public Result runRule(String username, String password, Patient patient, String ruleName, Integer encounterId, 
                          String locationName) throws RemoteException {
    	Context.openSession();
    	try {
    		log.info("runRule RMI service method accessed.");
    		authenticate(username, password);
    		DssService dssService = Context.getService(DssService.class);
    		Map<String,Object> parameters = new HashMap<String,Object>();
    		parameters.put("mode", "PRODUCE");
    		if (encounterId != null) {
    			parameters.put("encounterId", encounterId);
    		}
    		
    		if (locationName != null) {
    			Integer locationId = findLocationId(locationName);
    			if (locationId != null) {
    				parameters.put("locationId", locationId);
    			}
    		}
    		Rule rule = new Rule();
        	rule.setTokenName(ruleName);
    		rule.setParameters(parameters);
    		return dssService.runRule(patient, rule);
    	} catch (ContextAuthenticationException e) {
    		log.error("Unauthorized access attempted on web service method: runRule", e);
    		throw new RemoteException("Unauthorized access attempted on web service method: runRule", e);
    	} catch (Throwable e) {
    		log.error("Error running rule", e);
    		throw new RemoteException("Error running rule", e);
    	} finally {
    		Context.closeSession();
    	}
    }
    
    /**
     * @see org.openmrs.module.dss.service.DssRmiService#getRules(java.lang.String, java.lang.String, java.lang.String)
     */
    public List<Rule> getRules(String username, String password, String ruleNameLike) throws RemoteException {
    	Context.openSession();
    	List<Rule> rules = new ArrayList<Rule>();
    	try {
    		log.info("getRules RMI service method accessed.");
    		authenticate(username, password);
		    // Get the tokens first;
		    TokenService tokenService = Context.getService(TokenService.class);
		    List<String> tokens = null;
		    if (ruleNameLike == null || ruleNameLike.trim().length() == 0) {
		    	tokens = tokenService.getAllTokens();
		    } else {
		    	tokens = tokenService.getTokens(ruleNameLike);
		    }
		    
		    DssService dssService = Context.getService(DssService.class);
		    // Try to find additional metadata for the token.  MLM rules will have it, Java rules will not.
		    for (String token : tokens) {
		    	Rule rule = new Rule();
		    	rule.setTokenName(token);
		    	List<Rule> foundRule = dssService.getRules(rule, false, false, null);
		    	if (foundRule == null || foundRule.size() == 0) {
		    		Rule newRule = new Rule();
		    		newRule.setTokenName(token);
		    		rules.add(newRule);
		    	} else {
		    		Rule newRule = foundRule.get(0);
		    		rules.add(newRule);
		    	}
		    }
    	} catch (ContextAuthenticationException e) {
    		log.error("Unauthorized access attempted on web service method: getRules", e);
    		throw new RemoteException("Unauthorized access attempted on web service method: getRules", e);
    	} catch (Throwable e) {
    		log.error("Error querying rules", e);
    		throw new RemoteException("Error querying rules", e);
    	} finally {
    		Context.closeSession();
    	}
	    
	    return rules;
    }
    
    /**
     * @see org.openmrs.module.dss.service.DssRmiService#getPhysicianNote(java.lang.String, java.lang.String, org.openmrs.Patient)
     */
    public String getPhysicianNote(String username, String password, Patient patient) throws RemoteException {
    	Context.openSession();
    	try {
    		log.info("getPhysicianNote RMI service method accessed.");
    		authenticate(username, password);
    		DssService dssService = Context.getService(DssService.class);
    		Map<String,Object> parameters = new HashMap<String,Object>();
    		parameters.put("mode", "PRODUCE");
    		Rule rule = new Rule();
        	rule.setTokenName("PhysicianNote");
    		rule.setParameters(parameters);
    		Result result = dssService.runRule(patient, rule);
    		return result.toString();
    	} catch (ContextAuthenticationException e) {
    		log.error("Unauthorized access attempted on web service method: getPhysicianNote", e);
    		throw new RemoteException("Unauthorized access attempted on web service method: getPhysicianNote", e);
    	} catch (Throwable e) {
    		log.error("Error building physician note", e);
    		throw new RemoteException("Error building physician note", e);
    	} finally {
    		Context.closeSession();
    	}
    }
    
    /**
     * Initializes a Concept
     * 
     * @param concept The Concept to initialize.
     */
    private void initializeConcept(Concept concept) {
    	if (concept == null) {
    		return;
    	}
    	
    	Hibernate.initialize(concept.getDatatype());
    	Hibernate.initialize(concept.getNames());
    	Collection<ConceptName> conceptNames = concept.getNames();
		for (ConceptName conceptName : conceptNames) {
			Hibernate.initialize(conceptName.getConceptNameType());
			Hibernate.initialize(conceptName);
		}
		
		Hibernate.initialize(concept);
    }
    
    private void initializeEncounter(Encounter encounter) {
    	if (encounter == null) {
    		return;
    	}
    	
    	Set<Obs> obs = encounter.getAllObs();
		Iterator<Obs> iter = obs.iterator();
		while (iter.hasNext()) {
			Obs ob = iter.next();
			initializeConcept(ob.getConcept());
			Hibernate.initialize(ob.getValueCodedName());
			Hibernate.initialize(ob.getValueDrug());
			initializeConcept(ob.getValueCoded());
			Hibernate.initialize(ob);
		}
		
		Hibernate.initialize(encounter);
    }
    
    /**
     * Finds a location ID based on a location name.
     * 
     * @param locationName The location name used to find the location ID.
     * @return Integer containing the location ID or null if a location is not found matching the provided location name.
     */
    private Integer findLocationId(String locationName) throws IllegalArgumentException {
    	LocationService locService = Context.getLocationService();
    	Location loc = locService.getLocation(locationName);
    	if (loc == null) {
    		return null;
    	}
    	
    	return loc.getLocationId();
    }

    /**
     * Authenticates a user to the system.
     * 
     * @param username Used to login to the system. 
     * @param password Password for the username.
     * @throws ContextAuthenticationException
     */
    private void authenticate(String username, String password) throws ContextAuthenticationException {
    	Context.authenticate(username, password);
    }
}
