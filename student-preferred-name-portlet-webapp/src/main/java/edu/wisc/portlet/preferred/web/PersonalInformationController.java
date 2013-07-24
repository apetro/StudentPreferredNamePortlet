package edu.wisc.portlet.preferred.web;

import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.validation.Valid;

import org.jasig.springframework.security.portlet.authentication.PrimaryAttributeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import edu.wisc.portlet.preferred.form.PreferredName;
import edu.wisc.portlet.preferred.service.PreferredNameService;

@Controller
@RequestMapping("VIEW")
public class PersonalInformationController {
	
	private PreferredNameService preferredNameService;
	
	@Autowired
	public void setPreferredNameService(PreferredNameService pns) {
		this.preferredNameService = pns;
	}
	
	@RenderMapping
	public String initializeView(ModelMap modelMap, RenderRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String> userInfo = (Map <String, String>) request.getAttribute(PortletRequest.USER_INFO);
		
		/*
		final String pvi = userInfo.get("wiscedupvi");*/
		final String pvi = PrimaryAttributeUtils.getPrimaryId();
		PreferredName preferredName = preferredNameService.getPreferredName(pvi);
		
		
		modelMap.addAttribute("firstName", preferredName.getFirstName());
		modelMap.addAttribute("middleName", preferredName.getMiddleName());
		modelMap.addAttribute("pendingStatus",preferredNameService.isPending() ? "Pending" : "Approved");
		modelMap.addAttribute("displayName",userInfo.get("displayName"));
		
		return "viewPage";
	}
	
	@RenderMapping(params="action=edit")
	public String initializeEdit() {
		return "editPage";
	}
	
	@ActionMapping(params="action=savePreferredName")
	public void submitEdit(ActionResponse response, @Valid PreferredName preferredName, BindingResult bindingResult) throws PortletModeException {
		//validation
		if(!bindingResult.hasErrors()) {
			//submit changes to DAO
			final String pvi = PrimaryAttributeUtils.getPrimaryId();
			preferredName.setPvi(pvi);
			
			preferredNameService.setPreferredName(preferredName);
			//redirect to view page on success
			response.setPortletMode(PortletMode.VIEW);
		} else {
			//	fall back to edit page if there were problems
			response.setRenderParameter("firstName", preferredName.getFirstName());
			response.setRenderParameter("middleName", preferredName.getMiddleName());
			response.setRenderParameter("action", "edit");
		}
	}
}
