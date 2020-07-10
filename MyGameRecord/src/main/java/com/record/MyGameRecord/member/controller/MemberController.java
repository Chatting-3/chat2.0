package com.record.MyGameRecord.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.record.MyGameRecord.member.service.MemberService;
import com.record.MyGameRecord.member.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	   public String memberLogin(Member m, Model model){
	      Member loginUser = mService.loginMember(m);
	      System.out.println(loginUser);
	    
	      if(loginUser != null) {
	         model.addAttribute("loginUser", loginUser);
	         
	         return "redirect:chatting.do";
	         
	      }
	      
	      return "redirect:chatting.do";
}
	@RequestMapping(value="logout.do",method=RequestMethod.GET)
	public String logout(SessionStatus status) {

		status.setComplete();
		
		return "home";
	}
}
