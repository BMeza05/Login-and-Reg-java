package com.brian.beltexam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brian.beltexam.models.Task;
import com.brian.beltexam.models.User;
import com.brian.beltexam.services.TaskService;
import com.brian.beltexam.services.UserService;
import com.brian.beltexam.validators.UserValidator;



@Controller
public class Users {

	 private final UserService userService;
	    
	 private final UserValidator userValidator;

	 private final TaskService taskService;
	    
	 
	 
	    public Users(UserService userService, UserValidator userValidator, TaskService taskService) {
	        this.userService = userService;
	        this.userValidator = userValidator;
	        this.taskService = taskService;
			
	    }
	    

	    
	 @GetMapping("/")
	 public String login() {
		 return "login.jsp";
	 }
	 
	@GetMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "register.jsp";
	} 
	
	@GetMapping("/tasks/new")
	public String newTask(@ModelAttribute("task") Task task, Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(id);
		model.addAttribute("user",user);
		return "newTask.jsp";
	}
	//=====================================
	//Register
	    
	 @RequestMapping(value="/register", method=RequestMethod.POST)
	    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
	        // if result has errors, return the home page 
	        // else, save the user in the database, save the user id in session, and redirect them to the /ideas route
	    	 userValidator.validate(user, result);
	         if(result.hasErrors()) {
	             return "register.jsp";
	         }
	         User u = userService.registerUser(user);
	         session.setAttribute("user_id", u.getId());
	         return "redirect:/tasks";
	     }
	 //================================================
	 //Login
	 
	 
	   @RequestMapping(value="/", method=RequestMethod.POST)
	    public String loginUser(@RequestParam("email") 
	    						String email, @RequestParam("password") 
	    						String password, Model model, HttpSession session,
	    						RedirectAttributes flash) {
	        // if the user is authenticated, save their user id in session
	    	if(userService.authenticateUser(email, password)) {
	    		User thisUser = userService.findByEmail(email);
	    		//if there is a user, store in session
	    		session.setAttribute("user_id", thisUser.getId());
	    		//after the user is stored in session, send them back to the home page logged in
	    		return "redirect:/tasks";
	    	}
	    	
	        // else, add error messages and return the login page
	    	else {
	    		flash.addFlashAttribute("error", "incorrect email or password");
	    	}
	    	return "redirect:/";
	    }
	   //======================
	   //New task
	   @RequestMapping(value="/tasks/new", method=RequestMethod.POST)
	   public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result, RedirectAttributes flash, HttpSession session) {
		   //if task is blank
		   if(result.hasErrors()) {
			   flash.addFlashAttribute("error", "task required");
			   return "newTask.jsp";
		   }

		   Task t = taskService.createTask(task);
		   return "redirect:/tasks";
		     }
	   
	   //===============================
	   //Edit task
	   
	   @RequestMapping("/tasks/{id}/edit")
	   public String edit(@PathVariable("id") Long id, Model model) {
		   Task task = taskService.getOneTask(id);
		   model.addAttribute("task", task);
		   return "edit.jsp";
	   }
	   
	   @PutMapping("/tasks/{id}/edit")
	   public String editTask(@Valid @ModelAttribute("task")Task task, BindingResult result, RedirectAttributes flash) {
		   if(result.hasErrors()) {
			   flash.addFlashAttribute("error", "must contain at least 1 character");
			   return "edit.jsp";
		   }
		   Task t = taskService.updateTask(task);
		   
		   return "redirect:/tasks";
	   }
	   
	   //=======================
	   ///DELETE TASK :O
	   
	   @DeleteMapping("/tasks/{id}/edit")
	   public String deleteTask (@PathVariable("id") Long id){
		   taskService.deleteTask(id);
		   return "redirect:/tasks";
	   }
	   
	   @GetMapping("/tasks/{id}/details")
	   public String details(@PathVariable ("id") Long task_id , Model model, HttpSession session) {
		 
		   Long id=(Long) session.getAttribute("user_id");
		   
		   
		   User thisUser = userService.findUserById(id);
		   Task thisTask = taskService.getOneTask(task_id);
		   
		   model.addAttribute("task",thisTask);
   		   model.addAttribute("user", thisUser);
   		
		  
		   return "details.jsp";
	   }
	   
	   @RequestMapping("/tasks")
	    public String home(HttpSession session, Model model ) {
	        // get user from session, save them in the model and return the home page(/ideas)
		   Long id=(Long) session.getAttribute("user_id");
	    	
	    	if(id != null) {
	    		User thisUser = userService.findUserById(id);
	    		model.addAttribute("user", thisUser);
	    		model.addAttribute("users", userService.allUsers());
	    		model.addAttribute("tasks" ,taskService.allTasks());
	    		
	    	}
	    	else {
	    		return "redirect:/";
	    	}
	  	    	return "tasks.jsp";
	    }
	    @RequestMapping("/logout")
	    public String logout(HttpSession session) {
	        // invalidate session
	    	session.invalidate();
	        // redirect to login page
	    	return "redirect:/";
	    }
	    
}
