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
package org.openmrs.module.dss.ruleLibrary;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.chirdlutilbackports.hibernateBeans.ObsAttribute;
import org.openmrs.module.chirdlutilbackports.hibernateBeans.ObsAttributeValue;
import org.openmrs.module.chirdlutilbackports.service.ChirdlUtilBackportsService;


/**
 *
 * @author Steve McKee
 */
public class storeObsWithAttributes implements Rule {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, java.lang.Integer, java.util.Map)
	 */
	public Result eval(LogicContext logicContext, Integer patientId, Map<String, Object> parameters) throws LogicException {
		if (parameters == null) {
			return Result.emptyResult();
		}
		
		String conceptName = (String)parameters.get("param1");
		if (conceptName == null) {
			log.error("No concept name provided.");
			return Result.emptyResult();
		}
		
		Concept concept = Context.getConceptService().getConceptByName(conceptName);
		if (concept == null) {
			log.error("No concept found for name: " + conceptName);
			return Result.emptyResult();
		}
		
		Integer encounterId = (Integer) parameters.get("encounterId");
		String value = (String)parameters.get("param2");
		Obs obs = org.openmrs.module.chirdlutil.util.Util.saveObs(Context.getPatientService().getPatient(patientId), 
			concept, encounterId, value, new Date());
		if (obs == null) {
			return Result.emptyResult();
		}
		
		Integer obsId = obs.getObsId();
		
		// Retrieve and save the observation attributes
		// Observation attributes start at the third parameter index.  The first two are the concept name and value for the 
		// observation.
		ChirdlUtilBackportsService service = Context.getService(ChirdlUtilBackportsService.class);
		String paramName = "param";
		int paramCount = 3;
		while (true) {
			String attrKey = paramName + paramCount++;
			String attrValue = (String)parameters.get(attrKey);
			String valueKey = paramName + paramCount++;
			String valueValue = (String)parameters.get(valueKey);
			
			if (attrValue == null || valueValue == null) {
				return Result.emptyResult();
			}
			
			ObsAttribute obsAttr = service.getObsAttributeByName(attrValue);
			if (obsAttr == null) {
				log.error("Observation attribute not found with name " + attrValue);
				continue;
			}
			
			ObsAttributeValue obsAttrVal = new ObsAttributeValue();
			obsAttrVal.setObsAttributeId(obsAttr.getObsAttributeId());
			obsAttrVal.setObsId(obsId);
			obsAttrVal.setValue(valueValue);
			service.saveObsAttributeValue(obsAttrVal);
		}
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDefaultDatatype()
	 */
	public Datatype getDefaultDatatype() {
		return Datatype.CODED;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies() {
		return new String[]{};
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList() {
		return null;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getTTL()
	 */
	public int getTTL() {
		return 0; // 60 * 30; // 30 minutes
	}
	
}
