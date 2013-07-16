package edu.wisc.portlet.preferred.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

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
	
	@RenderMapping("edit")
	public String initializeEdit() {
		return "editPage";
	}
	
	@ActionMapping("submitEdit")
	public void submitEdit() {
		//validation
		
		//submit changes to dao
		
		//redirect to view page on success
		
		//fall back to edit page if there were problems
	}
	
	

}
