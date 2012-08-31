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
package org.openmrs.module.dss.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.logic.result.Result;
import org.openmrs.module.dss.hibernateBeans.Rule;


/**
 * Interface to expose DSS functionality to other consumers via RMI.
 *
 * @author Steve McKee
 */
public interface DssRmiService extends Remote {

	/**
	 * Returns patient metadata for a specific MRN.
	 * 
	 * @param username User used to meet the required credentials to pull the information.
	 * @param password Password for the username.
	 * @param mrn The patient's MRN used to pull information.
	 * @return Patient object containing the patient metadata or null if a patient cannot be found for the provided mrn.
	 * @throws RemoteException
	 */
	public Patient getPatient(String username, String password, String mrn) throws RemoteException;
	
	/**
	 * Retrieve encounter information for a given patient and between the given start date and end date.
	 * 
	 * @param username User used to meet the required credentials to pull the information.
	 * @param password Password for the username.
	 * @param patient The Patient object used to look up the encounters.
	 * @param fromDate Date to qualify the start of the encounters requested.  This can be null.
	 * @param toDate Date to qualify the end of the encounters requested.  This can be null.
	 * @return List of Encounter objects.
	 * @throws RemoteException
	 */
	public List<Encounter> getEncounters(String username, String password, Patient patient, Date fromDate, Date toDate) 
	throws RemoteException;
	
	/**
	 *  Executes a rule and returns a result.
	 * 
	 * @param username User used to meet the required credentials to pull the information.
	 * @param password Password for the username.
	 * @param patient The Patient object used to execute the rule against.
	 * @param ruleName The name of the rule to execute.
	 * @param encounterId The ID of the encounter to execute the rule against.  This can be null depending on the rule 
	 * requirements.
	 * @param locationName The name of the clinic.  This can be null depending on the rule requirements.
	 * @return Result object containing the result of the rule execution.
	 * @throws RemoteException
	 */
	public Result runRule(String username, String password, Patient patient, String ruleName, Integer encounterId, 
	                      String locationName) throws RemoteException;
	
	/**
	 * Retrieves a list of Rules.
	 * 
	 * @param username User used to meet the required credentials to pull the information.
	 * @param password Password for the username.
	 * @param ruleNameLike A like function will be used to get any potential matches to this string.  This can be null which 
	 * will cause the return of all known rules.
	 * @return List of Rule object matching the provided criteria.
	 * @throws RemoteException
	 */
	public List<Rule> getRules(String username, String password, String ruleNameLike) throws RemoteException;
	
	/**
	 * Retrieves the complete physician note based on the encounter for the current day's visit.
	 * 
	 * @param username User used to meet the required credentials to pull the information.
	 * @param password Password for the username.
	 * @param patient The Patient for the requested note.
	 * @return String containing the physician note.
	 * @throws RemoteException
	 */
	public String getPhysicianNote(String username, String password, Patient patient) throws RemoteException;
	
	/**
	 * Utility method available to consumers to test the connection to this service.
	 * 
	 * @return true if the connection is available.
	 * @throws RemoteException
	 */
	public boolean testConnection() throws RemoteException;
}
