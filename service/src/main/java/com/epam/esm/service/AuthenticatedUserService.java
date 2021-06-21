package com.epam.esm.service;
import com.epam.esm.dao.PurchaseRepository;
import com.epam.esm.dao.UserRepository;
import com.epam.esm.dao.entity.Purchase;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.CustomUserDetails;
import com.epam.esm.service.converter.CustomUserDetailsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticatedUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final CustomUserDetailsConverter customUserDetailsConverter;

    @Autowired
    public AuthenticatedUserService(UserRepository userRepository, PurchaseRepository purchaseRepositoryRepository) {
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepositoryRepository;
        customUserDetailsConverter = new CustomUserDetailsConverter();
    }

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(" login  not found"));
       return customUserDetailsConverter.convertTo(user);
    }

    public boolean isAuthUserById(long id){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByLogin(username);
        return user.isPresent()?id==user.get().getUserId():false;
    }
    public boolean isAuthUserByPurchaseId(long purchaseId){
        Optional<Purchase> purchase=purchaseRepository.findById(purchaseId);
        return purchase.isPresent()?isAuthUserById(purchase.get().getUser().getUserId()):false;
    }

}
