package com.record.MyGameRecord.chat.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.record.MyGameRecord.chat.service.ChatRoomService;
import com.record.MyGameRecord.chat.vo.chatroom;
import com.record.MyGameRecord.member.vo.Member;


@Controller
public class ChatController {

	@Autowired
	ChatRoomService cService;
	
	@RequestMapping(value="chatting.do",method = RequestMethod.GET)
	public ModelAndView chat(ModelAndView mv, HttpSession session) {
	mv.setViewName("chat/chattingview");
		
		//사용자 정보 출력(세션)//
		Member loginUser = (Member)session.getAttribute("loginUser");
	
		
		mv.addObject("userId", loginUser.getId());
		
		session.setAttribute("userId", loginUser.getId());
		
		return mv;
		
		
		
		
		
	}
	
	@RequestMapping("chatRoominsertpage.do")
	public String chatroominsert(ModelAndView mv) {
		return "chat/chatroominsert";
}
	
	@RequestMapping("chatroominsert.do")
	public ModelAndView Chatroominsert(HttpServletRequest request, chatroom cr, HttpSession session, ModelAndView mv) {
		
		System.out.println(cr);
		
		int result = cService.Chatroominsert(cr); 
		
		
		if(result > 0) {
			cr = cService.Chatroomselect(cr.getChatroomname());
			System.out.println("번호까지 다조회해와서 " + cr);
			mv.addObject("cr", cr).setViewName("chat/chatroomdetail");
			
			Member loginUser = (Member)session.getAttribute("loginUser");
			session.setAttribute("userId", loginUser.getId());
			
			return mv;
		}else {
			mv.addObject("<script> alert('채팅방 생성이 실패했습니다.'); history.back(); </script>");
			return mv;			
		}
		
	}
	
	@RequestMapping("chatlist.do")
	public ModelAndView chatlist(ModelAndView mv) {
	
		ArrayList<chatroom> chatroom = cService.selectList();
		
		if(chatroom != null) {
			mv.addObject("list",chatroom);
			mv.setViewName("chat/chatlist");
		}else {
			mv.addObject("<script> alert('채팅방 생성이 실패했습니다.'); history.back(); </script>");
		}
		
		return mv;
	}
	
	@RequestMapping("chatroomjoin.do")
	public ModelAndView chatroomjoin(ModelAndView mv,HttpSession session,
										@RequestParam(value = "roomnumber", required = false) String roomnumber) {
		 
		chatroom cr = new chatroom();
		cr.setChatroom_no(roomnumber);
		System.out.println("방번호 " + cr);
		mv.addObject("cr", cr).setViewName("chat/chatroomdetail");
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		loginUser.setChatroom_no(roomnumber);
		
		session.setAttribute("chatRoomnumber", loginUser.getChatroom_no());
		
		return mv;
	}

}
