package org.openmrs.module.dss.hibernateBeans;

import java.util.Date;
import java.util.Map;

import org.openmrs.Patient;
import org.openmrs.module.chirdlutil.util.Util;

/**
 * Holds information to store in the dss_rule table
 * 
 * @author Tammy Dugan
 */
public class Rule implements java.io.Serializable
{

	// Fields
	private Integer ruleId = null;
	private String classFilename = null;
	private Date creationTime = null;
	private Integer priority = null;
	private String title = null;
	private Double version = null;
	private String institution = null;
	private String author = null;
	private String specialist = null;
	private String ruleCreationDate = null;
	private String purpose = null;
	private String explanation = null;
	private String keywords = null;
	private String citations = null;
	private String links = null;
	private String data = null;
	private String logic = null;
	private String action = null;
	private String ruleType = null;
	private Date lastModified = null;
	private String result = null; // result of executing the rule
	private String tokenName = null;
	private Map<String, Object> parameters = null; // parameters for rule
													// evaluation
	private String ageMinUnits = null;
	private String ageMaxUnits = null;
	private Integer ageMin = null;
	private Integer ageMax = null;

	/**
	 * @return the ruleId
	 */
	public Integer getRuleId()
	{
		return this.ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(Integer ruleId)
	{
		this.ruleId = ruleId;
	}

	/**
	 * @return the classFilename
	 */
	public String getClassFilename()
	{
		return this.classFilename;
	}

	/**
	 * @param classFilename the classFilename to set
	 */
	public void setClassFilename(String classFilename)
	{
		this.classFilename = classFilename;
	}

	/**
	 * @return the creationTime
	 */
	public Date getCreationTime()
	{
		return this.creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Date creationTime)
	{
		this.creationTime = creationTime;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority()
	{
		return this.priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the version
	 */
	public Double getVersion()
	{
		return this.version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Double version)
	{
		this.version = version;
	}

	/**
	 * @return the institution
	 */
	public String getInstitution()
	{
		return this.institution;
	}

	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(String institution)
	{
		this.institution = institution;
	}

	/**
	 * @return the author
	 */
	public String getAuthor()
	{
		return this.author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author)
	{
		this.author = author;
	}

	/**
	 * @return the specialist
	 */
	public String getSpecialist()
	{
		return this.specialist;
	}

	/**
	 * @param specialist the specialist to set
	 */
	public void setSpecialist(String specialist)
	{
		this.specialist = specialist;
	}

	/**
	 * @return the ruleCreationDate
	 */
	public String getRuleCreationDate()
	{
		return this.ruleCreationDate;
	}

	/**
	 * @param ruleCreationDate the ruleCreationDate to set
	 */
	public void setRuleCreationDate(String ruleCreationDate)
	{
		this.ruleCreationDate = ruleCreationDate;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose()
	{
		return this.purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation()
	{
		return this.explanation;
	}

	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation)
	{
		this.explanation = explanation;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords()
	{
		return this.keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	/**
	 * @return the citations
	 */
	public String getCitations()
	{
		return this.citations;
	}

	/**
	 * @param citations the citations to set
	 */
	public void setCitations(String citations)
	{
		this.citations = citations;
	}

	/**
	 * @return the links
	 */
	public String getLinks()
	{
		return this.links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(String links)
	{
		this.links = links;
	}

	/**
	 * @return the data
	 */
	public String getData()
	{
		return this.data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * @return the logic
	 */
	public String getLogic()
	{
		return this.logic;
	}

	/**
	 * @param logic the logic to set
	 */
	public void setLogic(String logic)
	{
		this.logic = logic;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return this.action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @return the ruleType
	 */
	public String getRuleType()
	{
		return this.ruleType;
	}

	/**
	 * @param ruleType the ruleType to set
	 */
	public void setRuleType(String ruleType)
	{
		this.ruleType = ruleType;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified()
	{
		return this.lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}

	/**
	 * @return the result
	 */
	public String getResult()
	{
		return this.result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result)
	{
		this.result = result;
	}

	/**
	 * @return the tokenName
	 */
	public String getTokenName()
	{
		return this.tokenName;
	}

	/**
	 * @param tokenName the tokenName to set
	 */
	public void setTokenName(String tokenName)
	{
		this.tokenName = tokenName;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters()
	{
		return this.parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters)
	{
		this.parameters = parameters;
	}

	public String getAgeMinUnits()
	{
		return this.ageMinUnits;
	}

	public void setAgeMinUnits(String ageMinUnits)
	{
		this.ageMinUnits = ageMinUnits;
	}

	public String getAgeMaxUnits()
	{
		return this.ageMaxUnits;
	}

	public void setAgeMaxUnits(String ageMaxUnits)
	{
		this.ageMaxUnits = ageMaxUnits;
	}

	public Integer getAgeMin()
	{
		return this.ageMin;
	}

	public void setAgeMin(Integer ageMin)
	{
		this.ageMin = ageMin;
	}

	public Integer getAgeMax()
	{
		return this.ageMax;
	}

	public void setAgeMax(Integer ageMax)
	{
		this.ageMax = ageMax;
	}

	public boolean checkAgeRestrictions(Patient patient)
	{
		String minAgeUnits = null;
		String maxAgeUnits = null;
		minAgeUnits = getAgeMinUnits();
		maxAgeUnits = getAgeMaxUnits();

		boolean minUnitsOk = false;
		if (minAgeUnits != null)
		{
			minUnitsOk = processMinAge(minAgeUnits, patient);
		} else
		{
			minUnitsOk = true;
		}
		boolean maxUnitsOk = false;
		if (maxAgeUnits != null)
		{
			maxUnitsOk = processMaxAge(maxAgeUnits, patient);
		} else
		{
			maxUnitsOk = true;
		}
		
		if(minUnitsOk&&maxUnitsOk){
			return true;
		}
		return false;
	}

	private boolean processMinAge(String units,Patient patient)
	{
		Date today = new Date();
		Date birthdate = patient.getBirthdate();
		int ageInDays = Util.getAgeInUnits(birthdate, today, Util.DAY_ABBR);
		int ageInWeeks = Util.getAgeInUnits(birthdate, today, Util.WEEK_ABBR);
		int ageInMonths = Util.getAgeInUnits(birthdate, today, Util.MONTH_ABBR);
		int ageInYears = Util.getAgeInUnits(birthdate, today, Util.YEAR_ABBR);

		if (units.equalsIgnoreCase("days"))
		{
			if (getAgeMin() <= ageInDays)
			{
				return true;
			}
		} else if (units.equalsIgnoreCase("weeks"))
		{
			if (getAgeMin() <= ageInWeeks)
			{
				return true;
			}
		} else if (units.equalsIgnoreCase("months"))
		{
			if (getAgeMin() <= ageInMonths)
			{
				return true;
			}
		} else if (units.equalsIgnoreCase("years"))
		{
			if (getAgeMin() <= ageInYears)
			{
				return true;
			}
		}

		return false;
	}

	private boolean processMaxAge(String units, Patient patient)
	{
		Date today = new Date();
		Date birthdate = patient.getBirthdate();
		int ageInDays = Util.getAgeInUnits(birthdate, today, Util.DAY_ABBR);
		int ageInWeeks = Util.getAgeInUnits(birthdate, today, Util.WEEK_ABBR);
		int ageInMonths = Util.getAgeInUnits(birthdate, today, Util.MONTH_ABBR);
		int ageInYears = Util.getAgeInUnits(birthdate, today, Util.YEAR_ABBR);

		if (units.equalsIgnoreCase("days"))
		{
			if (getAgeMax() > ageInDays)
			{
				return true;
			}
		} else if (units.equalsIgnoreCase("weeks"))
		{
			if (getAgeMax() > ageInWeeks)
			{
				return true;
			}
		} else if (units.equalsIgnoreCase("months"))
		{
			if (getAgeMax() > ageInMonths)
			{
				return true;
			}
		} else if (units.equalsIgnoreCase("years"))
		{
			if (getAgeMax() > ageInYears)
			{
				return true;
			}
		}

		return false;
	}
}