package org.openmrs.module.dss.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.result.Result;
import org.openmrs.module.dss.hibernateBeans.Rule;
import org.openmrs.module.dss.service.DssService;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class RunRulesController extends SimpleFormController
{

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception
	{
		return "testing";
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();

		String patientIdString = request.getParameter("patientId");

		if (patientIdString == null)
		{
			patientIdString = "";
		}

		if (!(patientIdString.length() == 0))
		{
			int patientId = Integer.parseInt(patientIdString);
			DssService dssService = Context
					.getService(DssService.class);
			List<Rule> rules = dssService.getPrioritizedRules("");
			Patient patient = Context.getPatientService().getPatient(patientId);
			ArrayList<Rule> ruleList = new ArrayList<Rule>();
			
			for (Rule currRule : rules)
			{
				ruleList.add(currRule);
			}
			
			ArrayList<Result> results = dssService.runRules(patient, ruleList);
			if (results != null)
			{
				for (int i = 0; i < rules.size() && i < results.size(); i++)
				{
					Rule currRule = rules.get(i);
					if(results.get(i) != null)
					{
						String currResult = results.get(i).toString();
						if (currResult != null)
						{
							currRule.setResult(currResult);
						}
					}
				}
			}
			
			map.put("rules", rules);
			map.put("numRules", rules.size());
		}
		
		map.put("patientId", patientIdString);
		
		return map;
	}

}
