package com.mix.unmanage.app.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mix.unmanage.domain.entity.Menu;
import com.mix.unmanage.domain.repository.MenuRepository;

@Controller
@RequestMapping("/*")
public class MenuAct {

	@Resource
	private MenuRepository menuRepository;

	@RequestMapping(value = "menu.act")
	public String menutree(ModelMap map) {
		Menu root = menuRepository.select(-1000);
		map.addAttribute("root", root);
		
		return "home/menu";
	}

}
