package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.PushMsgByFCMService;

@Controller
@RequestMapping(path= "/demo")
public class MainController {
	
	@Autowired
	private PushMsgByFCMService pushMsgByFCMService;
	
	@GetMapping(path= "/send")
	@ResponseBody
	public String sendMsg() {
		boolean res = pushMsgByFCMService.sendMessage(null);
		System.out.println(res);
		return res?"success":"fail";
	}
}
