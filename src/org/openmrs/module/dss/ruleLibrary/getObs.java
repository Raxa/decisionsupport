package org.openmrs.module.dss.ruleLibrary;

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicCriteria;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.LogicService;
import org.openmrs.logic.Rule;
import org.openmrs.logic.impl.LogicCriteriaImpl;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;

public class getObs implements Rule
{
	private Log log = LogFactory.getLog(this.getClass());
	private LogicService logicService = Context.getLogicService();

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.rule.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList()
	{
		return null;
	}

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.rule.Rule#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[]
		{};
	}

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.rule.Rule#getTTL()
	 */
	public int getTTL()
	{
		return 0; // 60 * 30; // 30 minutes
	}

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.rule.Rule#getDatatype(String)
	 */
	public Datatype getDefaultDatatype()
	{
		return Datatype.CODED;
	}

	/**
	 * Limits results by a given encounter id
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient, java.util.Map)
	 */
	public Result eval(LogicContext context, Integer patientId,
			Map<String, Object> parameters) throws LogicException
	{
		if(parameters == null)
		{
			return Result.emptyResult();
		}
		
		String conceptName = (String) parameters.get("param1");
		
		if(conceptName == null)
		{
			return Result.emptyResult();
		}
		Result ruleResult = null;
		
		Integer encounterId = (Integer) parameters.get("encounterId");
		
		if(encounterId == null){
			return Result.emptyResult();
		}
		
		LogicCriteria conceptCriteria = new LogicCriteriaImpl(
				conceptName);
		
		LogicCriteria fullCriteria = null;

		LogicCriteria encounterCriteria = 
			new LogicCriteriaImpl("encounterId").equalTo(encounterId.intValue());
			
		fullCriteria = conceptCriteria.and(encounterCriteria);

		ruleResult = context.read(patientId,context.getLogicDataSource("obs"), 
				fullCriteria);

		return ruleResult;
	}
}