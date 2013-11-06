package edu.wisc.portlet.preferred.admin.web;


import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import edu.wisc.portlet.preferred.form.PreferredName;
import edu.wisc.portlet.preferred.form.validator.PreferredNameValidator;
import edu.wisc.portlet.preferred.service.PreferredNameService;

@Controller
@RequestMapping("VIEW")
public class AdminController {
	
	private PreferredNameService preferredNameService;
	
	@Autowired
	public void setPreferredNameService(PreferredNameService pns) {
		this.preferredNameService = pns;
	}
	
	@RenderMapping
	public String initializeView(ModelMap modelMap, RenderRequest request) {
		if(request.getParameter("info") != null) {
			modelMap.addAttribute("info",request.getParameter("info"));
		}
		return "selectPvi";
	}
	
	@RenderMapping(params = "action=viewPrefName")
	public String viewPrefName(@RequestParam PreferredName preferredName, ModelMap modelMap, RenderRequest request) {
		if(preferredName == null) {
			return "selectPvi";
		}
		return "viewPrefNameAdmin";
	}
	
	@RenderMapping(params="action=searchPvi")
	public String searchPvi(@RequestParam String pvi, RenderResponse response, ModelMap modelMap, RenderRequest request) throws PortletModeException {
		
		PreferredName preferredName = (PreferredName) modelMap.get("preferredName");
		
		if(preferredName == null)
			preferredName = preferredNameService.getPreferredName(pvi);
		if(request.getParameter("hasError") != null) {
			modelMap.addAttribute("hasError","true");
		}
		
		if(preferredName != null) {
			modelMap.addAttribute("preferredName", preferredName);
			return "viewPrefNameAdmin";
		} else {
			modelMap.addAttribute("error", "No preferred name found under pvi: " + pvi);
			return "selectPvi";
		}
		
	}
	
	@ActionMapping(params="action=deleteAdmin") 
	public void submitDeleteAction(ActionResponse response, @RequestParam String pvi) throws PortletModeException {
		preferredNameService.deletePreferredNameAdmin(pvi);
		response.setPortletMode(PortletMode.VIEW);
	}
	
	@ActionMapping(params="action=delete")
	public void submitDelete(ActionResponse response, @RequestParam String pvi) throws PortletModeException {
		preferredNameService.deletePreferredName(pvi);
		response.setPortletMode(PortletMode.VIEW);
	}
	
	@ActionMapping(params="action=savePreferredName")
	public void submitEdit(ActionResponse response, PreferredName preferredName, BindingResult bindingResult) throws PortletModeException {
		//validation
		ValidationUtils.invokeValidator(new PreferredNameValidator(), preferredName, bindingResult);
		if(!bindingResult.hasErrors()) {
			preferredNameService.setPreferredName(preferredName);
			response.setRenderParameter("info", "Preferred Name Updated Successfully");
			//redirect to view page on success
			response.setPortletMode(PortletMode.VIEW);
		} else {
			//fail back to edit mode with flag set
			response.setRenderParameter("action", "searchPvi");
			response.setRenderParameter("pvi", preferredName.getPvi());
			response.setRenderParameter("hasError", "true");
			response.setPortletMode(PortletMode.VIEW);
		}
	}
}
