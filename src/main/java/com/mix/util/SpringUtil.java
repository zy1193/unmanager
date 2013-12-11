package com.mix.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 普通类获取Spring容器定义的bean
 * @author hxk 2010-08-19
 */
public class SpringUtil implements ApplicationContextAware
{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context)
        throws BeansException
    {
        SpringUtil.applicationContext = context;
    }

    public static Object getBean(String name)
    {
        return applicationContext.getBean(name);
    }

}
