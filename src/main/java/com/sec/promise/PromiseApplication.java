package com.sec.promise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PromiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromiseApplication.class, args);
		Debugger.setValid();
		WebController.signUp(new SignInfo("Kim", "Kim"));
		WebController.signUp(new SignInfo("Lee", "Lee"));
		WebController.signUp(new SignInfo("Park", "Park"));
		WebController.signUp(new SignInfo("Choi", "Choi"));
	}

}
