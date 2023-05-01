package com.ssafy.enjoytrip.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@GetMapping("navigator/searchTripView")
    public ModelAndView searchTrip(ModelAndView mav) {
    	try {	
    		mav.setViewName("attr/attr");
    	} catch(Exception e) {
    		e.printStackTrace();
    		mav.setViewName("error");
    	}
    	return mav;
    }

}
