package com.spring.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootConfiguration
public class Configuration {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver(){
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(webApplicationContext);
	    templateResolver.setOrder(9);
	    templateResolver.setPrefix("classpath:/templates/");
	    templateResolver.setSuffix("");
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine springTemplateEngine= new SpringTemplateEngine();
	    springTemplateEngine.setTemplateResolver(thymeleafTemplateResolver());
	    springTemplateEngine.setEnableSpringELCompiler(true);
	    return springTemplateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(){
	    final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setViewNames(new String[] {"*.html"});
	    viewResolver.setExcludedViewNames(new String[] {"*.jsp"});
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("UTF-8");
	    return viewResolver;
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver(){
	    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setOrder(10);
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("classpath:/templates/");
	    viewResolver.setSuffix("");
	    viewResolver.setViewNames("*.jsp");
	    return viewResolver;
	}
	
}
