package com.mix.unmanage.app.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.domain.entity.Menu;
import com.mix.unmanage.domain.repository.MenuRepository;

@Controller
@RequestMapping("/*")
public class TabsFrameAct {

	@Resource
	private MenuRepository menuRepository;

	@RequestMapping(value = "tabsFrame.act")
	public String tabs(ModelMap map, int id) {
		Menu menu = menuRepository.select(id);
		Menu superMenu = menuRepository.select(menu.getSuperId());
		map.addAttribute("menu", menu);
		map.addAttribute("superMenu", superMenu);
		return "home/tabs";
	}

}
