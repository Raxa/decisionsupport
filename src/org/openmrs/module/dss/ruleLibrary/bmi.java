package org.openmrs.module.dss.ruleLibrary;

import java.util.Map;
import java.util.Set;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.impl.LogicCriteriaImpl;
import org.openmrs.logic.LogicCriteria;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.LogicService;
import org.openmrs.logic.Rule;
import org.openmrs.logic.op.Operator;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;

public class bmi implements Rule
{
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

	public Result eval(LogicContext context, Integer patientId,
			Map<String, Object> parameters) throws LogicException
	{
		Result weightResult = null;
		Result heightResult = null;
		String conceptName = "WEIGHT";
		Integer encounterId = (Integer) parameters.get("encounterId");
		
		LogicCriteria conceptCriteria = new LogicCriteriaImpl(
				conceptName);
		
		LogicCriteria fullCriteria = null;
		
		if(encounterId != null)
		{
			LogicCriteria encounterCriteria = new LogicCriteriaImpl("encounterId").equalTo(encounterId.intValue());
			
			fullCriteria = conceptCriteria.and(encounterCriteria);
		}else
		{
			fullCriteria = conceptCriteria;
		}
		weightResult = context.read(patientId, context.getLogicDataSource("obs"),
				fullCriteria.last());
		
		conceptName = "HEIGHT";
		encounterId = (Integer) parameters.get("encounterId");
		
		conceptCriteria = new LogicCriteriaImpl(
				conceptName);
		
		fullCriteria = null;
		
		if(encounterId != null)
		{
			LogicCriteria encounterCriteria = new LogicCriteriaImpl("encounterId").equalTo(encounterId.intValue());
			
			fullCriteria = conceptCriteria.and(encounterCriteria);
		}else
		{
			fullCriteria = conceptCriteria;
		}
		heightResult = context.read(patientId, context.getLogicDataSource("obs"),
				fullCriteria.last());

		if(weightResult != null && heightResult != null)
		{
			Double weightNum = weightResult.toNumber();
			Double heightNum = heightResult.toNumber();
			//check for division by zero
			if(heightNum==null||heightNum==0){
				return Result.emptyResult();
			}
			if(weightNum != null && heightNum != null)
			{
				Double bmi = ( weightNum / 
						(heightNum * heightNum) ) * 703;
				return new Result(bmi);
			}
		}
		
		return Result.emptyResult();
	}
}