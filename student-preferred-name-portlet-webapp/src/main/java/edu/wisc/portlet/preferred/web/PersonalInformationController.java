package edu.wisc.portlet.preferred.web;

import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import edu.wisc.portlet.preferred.form.PreferredName;

@Controller
@RequestMapping("VIEW")
public class PersonalInformationController {
	
	@RenderMapping
	public String initializeView(ModelMap modelMap) {
		//TODO: Select current preferred name
		String name = "TODO";
		
		//TODO: check status (pending or not
		boolean isPending = true;
		
		//submit to map
		modelMap.addAttribute("currentPreferredName",name);
		modelMap.addAttribute("pendingStatus",isPending);
		return "viewPage";
	}
	
	@RenderMapping("action=edit")
	public String initializeEdit() {
		return "editPage";
	}
	
	@ActionMapping("action=savePreferredName")
	public void submitEdit(ActionResponse response, @Valid PreferredName preferredName, BindingResult bindingResult) throws PortletModeException {
		//validation
		if(!bindingResult.hasErrors()) {
			//submit changes to DAO
			
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
