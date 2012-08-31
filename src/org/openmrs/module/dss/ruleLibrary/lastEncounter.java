package org.openmrs.module.dss.ruleLibrary;

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
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
public class lastEncounter implements Rule
{
	private Log log = LogFactory.getLog(this.getClass());
	private LogicService logicService = Context.getLogicService();

	public Result eval(LogicContext context, Integer patientId,
			Map<String, Object> parameters) throws LogicException
	{		
		PatientService patientService = Context.getPatientService();
		Patient patient = patientService.getPatient(patientId);
		// observation
		try
		{
			
			Result result = null;
			
			result = context.read(patientId, context.getLogicDataSource("encounter"),
					"encounterDatetime").latest();
			
			if (result != null&&result.size()>0)
			{
				return result.get(0);
			}else if(result != null){
				return result;
			}
		} catch (Exception e)
		{
			this.log.error(e.getMessage());
			this.log.error(Util.getStackTrace(e));
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
