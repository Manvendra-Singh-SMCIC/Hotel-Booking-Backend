package spring.boot.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.boot.constants.Web;
import spring.boot.entity.User;
import spring.boot.webapp.entity.NewUser;

@Controller
public class PageController {

	@GetMapping(value = "/about")
	public String about(Model model) {
		return "about";
	}
	
	@GetMapping(value = { "/base", "/" })
	public String home(Model model) {
		model.addAttribute(Web.LOGGEDIN.toString(), false);
		return "home";
	}

	@GetMapping(value = { "/login" })
	public String login(Model model) {
		model.addAttribute(Web.LOGGEDIN.toString(), false);
		model.addAttribute(Web.USER.toString(), new User());
		model.addAttribute(Web.LOGINDATA.toString(), new spring.boot.webapp.entity.User());
		return "login";
	}
	

	@GetMapping(value = { "/signup" })
	public String signup(Model model) {
		model.addAttribute(Web.LOGGEDIN.toString(), false);
		model.addAttribute(Web.USER.toString(), new User());
		model.addAttribute(Web.SIGNUPDATA.toString(), new NewUser());
		return "signup";
	}
}
