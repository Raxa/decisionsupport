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
 
public class DeleteRulesController extends SimpleFormController
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
 
                DssService dssService = Context
                                .getService(DssService.class);
                String[] rulesToDelete = request.getParameterValues("RulesToDelete");
 
                if (rulesToDelete != null)
                {
                        for (String currRuleToDelete : rulesToDelete)
                        {
                                dssService.deleteRule(Integer.parseInt(currRuleToDelete));
                        }
                        map.put("rulesToDelete", rulesToDelete);
                }
                List<Rule> rules = dssService.getPrioritizedRules("");
                
                map.put("rules", rules);
 
                return map;
        }
 
}