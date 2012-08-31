package org.openmrs.module.dss.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dss.service.DssService;
import org.openmrs.module.dss.hibernateBeans.Rule;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class SearchRulesController extends SimpleFormController
{

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * 
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

		Rule rule = new Rule();

		String title = request.getParameter("title");
		if (title == null || title.length() == 0)
		{
			title = "";
		} else
		{
			rule.setTitle(title);
		}
		map.put("title", title);
		
		String author = request.getParameter("author");
		if (author == null || author.length() == 0)
		{
			author = "";
		} else
		{
			rule.setAuthor(author);
		}
		map.put("author", author);

		String keywords = request.getParameter("keywords");
		if (keywords == null || keywords.length() == 0)
		{
			keywords = "";
		} else
		{
			rule.setKeywords(keywords);
		}
		map.put("keywords", keywords);

		String ruleType = request.getParameter("ruleType");
		if (ruleType == null || ruleType.length() == 0)
		{
			ruleType = "";
		} else
		{
			rule.setRuleType(ruleType);
		}
		map.put("ruleType", ruleType);
		
		String action = request.getParameter("action");
		if (action == null || action.length() == 0)
		{
			action = "";
		} else
		{
			rule.setAction(action);
		}
		map.put("action", action);

		String logic = request.getParameter("logic");
		if (logic == null || logic.length() == 0)
		{
			logic = "";
		} else
		{
			rule.setLogic(logic);
		}
		map.put("logic", logic);
		
		String data = request.getParameter("data");
		if (data == null || data.length() == 0)
		{
			data = "";
		} else
		{
			rule.setData(data);
		}
		map.put("data", data);
		
		String links = request.getParameter("links");
		if (links == null || links.length() == 0)
		{
			links = "";
		} else
		{
			rule.setLinks(links);
		}
		map.put("links", links);
		
		String citations = request.getParameter("citations");
		if (citations == null || citations.length() == 0)
		{
			citations = "";
		} else
		{
			rule.setCitations(citations);
		}
		map.put("citations", citations);
		
		String explanation = request.getParameter("explanation");
		if (explanation == null || explanation.length() == 0)
		{
			explanation = "";
		} else
		{
			rule.setExplanation(explanation);
		}
		map.put("explanation", explanation);
		
		String purpose = request.getParameter("purpose");
		if (purpose == null || purpose.length() == 0)
		{
			purpose = "";
		} else
		{
			rule.setPurpose(purpose);
		}
		map.put("purpose", purpose);
		
		String specialist = request.getParameter("specialist");
		if (specialist == null || specialist.length() == 0)
		{
			specialist = "";
		} else
		{
			rule.setSpecialist(specialist);
		}
		map.put("specialist", specialist);
		
		String institution = request.getParameter("institution");
		if (institution == null || institution.length() == 0)
		{
			institution = "";
		} else
		{
			rule.setInstitution(institution);
		}
		map.put("institution", institution);
		
		String classFilename = request.getParameter("classFilename");
		if (classFilename == null || classFilename.length() == 0)
		{
			classFilename = "";
		} else
		{
			rule.setClassFilename(classFilename);
		}
		map.put("classFilename", classFilename);
		
		boolean runSearch = false;

		if (request.getParameter("runSearch") != null
				&& request.getParameter("runSearch").length() > 0)
		{
			runSearch = true;
		}

		if (runSearch)
		{
			DssService dssService = Context
					.getService(DssService.class);
			List<Rule> rules = dssService.getRules(rule,true,true,null);
			map.put("rules", rules);
		}
		map.put("runSearch", runSearch);

		return map;
	}

}
