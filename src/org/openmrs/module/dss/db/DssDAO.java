package org.openmrs.module.dss.db;

import java.util.List;

import org.openmrs.module.dss.hibernateBeans.Rule;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dss related database functions
 * 
 * @author Tammy Dugan
 */
@Transactional
public interface DssDAO {

	/**
	 * Looks up a rule from the dss_rule table by rule_id
	 * @param ruleId unique identifier for rule in the dss_rule table
	 * @return Rule from the dss_rule table
	 */
	public Rule getRule(int ruleId);
	
	/**
	 * Looks up a rule from the dss_rule table by token name
	 * @param tokenName name that is used to register a rule with the openmrs LogicService
	 * @return Rule from the dss_rule table
	 */
	public Rule getRule(String tokenName);
	
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
	public List<Rule> getRules(Rule rule,boolean ignoreCase, 
			boolean enableLike, String sortColumn);
	
	/**
	 * Adds a new rule to the dss_rule table
	 * @param rule new rule to add to the dss_rule table
	 * @return Rule added to the dss_rule table
	 */
	public Rule addOrUpdateRule(Rule rule);
	
	/**
	 * Deletes an existing rule in the dss_rule table
	 * @param ruleId unique id of the rule to delete
	 */
	public void deleteRule(int ruleId);
}
