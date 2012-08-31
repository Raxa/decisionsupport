package org.openmrs.module.dss.ruleLibrary;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.User;
import org.openmrs.api.EncounterService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.LogicService;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.chirdlutil.util.Util;

/**
 * 
 * Calculates a person's age in years based from their date of birth to the
 * index date
 * 
 */
public class providerName implements Rule
{

	private LogicService logicService = Context.getLogicService();

	public Result eval(LogicContext context, Integer patientId,
			Map<String, Object> parameters) throws LogicException
	{
		EncounterService encounterService = Context
				.getService(EncounterService.class);

		Encounter encounter = null;
		Integer encounterId = (Integer) parameters.get("encounterId");

		if (encounterId != null)
		{
			encounter = encounterService.getEncounter(encounterId);
			UserService userService = Context.getUserService();
			List<User> providers = userService.getUsersByPerson(encounter.getProvider(), true);
			User provider = null;
			if(providers != null&& providers.size()>0){
				provider = providers.get(0);
			}
			if(provider != null)
			{
				String providerName = "";
				
				if(provider.getGivenName() != null)
				{
					providerName+=Util.toProperCase(provider.getGivenName())+" ";
				}
				if(provider.getFamilyName() != null)
				{
					providerName+=Util.toProperCase(provider.getFamilyName());
				}
				return new Result(providerName);
			}
		}

		return Result.emptyResult();
	}

	/**
	 * @see org.openmrs.logic.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList()
	{
		return null;
	}

	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies()
	{
		return new String[]
		{};
	}

	/**
	 * @see org.openmrs.logic.Rule#getTTL()
	 */
	public int getTTL()
	{
		return 0; 
	}

	/**
	 * @see org.openmrs.logic.Rule#getDefaultDatatype()
	 */
	public Datatype getDefaultDatatype()
	{
		return Datatype.NUMERIC;
	}
}
