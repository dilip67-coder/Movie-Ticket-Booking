package com.movieTicket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movieTicket.model.Bookings;
import com.movieTicket.model.Movie;
import com.movieTicket.model.RegisterUser;
import com.movieTicket.service.BookingService;
import com.movieTicket.service.MovieService;
import com.movieTicket.service.RegisterUserService;

@Controller
public class LoginController {
@Autowired
private RegisterUserService userService;
@Autowired
private MovieService movieService;
@Autowired
private BookingService bookingService;
	long movieId;
	
	@GetMapping("/login/{id}")
	public String loginHome(@PathVariable Long id, Model model) {
		model.addAttribute("movieId", id);
		return "LoginFolder/login";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "LoginFolder/login";
	}
	@GetMapping("/loginVerify")
	public ModelAndView loginVerify(@RequestParam("loginName") String username, 
			@RequestParam("loginPassword") String password, 
			@RequestParam("movieId") long movieId, ModelAndView view) {
		
		List<RegisterUser> allUser = userService.getAllUser();
		boolean flag = false;
		ModelAndView modelView = new ModelAndView("bookTicket");
		for(int i=0; i<allUser.size(); i++) {
			if(username.equals(allUser.get(i).getRegisterName()) && password.equals(allUser.get(i).getRegisterPassword())) {
				flag=true;
				modelView.addObject("user", allUser.get(i));
				Movie movie = movieService.getMovieById(movieId);
				modelView.addObject("movie", movie);
				//System.out.println("Users Id ==> "+ allUser.get(i).getId());
				modelView.addObject("userId", allUser.get(i).getId());
			}
		}
		
		if(flag) {
			return modelView;
		}else {
			ModelAndView mv = new ModelAndView("LoginFolder/registerUser");
			return mv;
		}
		
	}
	@GetMapping("/registerUser")
	public String registerUser() {
		
		return "LoginFolder/registerUser";
	}
	
	@GetMapping("registerUserPost")
	public String registerUserPost(@ModelAttribute RegisterUser user) {
		
		userService.saveUser(user);
		
		return "redirect:/";
	}
	
	@GetMapping("/bookingsLogin")
	public String showBookings() {
			
		return "LoginFolder/bookingsLogin";
	}
	
	@GetMapping("/getBookingsData")
	public String getBookingsData(@RequestParam("username") String username,
												@RequestParam("password") String password,
												Model model) {
		List<RegisterUser> allUser = userService.getAllUser();
		for(int i = 0; i < allUser.size(); i++) {
			if(username.equals(allUser.get(i).getRegisterName()) && password.equals(allUser.get(i).getRegisterPassword())) {
				
				List<Bookings> allBookingsById = bookingService.getAllBookingsById(allUser.get(i).getId());
				/*
				 * System.out.println("Username "+ username +" && password "+ password);
				 * System.out.println("Username "+ allUser.get(i).getRegisterName()
				 * +" && password "+ allUser.get(i).getRegisterPassword());
				 */
				model.addAttribute("bookings", allBookingsById);
				System.out.println("All Bookings by Id"+ allBookingsById);
			}
		}
		return "showBookings";
	}
}
