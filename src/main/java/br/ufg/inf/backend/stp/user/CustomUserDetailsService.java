package br.ufg.inf.backend.stp.user;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufg.inf.backend.stp.role.Role;
import br.ufg.inf.backend.stp.role.TypesRole;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	PasswordEncoder encoder;

	public CustomUserDetailsService() {
		this.encoder = new BCryptPasswordEncoder();
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado: " + username);
		}
		return user;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> listar() {
		return userRepository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User salvar(User user) {
		//PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public void initializeAdminUser() {
		User adminUser = userRepository.findByUsername("admin");
		if (adminUser != null) {
			System.out.println("----------Usuario ADMIN já existe no banco de dados!");
			return;
		}
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode("admin123");
		Role role = new Role();
		role.setRole(TypesRole.ROLE_ADMIN);
		User user = new User();
		user.setUsername("admin");
		user.setPassword(hashedPassword);
		user.setEnabled(true);
		user.setRoles(Collections.singleton(role));
		userRepository.save(user);
		System.out.println("----------Usuario ADMIN criado=" + user.getUsername() + ":" + user.getPassword());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User salvar(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User obter(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User obter(String username) {
		return userRepository.findByUsername(username);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remover(Long id) {
		userRepository.deleteById(id);
	}
}