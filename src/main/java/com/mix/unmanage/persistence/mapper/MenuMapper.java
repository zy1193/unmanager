package com.mix.unmanage.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.mix.unmanage.domain.entity.Menu;

public interface MenuMapper {

	List<Menu> list(Map<String, Object> map);
}
