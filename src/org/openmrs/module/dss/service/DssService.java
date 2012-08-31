package org.openmrs.module.dss.service;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.logic.result.Result;
import org.openmrs.module.dss.DssRule;
import org.openmrs.module.dss.hibernateBeans.Rule;

/**
 * Defines services used by this module
 *  
 * @author Tammy Dugan
 *
 */
public interface DssService
{
	/**
	 * Runs a list of rules and returns a string result
	 * @param p Patient to run the rules for
	 * @param ruleList List of rules to run
	 * @return String result as a string
	 */
	public String runRulesAsString(Patient p, List<Rule> ruleList);

	/**
	 * Runs a single rule and returns the result as an openmrs Result object
	 * @param p Patient to run the rules for
	 * @param rule single rule to evaluate
	 * @return String result as an openmrs Result object
	 */
	public Result runRule(Patient p, Rule rule);

	/**
	 * Runs a list of rules and returns an arraylist of openmrs Result objects
	 * @param p Patient to run the rules for
	 * @param ruleList list of rules to evaluate
	 * @return ArrayList of openmrs Result objects
	 */
	public ArrayList<Result> runRules(Patient p, List<Rule> ruleList);

	/**
	 * Looks up a rule from the dss_rule table by rule_id
	 * @param ruleId unique id for a rule in the dss_rule table
	 * @return Rule rule from the dss_rule table
	 * @throws APIException
	 */
	public Rule getRule(int ruleId) throws APIException;

	/**
	 * Adds a new rule to the dss_rule table
	 * @param classFilename name of the compiled class file that contains the 
	 * executable rule
	 * @param rule DssRule to save to the dss_rule table
	 * @return Rule rule that was added to the dss_rule table
	 * @throws APIException
	 */
	public Rule addRule(String classFilename, DssRule rule) throws APIException;

	/**
	 * Deletes an existing rule from the dss_rule table based on the ruleId
	 * @param ruleId unique id for the rule to be deleted from the dss_rule table
	 */
	public void deleteRule(int ruleId);
		
	public List<Rule> getPrioritizedRules(String type);
	
	public List<Rule> getNonPrioritizedRules(String type);
		
	/**
	 * Returns a list of rules from the dss_rule table that match the criteria
	 * assigned to the rule parameter
	 * @param rule Rule whose assigned attributes indicate the restrictions
	 * of the dss_rule table query
	 * @param ignoreCase String attributes assigned in the Rule parameter should
	 * be matched in the dss_rule query regardless of case
	 * @param enableLike String attributes assigned in the Rule parameter should
	 * be matched in the dss_rule query using LIKE instead of exact matching
	 * @return List<Rule>
	 */
	public List<Rule> getRules(Rule rule, boolean ignoreCase, boolean enableLike, String sortColumn);

	/**
	 * Loads a rule into the openmrs LogicService in preparation for executing it
	 * @param rule name that the rule will be stored under in the openmrs LogicService
	 * @return Rule object
	 * @throws Exception
	 */
	public org.openmrs.logic.Rule loadRule(String rule, boolean updateRule) throws Exception;
}