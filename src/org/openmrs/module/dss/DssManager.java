/**
 * 
 */
package org.openmrs.module.dss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openmrs.Patient;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.logic.result.Result;
import org.openmrs.module.dss.hibernateBeans.Rule;
import org.openmrs.module.dss.service.DssService;

/**
 * Evaluates prioritized rules.
 * 
 * @author Tammy Dugan
 * 
 */
public class DssManager
{
	private HashMap<String,ArrayList<DssElement>> dssElementsByType = null;
	private Patient patient = null;
	private HashMap<String, Integer> maxDssElementsByType = null;

	/**
	 * Constructor 
	 * Initializes the patient
	 * @param patient Patient to evaluate rules for
	 */
	public DssManager(Patient patient)
	{
		this.patient = patient;
	}
	
	/**
	 * Runs rules of the given type in order of priority until a specified number
	 * of non-null results are obtained.
	 * @param type rule type
	 * @param override true if the dssElements should be re-populated
	 * @param parameters parameters to be passed to rule evaluation
	 */
	public void populateDssElements(String type, boolean override,
			Map<String, Object> parameters)
	{
		if(this.dssElementsByType == null)
		{
			this.dssElementsByType = new HashMap<String,ArrayList<DssElement>>();
		}
		ArrayList<DssElement> dssElements = this.dssElementsByType.get(type);
		if(!(override||dssElements==null))
		{
			return;
		}
		dssElements = new ArrayList<DssElement>();
		this.dssElementsByType.put(type, dssElements);
		DssService dssService = Context
				.getService(DssService.class);
		List<Rule> rules = null;
		if(type == null)
		{
			rules = dssService.getPrioritizedRules("");
		}else
		{
			rules = dssService.getPrioritizedRules(type);
		}
		Iterator<Rule> iter = rules.iterator();
		Rule currRule = null;
		ArrayList<Rule> ruleList = null;
		DssElement currDssElement = null;
		ArrayList<Result> results = null;
		Result currResult = null;
		int maxDssElements = getMaxDssElementsByType(type);
		
		//Run rules until there are maxDssElements non-null results
		while (dssElements.size() < maxDssElements && iter.hasNext()) {
			ruleList = new ArrayList<Rule>();
			
			//get rules that meet the age restrictions
			currRule = iter.next();
			parameters.put("promptPosition", Integer.toString(dssElements.size() + 1));
			currRule.setParameters(parameters);
			
			if (currRule.checkAgeRestrictions(this.patient)) {
				ruleList.add(currRule);
			} else {
				continue;//skip to the beginning of the loop if the age restrictions are not met
			}
			
			results = dssService.runRules(this.patient, ruleList);
			
			//only add results that are non-null
			
			currRule = ruleList.get(0);
			currResult = results.get(0);
			if (currResult != null && !currResult.isNull()) {
				currDssElement = new DssElement(currResult, currRule.getRuleId());
				dssElements.add(currDssElement);
			}
		}
	}

	/**
	 * Returns a dss element by index and type
	 * @param index position of the element
	 * @param type rule type
	 * @return DssElement element with the given index and rule type
	 */
	public DssElement getDssElement(int index,String type)
	{
		DssElement dssElement = null;
		if(this.dssElementsByType == null)
		{
			return null;
		}
		ArrayList<DssElement> dssElements = this.dssElementsByType.get(type);
		
		if (dssElements == null || !(index < dssElements.size()))
		{
			return null;
		}

		dssElement = dssElements.get(index);

		return dssElement;
	}
	
	/**
	 * Returns all dss elements
	 * @return HashMap of dss elements by type
	 */
	public HashMap<String,ArrayList<DssElement>> getDssElementsByType()
	{
		return this.dssElementsByType;
	}

	/**
	 * Sets the maximum number of dss elements that should be populated
	 * for a given rule type
	 * @param type rule type
	 * @param maxDssElements maximum dss elements that should be populated
	 */
	public void setMaxDssElementsByType(String type, int maxDssElements)
	{
		if(this.maxDssElementsByType == null)
		{
			this.maxDssElementsByType = new HashMap<String,Integer>();
		}
		
		this.maxDssElementsByType.put(type, new Integer(maxDssElements));
	}
	
	/**
	 * Returns the maximum number of dss elements that should be 
	 * populated for the given rule type
	 * @param type rule type
	 * @return int maximum number of dss elements that should be
	 * populated for the given rule type
	 */
	public int getMaxDssElementsByType(String type)
	{
		Integer lookup = null;
		
		if (this.maxDssElementsByType != null)
		{
			lookup = this.maxDssElementsByType.get(type);
		}
		
		if(lookup != null)
		{
			return lookup;
		}
		return 0;
	}
}
