package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
		
	}

//	@Bean
//    public CommandLineRunner run(TestComponent t) throws Exception {
//        return args -> {
//        	t.test();
//        };
//	}


}
