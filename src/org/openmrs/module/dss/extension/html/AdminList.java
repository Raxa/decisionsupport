package org.openmrs.module.dss.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 * Purpose: Provides links to jsp pages for the module
 * on the administration page of openmrs
 * 
 * @author Tammy Dugan
 *
 */
public class AdminList extends AdministrationSectionExt {

	@Override
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	@Override
	public String getTitle() {
		return "dss.title";
	}
	
	@Override
	public Map<String, String> getLinks() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("module/dss/manage_rules.htm", "Manage Rules");
		map.put("module/dss/ruleTester.form", "Rule Tester");

		return map;
	}
}
