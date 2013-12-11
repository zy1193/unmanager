package com.mix.unmanage.app.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class ContentAct {

	@RequestMapping(value = "content.act")
	public String login() {
		return "home/content";
	}
}
