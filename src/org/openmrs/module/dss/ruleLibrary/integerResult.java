/********************************************************************
 Translated from - mcad.mlm on Fri Dec 28 15:10:34 EST 2007

 Title : MCAD Reminder
 Filename:  mcad
 Version : 0 . 2
 Institution : Indiana University School of Medicine
 Author : Steve Downs
 Specialist : Pediatrics
 Date : 05 - 22 - 2007
 Validation :
 Purpose : Provides a specific reminder, tailored to the patient who identified one or more fatty acid disorders
 Explanation : Based on AAP screening recommendations
 Keywords : fatty, acid, fatty acid disorder
 Citations : Screening for fatty acid disorder AAP
 Links :

 ********************************************************************/
package org.openmrs.module.dss.ruleLibrary;

import java.util.Map;
import java.util.Set;

import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.LogicService;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.chirdlutil.util.Util;

public class integerResult implements Rule
{
	private LogicService logicService = Context.getLogicService();

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList()
	{
		return null;
	}

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[]
		{};
	}

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.Rule#getTTL()
	 */
	public int getTTL()
	{
		return 0; // 60 * 30; // 30 minutes
	}

	/**
	 * *
	 * 
	 * @see org.openmrs.logic.Rule#getDefaultDatatype()
	 */
	public Datatype getDefaultDatatype()
	{
		return Datatype.CODED;
	}

	
	public Result eval(LogicContext context, Integer patientId,
			Map<String, Object> parameters) throws LogicException
	{
		if (parameters != null)
		{
			Result ruleResult = null;
			
			if (parameters.get("param0") != null) {
				ruleResult = (Result) parameters.get("param0");
			} else {
				if (parameters.get("param1") != null) {
					if(parameters.get("param1") instanceof Result){
						ruleResult = (Result) parameters.get("param1");
					}else{
						ruleResult = new Result(Double.valueOf(parameters.get("param1").toString()));
					}
				}
			}
			
			if (ruleResult != null)
			{
				String resultString = ruleResult.toString();
				Integer resultInt = null;

				if (resultString != null)
				{
					try
					{
						Double resultDouble = Double.valueOf(resultString);
						if (resultDouble != null)
						{
							resultInt = Util.round(resultDouble, 0).intValue();
						}
					} catch (NumberFormatException e1)
					{
					}

					if (resultInt != null)
					{
						return new Result(String.valueOf(resultInt));
					}
				}
			}
		}
		return Result.emptyResult();
	}
}