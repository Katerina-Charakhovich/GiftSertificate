package com.epam.esm.service;
import com.epam.esm.dao.UserRepository;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.CustomUserDetails;
import com.epam.esm.service.converter.CustomUserDetailsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CustomUserDetailsConverter customUserDetailsConverter;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        customUserDetailsConverter = new CustomUserDetailsConverter();
    }

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
       return customUserDetailsConverter.convertTo(user);
    }
}
