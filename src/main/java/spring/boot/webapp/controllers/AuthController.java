package spring.boot.webapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import spring.boot.constants.Web;
import spring.boot.entity.User;
import spring.boot.service.UserService;
import spring.boot.webapp.entity.NewUser;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/submitlogin")
	public String submitLogin(@Valid @ModelAttribute("LOGINDATA") spring.boot.webapp.entity.User user,
			BindingResult result, Model model) {
		System.out.println(user);
		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}
		List<User> users = userService.findByEmailAndPassword(user.email, user.password);
		if (users.size() == 0) {
			return "login";
		}
		model.addAttribute(Web.USER.toString(), users.get(0));
		model.addAttribute(Web.LOGGEDIN.toString(), true);
		return "profile";
	}
	
	@PostMapping(value = "/submitsignup")
	public String submitSignup(@Valid @ModelAttribute("SIGNUPDATA") NewUser newUser,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "signup";
		}
		User user = User.builder().email(newUser.email).name(newUser.name).phoneNumber(newUser.phoneNumber).dob(newUser.dob).password(newUser.password).role(Web.VENDOR.toString()).build();
		User outcome = userService.add(user);
		if(outcome == null) {
			return "signup";
		}
		List<User> users = userService.findByEmailAndPassword(newUser.email, newUser.password);
		model.addAttribute(Web.USER.toString(), users.get(0));
		model.addAttribute(Web.LOGGEDIN.toString(), true);
		System.out.println(users.get(0));
		return "profile";
	}
	
	
	@GetMapping(value = "/profile")
	public String profile( Model model, @ModelAttribute("USER") User user) {
		return "profile";
	}
}

