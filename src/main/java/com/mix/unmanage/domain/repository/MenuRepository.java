package com.mix.unmanage.domain.repository;

import java.util.List;

import com.mix.unmanage.domain.entity.Menu;

public interface MenuRepository {

	Menu select(int id);

	List<Menu> list();

}
