package edu.wisc.portlet.preferred.admin.web;

import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import org.jasig.springframework.security.portlet.authentication.PrimaryAttributeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return "selectPvi";
	}
	
	@RenderMapping(params="action=searchPvi")
	public String searchPvi(ModelMap modelMap, RenderRequest request) {
		
		//TODO: Search PVI
		// If found 
		/// return viewInfoPage
		// else return error on selectPvi Page
		return "selectPvi";
	}
	
	@ActionMapping(params="action=delete") 
	public void submitDelete(ActionResponse response, String pvi) throws PortletModeException {
		//TODO : Change to an admin delete
		preferredNameService.deletePreferredName(pvi);
		response.setPortletMode(PortletMode.VIEW);
	}
	
	@ActionMapping(params="action=savePreferredName")
	public void submitEdit(ActionResponse response, PreferredName preferredName, BindingResult bindingResult) throws PortletModeException {
		//validation
		ValidationUtils.invokeValidator(new PreferredNameValidator(), preferredName, bindingResult);
		if(!bindingResult.hasErrors()) {
			preferredNameService.setPreferredName(preferredName);
			//redirect to view page on success
			response.setPortletMode(PortletMode.VIEW);
		} else {
			//fail back to edit mode with flag set
			response.setRenderParameter("therewasanerror", "true");
			response.setPortletMode(PortletMode.VIEW);
		}
	}
}
