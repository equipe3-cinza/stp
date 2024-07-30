package br.ufg.inf.backend.stp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class StpApplication {

	public static void main(String[] args) {
		SpringApplication.run(StpApplication.class, args);
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String hashedPassword = encoder.encode("admin123");
        //System.err.println("----------------Senha para o primeiro usuário="+hashedPassword);
	}
}
