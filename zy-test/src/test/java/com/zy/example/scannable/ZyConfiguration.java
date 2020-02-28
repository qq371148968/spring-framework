package com.zy.example.scannable;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ZyConfiguration {
	@Bean(name = "zyStudent1")
	public Student student(){
		return new Student(11,"jack",22);
	}

	@Component("zhaoying")
	private static class DefaultAndDevProfileAnnotatedComponent {
		static final String BEAN_NAME = "defaultAndDevProfileAnnotatedComponent";
		private int a=100;

		public String toString(){
			return "a="+a+",BEAN_NAME="+BEAN_NAME;
		}
	}

}
