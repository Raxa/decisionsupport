/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.logic.Rule;
import org.openmrs.logic.rule.provider.AbstractRuleProvider;
import org.openmrs.module.dss.service.DssService;
import org.springframework.stereotype.Component;

/**
 * @author Steve McKee
 */
@Component
public class DssRuleProvider extends AbstractRuleProvider {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see org.openmrs.logic.rule.provider.RuleProvider#getRule(java.lang.String)
	 */
	public Rule getRule(String configuration) {
		String token = getTokenFromConfiguration(configuration);
		try {
	        return Context.getService(DssService.class).loadRule(token, false);
        }
        catch (Exception e) {
	        log.error("Error finding rule: " + token, e);
        }
        
        return null;
	}
	
	/**
	 * Returns the token name based on the configuration.  Currently, the DSS rule configurations 
	 * are the class name complete with full package prefix.
	 * 
	 * @param configuration The class name with the full package prefix.
	 * @return The token name (which is the class name without the package prefix.
	 */
	private String getTokenFromConfiguration(String configuration) {
		if (configuration == null) {
			return null;
		}
		
		int lastPeriod = configuration.lastIndexOf(".");
		if (lastPeriod < 0) {
			return configuration;
		}
		
		return configuration.substring(lastPeriod + 1, configuration.length());
	}
}