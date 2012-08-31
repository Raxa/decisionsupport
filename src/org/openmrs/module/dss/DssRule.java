package org.openmrs.module.dss;

/**
 * Purpose: Provides methods to pass mlm and other rule file metadata into 
 * the dss_rule table
 * 
 * @author Tammy Dugan
 */
public interface DssRule
{
	/**
	 * Returns the priority value of the rule. This value determines
	 * in what order the rules get evaluated.
	 * @return Integer priority value
	 */
	public Integer getPriority();
	
	/**
	 * Returns a descriptive title for the rule.
	 * @return String title for the rule
	 */
	public String getTitle();
	
	/**
	 * Returns the version of the rule.
	 * @return Double version of the rule
	 */
	public Double getVersion();
	
	/**
	 * Returns the institution that authored this rule
	 * @return String institution that authored this rule
	 */
	public String getInstitution();
	
	/**
	 * Returns the name of the person that authored this rule
	 * @return String name of the person that authored this rule
	 */
	public String getAuthor();
	
	/**
	 * Returns the name of the specialist this rule applies to
	 * @return String name of the specialist this rule applies to 
	 */
	public String getSpecialist();
	
	/**
	 * Returns the date this rule was created
	 * @return String date this rule was created
	 */
	public String getDate();
	
	/**
	 * Returns the purpose of this rule
	 * @return String purpose of this rule
	 */
	public String getPurpose();
	
	/**
	 * Returns an explanation of why this rule is needed
	 * @return String explanation of why this rule is needed
	 */
	public String getExplanation();
	
	/**
	 * Returns keywords that can be used to classify the rule
	 * @return String keywords that can be used to classify the rule
	 */
	public String getKeywords();
	
	/**
	 * Returns citations that can be used as reference for this rule
	 * @return String citations that can be used as reference for this rule
	 */
	public String getCitations();
	
	/** 
	 * Returns urls that can be used as reference for this rule
	 * @return String urls that can be used as reference for this rule
	 */
	public String getLinks();
	
	/**
	 * Returns the portion of the rule logic that pulls the data to run the rule
	 * @return String portion of the rule logic that pulls the data to run the rule
	 */
	public String getData();
	
	/**
	 * Returns the logic that is applied to the data to decide what action should
	 * be taken
	 * @return String the logic that is applied to the data to decide what action
	 * should be taken
	 */
	public String getLogic();
	
	/**
	 * Returns the action that should be taken when the logic evaluates to true
	 * @return String action that should be taken when the logic evaluates to true
	 */
	public String getAction();
	
	/**
	 * Returns the type of the rule. This type is used along with the priority
	 * to evaluate prioritized rule sets
	 * @return String type of the rule
	 */
	public String getType();
	
	public Integer getAgeMin();
	
	public Integer getAgeMax();
	
	public String getAgeMinUnits();
	
	public String getAgeMaxUnits();
	
}
