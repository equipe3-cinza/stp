package br.ufg.inf.backend.stp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return user;
    }

	public List<User> listar() {
		return userRepository.findAll();
	}

	public User salvar(User user) {
		return userRepository.save(user);
	}

	public User salvar(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	public User obter(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		userRepository.deleteById(id);
	}
}