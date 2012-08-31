/**
 * 
 */
package org.openmrs.module.dss;

import java.util.HashMap;

import org.openmrs.logic.result.Result;

/**
 * This class contains metadata for the result
 * of an openmrs LogicService rule evaluation.
 * 
 * @author Tammy Dugan
 *
 */
public class DssElement
{
	private Result result = null;
	private Integer ruleId = null;
	private HashMap<String,Object> parameters = null;
	
	/**
	 * Assigns the result of the rule evaluation
	 * and the ruleId of the rule that was evaluated
	 * @param result result of the rule evaluation
	 * @param ruleId id of the rule that was evaluated
	 */
	public DssElement(Result result, int ruleId)
	{
		this.result = result;
		this.ruleId = ruleId;
	}

	/**
	 * @return the result
	 */
	public Result getResult()
	{
		return this.result;
	}

	/**
	 * @return the ruleId
	 */
	public Integer getRuleId()
	{
		return this.ruleId;
	}

	/**
	 * @param parameter 
	 * @return the parameter
	 */
	public Object getParameter(String parameter)
	{
		if(this.parameters != null){
			return this.parameters.get(parameter);
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,Object value)
	{
		if(this.parameters == null){
			this.parameters = new HashMap<String,Object>();
		}
		this.parameters.put(key, value);
	}
}
