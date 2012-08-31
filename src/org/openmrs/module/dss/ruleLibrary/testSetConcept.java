package org.openmrs.module.dss.ruleLibrary;

import java.util.Map;
import java.util.Set;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.ConceptService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicCriteria;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.chirdlutil.util.Util;
import org.openmrs.Concept;
import org.openmrs.logic.impl.LogicCriteriaImpl;
import org.openmrs.logic.op.OperandConcept;
import org.openmrs.logic.op.Operator;

public class testSetConcept implements Rule
{
	private Log log = LogFactory.getLog(this.getClass());

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
		Result ruleResult = null;
		PatientService patientService = Context.getPatientService();
		Patient patient = patientService.getPatient(patientId);

		try
		{
			ConceptService conceptService = Context.getConceptService();
			// term number for ABNORMAL
			Concept answer = conceptService.getConcept(556);

			// name of the set to search
			Concept set = conceptService.getConceptByName("TEST SET");

			List<Concept> concepts = conceptService.getConceptsInSet(set);

			LogicCriteria criteria = null;

			// add query restrictions for each concept in the set
			for (Concept currConcept : concepts)
			{
				if (criteria == null)
				{
					criteria = new LogicCriteriaImpl(currConcept
							.getName().getName());
				} else
				{
					criteria = criteria.or(new LogicCriteriaImpl(currConcept
							.getName().getName()));
				}
			}

			if (criteria != null)
			{
				// and concept criteria with "answer" criteria
				criteria = criteria.and(
						new LogicCriteriaImpl(Operator.CONTAINS, new OperandConcept(answer))).last();
			}

			ruleResult = context.read(patientId, context
					.getLogicDataSource("obs"), criteria);

			if (ruleResult.exists())
			{
				return ruleResult;
			}
		} catch (Exception e)
		{
			this.log.error(e.getMessage());
			this.log.error(Util.getStackTrace(e));
		}
		return Result.emptyResult();
	}
}