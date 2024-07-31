package br.ufg.inf.backend.stp;

import br.ufg.inf.backend.stp.role.Role;
import br.ufg.inf.backend.stp.role.RoleService;
import br.ufg.inf.backend.stp.role.TypesRole;
import br.ufg.inf.backend.stp.user.CustomUserDetailsService;
import br.ufg.inf.backend.stp.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class StpApplication {

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public static void main(String[] args) {
		SpringApplication.run(StpApplication.class, args);
	}

	@PostConstruct
	public void init() {
		try {
			if (userService.obter("admin").getUsername() == null) {
				return;
			}
		} catch (Exception e) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = "admin123";
			String hashedPassword = passwordEncoder.encode(password);
			Role role = new Role();
			role.setRole(TypesRole.ROLE_ADMIN);
			roleService.salvar(role);
			User user = new User();
			user.setUsername("admin");
			user.setPassword(hashedPassword);
			user.setEnabled(true);
			user.setRoles(Collections.singleton(role));
			userService.salvar(user);

		}
	}
}
