package org.openmrs.module.dss.ruleLibrary;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.chirdlutil.util.ResultDateComparator;

/**
 * Calculates a person's age in years based from their date of birth to the index date
 */
public class getReverseNumericResultElement implements Rule {
	
	
	/**
	 * @see org.openmrs.logic.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList() {
		return null;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies() {
		return new String[] {};
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getTTL()
	 */
	public int getTTL() {
		return 0;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDefaultDatatype()
	 */
	public Datatype getDefaultDatatype() {
		return Datatype.NUMERIC;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, java.lang.Integer, java.util.Map)
	 */
	public Result eval(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Integer index = null;
		List<Result> results = null;
		Result distinctResult = null;
		
		if (parameters != null) {
			Object param1Obj = parameters.get("param1");
			if (param1Obj != null) {
				index = Integer.parseInt((String) param1Obj);
			}
			
			results = (List<Result>) parameters.get("param2");
		}
		
		if (index != null && results != null && index < results.toArray().length) {
			// Sort the results by date
			Collections.sort(results, new ResultDateComparator());
			// Reverse the list
			Collections.reverse(results);
			distinctResult = (Result) results.toArray()[index];
		}
		
		if (distinctResult == null) {
			distinctResult = new Result();
		}
		
		if (distinctResult.toString() == null) {
			distinctResult.setValueText(String.valueOf(distinctResult.toNumber()));
		}
		
		return distinctResult;
	}
	
}
