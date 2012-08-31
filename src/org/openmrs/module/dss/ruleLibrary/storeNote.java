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

import java.util.Map;
import java.util.Set;

import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.dss.service.DssService;


/**
 *
 * @author Steve McKee
 */
public class storeNote implements Rule {
	
	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, java.lang.Integer, java.util.Map)
	 */
	public Result eval(LogicContext logicContext, Integer patientId, Map<String, Object> parameters) throws LogicException {
		if (parameters == null) {
			return Result.emptyResult();
		}
		
		Integer ruleId = (Integer) parameters.get("ruleId");
		String value = (String)parameters.get("param1");
		String heading = (String)parameters.get("param2");
		parameters.put("param1", "CHICA_Note");
		parameters.put("param2", value);
		parameters.put("param3", "primaryHeading");
		parameters.put("param4", heading);
		parameters.put("param5", "ruleId");
		parameters.put("param6", String.valueOf(ruleId));
		
		DssService dssService = Context.getService(DssService.class);
    	org.openmrs.module.dss.hibernateBeans.Rule rule = new org.openmrs.module.dss.hibernateBeans.Rule();
    	rule.setTokenName("storeObsWithAttributes");
		rule.setParameters(parameters);
		
		return dssService.runRule(Context.getPatientService().getPatient(patientId), rule);
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
