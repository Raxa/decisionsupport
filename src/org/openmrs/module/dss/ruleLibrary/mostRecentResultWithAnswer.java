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

public class mostRecentResultWithAnswer implements Rule
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
    	String answer = (String) parameters.get("param1");
    	Result results = Result.emptyResult();
    	Result finalResult = null;
    	int i = 2;
		Object paramObj = "";

		while(paramObj != null){
			paramObj = parameters.get("param"+i);
			i++;
			if(paramObj instanceof Result){
				results = (Result) paramObj;
			}else{

				continue;
			}
			if(results != null){
				if (results.size() == 0) {
					Result result = results;
					if (answer != null && answer.equalsIgnoreCase(result.toString())) {
						Result matchedResult = result;
						if (finalResult == null || 
								(matchedResult.getResultDate()!=null&&finalResult.getResultDate()==null)||
								(matchedResult.getResultDate()!=null&&
										matchedResult.getResultDate().compareTo(finalResult.getResultDate()) > 0)) {
							finalResult = matchedResult;
						}
					}
				} else {
					for (Result result : results) {
						if (answer != null && answer.equalsIgnoreCase(result.toString())) {
							Result matchedResult = result;
							if (finalResult == null || 
									(matchedResult.getResultDate()!=null&&finalResult.getResultDate()==null)||
									(matchedResult.getResultDate()!=null&&
											matchedResult.getResultDate().compareTo(finalResult.getResultDate()) > 0)) {
								finalResult = matchedResult;
							}
						}
					}
				}
			}
		}
		if(finalResult == null){
			finalResult = Result.emptyResult();
		} 
		return finalResult;
	}
	
}