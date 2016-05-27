package com.springamq.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println("Inspecting beans auto-resolved by Spring");
		String[] beans = context.getBeanDefinitionNames();
		for(String beanName : beans) {
			System.out.println(beanName);
		}
	}
}
