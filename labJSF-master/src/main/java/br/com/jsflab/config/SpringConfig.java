package br.com.jsflab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
@ComponentScan(basePackages = "br.com.jsflab", excludeFilters = @Filter(Configuration.class))  
public class SpringConfig extends WebMvcConfigurerAdapter {

}  
