package com.mix.unmanage.domain.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mix.unmanage.domain.entity.Menu;
import com.mix.unmanage.domain.repository.MenuRepository;
import com.mix.unmanage.persistence.mapper.MenuMapper;

@Repository("menuRepository")
public class MenuRepositoryImpl implements MenuRepository {

	/** 全部菜单 **/
	private List<Menu> menuList;
	private HashMap<Integer, Menu> menuMap;

	@Resource
	private MenuMapper menuMapper;

	@SuppressWarnings("unused")
	@PostConstruct
	@Transactional(readOnly = true)
	private final void postConstruct() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("category", "1");
		menuList = menuMapper.list(parameters);

		menuMap = new HashMap<Integer, Menu>(menuList.size());
		for (Menu menu : menuList) {
			Queue<Menu> fifo = new LinkedList<Menu>();
			fifo.offer(menu);
			do {
				fillSubMenu(fifo);
			} while (fifo.size() > 0);
			menuMap.put(menu.getId(), menu);
		}
	};

	@Override
	public Menu select(int id) {
		return menuMap.get(id);
	}

	@Override
	public List<Menu> list() {
		return menuList;
	}

	/** 使用一个先进先出的队列来加载菜单的子菜单 **/
	private void fillSubMenu(Queue<Menu> fifo) {
		Menu menu = fifo.poll();
		List<Menu> submenus = new ArrayList<Menu>();
		for (Menu m : menuList) {
			if (m.getSuperId() == menu.getId()) {
				submenus.add(m);
				fifo.offer(m);
			}
		}
		menu.setSubMenus(submenus);
	}
}
