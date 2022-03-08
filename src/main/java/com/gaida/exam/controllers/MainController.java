package com.gaida.exam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaida.exam.models.Rating;
import com.gaida.exam.models.TvShow;
import com.gaida.exam.models.User;
import com.gaida.exam.services.ApplicationService;

@Controller
public class MainController {
	
	@Autowired
	ApplicationService appService;
	

	@GetMapping("/shows")
	public String dashboard(HttpSession session, Model model) {
		
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}
		
		User currentUser = this.appService.findUserByID((long)session.getAttribute("userid"));
		List<TvShow> allShows = this.appService.findAllShows();
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allShows", allShows);
		
		return "index.jsp";
	}
	
	//*************************************************************************
	//Routes to add new program
	//*************************************************************************
	
	@GetMapping("/shows/new")
	public String addNew(HttpSession session, @ModelAttribute("newShow") TvShow newShow) {
		
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}		
		
		return "addPage.jsp";
	}
	
	@PostMapping("/shows/create")
	public String createShow(@Valid @ModelAttribute("newShow") TvShow newShow, BindingResult result,HttpSession session,Model model) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}	
		
		if(result.hasErrors()) {
			model.addAttribute("newShow", newShow);
				return "addPage.jsp";
		}
		
		List<TvShow> allShows= (List<TvShow>) this.appService.findAllShows();
		String newShowTitle = newShow.getTitle().toLowerCase().trim();
		
		System.out.println(newShowTitle);
		
		for(int i = 0 ; i<allShows.size();i++) {
			String priorShow = allShows.get(i).getTitle().toLowerCase().trim(); 
			if(newShowTitle.compareTo(priorShow)==0) {
				result.rejectValue("title", "matched", "This Title already exists");
			}
			
			
		}
		
		if(result.hasErrors()) {
			model.addAttribute("newShow", newShow);
				return "addPage.jsp";
		}
		
		newShow.setPerson(this.appService.findUserByID((long)session.getAttribute("userid")));
		
		this.appService.addShow(newShow);
		
		return "redirect:/shows";
		
	}


	//*************************************************************************
	//details page selection
	//*************************************************************************
	
	@GetMapping("/shows/{showid}")
	public String showDetail(HttpSession session, Model model,@PathVariable("showid")long showid) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}	
		
		long curUser = (long)session.getAttribute("userid");
		System.out.println(curUser);
		TvShow aShow = this.appService.findShowById(showid);
		User creator = this.appService.findUserByID(aShow.getPerson().getId());
		List<User> allUsers = this.appService.findAllUsers();
		model.addAttribute("aShow", aShow);
		model.addAttribute("creator", creator);
		model.addAttribute("theRate", new Rating());
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("curUser", curUser);
		return "showDetail.jsp";
	}
	
	@GetMapping("/shows/{showid}/edit")
	public String editShow(HttpSession session,Model model,@PathVariable("showid") long showid ) {
		
		TvShow aShow = this.appService.findShowById(showid);
		model.addAttribute("aShow", aShow);
		
		return "editShow.jsp";
	}
	
	
	@PutMapping("/shows/update")
	public String updateShow(@Valid @ModelAttribute("aShow") TvShow aShow,BindingResult result,Model model) {
		
	
		
		List<TvShow> allShows= (List<TvShow>) this.appService.findAllShows();
		String newShowTitle = aShow.getTitle().toLowerCase().trim();
		long ourShowID = aShow.getId();
		
		
		for(int i = 0 ; i<allShows.size();i++) {
			String priorShow = allShows.get(i).getTitle().toLowerCase().trim();
			long priorShowID = allShows.get(i).getId();
			if(ourShowID!=priorShowID) {
				
				if(newShowTitle.compareTo(priorShow)==0) {
				result.rejectValue("title", "matched", "This Title already exists");
				}
			}			
			
		}
		
		if(result.hasErrors()) {
			model.addAttribute("aShow", aShow);
			return "editShow.jsp";
		}
		
		this.appService.addShow(aShow);
		return "redirect:/shows"; 
	}
	
	
	@RequestMapping("/shows/{showid}/delete")
	public String deleteShow(@PathVariable("showid") long showid,HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}
		TvShow showToremove = this.appService.findShowById(showid);
		this.appService.deleteShow(showToremove);
		
		return "redirect:/shows";
	}
	
	@PostMapping("/shows/{showid}/rate")
	public String rateShow(@PathVariable("showid")long showid, @Valid @ModelAttribute("theRate") Rating theRate,BindingResult result,Model model,HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}
		
		long curUser= (long)	session.getAttribute("userid");
		if(result.hasErrors()) {
			TvShow aShow = this.appService.findShowById(showid);
			User creator = this.appService.findUserByID(aShow.getPerson().getId());
			model.addAttribute("aShow", aShow);
			model.addAttribute("creator", creator);
			model.addAttribute("theRate", theRate);
				model.addAttribute("curUser", curUser);
			return "showDetail.jsp";
		}
		
		this.appService.addRating(theRate);
		return "redirect:/shows/"+showid;
		
	}
	
	
}
