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
import org.openmrs.module.chirdlutilbackports.hibernateBeans.FormInstance;
import org.openmrs.module.chirdlutilbackports.hibernateBeans.LocationAttributeValue;
import org.openmrs.module.chirdlutilbackports.service.ChirdlUtilBackportsService;

public class LocationAttributeLookup implements Rule
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
		FormInstance formInstance = (FormInstance) parameters
			.get("formInstance");
		ChirdlUtilBackportsService chirdlUtilBackportsService = Context.getService(		ChirdlUtilBackportsService.class);
		String param = (String) parameters.get("param1");
		if (formInstance != null && param!=null)
		{
			Integer locationId = formInstance.getLocationId();
			LocationAttributeValue locationAttributeValue = 
				chirdlUtilBackportsService.getLocationAttributeValue(locationId, param);
		
			if(locationAttributeValue != null){
				return new Result(locationAttributeValue.getValue());
			}
		}
		return Result.emptyResult();
	}
}