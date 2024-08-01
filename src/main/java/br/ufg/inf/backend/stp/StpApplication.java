package br.ufg.inf.backend.stp;

import br.ufg.inf.backend.stp.user.CustomUserDetailsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StpApplication {

	@Autowired
	private CustomUserDetailsService userService;


	public static void main(String[] args) {
		SpringApplication.run(StpApplication.class, args);
	}

	@PostConstruct
	public void init() {
		userService.initializeAdminUser();
	}
}
