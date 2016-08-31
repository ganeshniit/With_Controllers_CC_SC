package com.amazon;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazon.cart1.dao.UserDetailsDAO;
import com.amazon.cart1.model.UserDetails;

@Controller
public class UserController {
	
	//when the user clicked login
	//based on the credentials i want to find whether he is admin or not
	//if he is admin i want to navigate to adminHome page
	//if he is user i want to navigate to home page
	//if the user doesnt exist with this credentials i want to give error
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	@Autowired
	UserDetails userDetails;
	
	@Autowired
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value="id") String id,@RequestParam(value="password") String password,HttpSession session)
	{
		String msg;
		userDetails=userDetailsDAO.isValidUser(id, password);
		ModelAndView mv=new ModelAndView("home");
		if(userDetailsDAO.isValidUser(id, password)==null)
		{
			msg="Invalid user...please try again";
		}
		else
		{
			
			if(userDetails.getRole().equals("ROLE_ADMIN"))
			{
				mv=new ModelAndView("adminHome");
			}
				session.setAttribute("welcomeMsg", userDetails.getName());//mv.addObject("welcomeMsg", userDetails.getName());
				session.setAttribute("userID", userDetails.getId());
		}
		return mv;
	}

}
