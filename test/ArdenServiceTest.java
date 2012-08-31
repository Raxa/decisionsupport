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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.dss.ClassLoaderTask;
import org.openmrs.module.dss.hibernateBeans.Rule;
import org.openmrs.module.dss.service.DssService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.SkipBaseSetup;

@SkipBaseSetup
public class ArdenServiceTest extends BaseModuleContextSensitiveTest {
	int MAX_MLM = 1000;
	
	@Before
	public void runBeforeEachTest() throws Exception {
		authenticate();
	}
	
    @Override
    public Boolean useInMemoryDatabase() {
    	return false;
    }

    @Test
	public void testClass() throws Exception 
	{
		ClassLoaderTask task = new ClassLoaderTask();
		task.lookForNewClasses();
		DssService dssService = 
			Context.getService(DssService.class);
		Patient patient = Context.getPatientService().getPatient(9349);
		ArrayList<Rule> ruleList = new ArrayList<Rule>();
		List<org.openmrs.module.dss.hibernateBeans.Rule> rules = 
			dssService.getPrioritizedRules("");
		
		for (Rule currRule : rules)
		{
			ruleList.add(currRule);
		}
		
		String result = dssService.runRulesAsString(patient, ruleList);
		System.out.println("result is: "+result);
	}
}