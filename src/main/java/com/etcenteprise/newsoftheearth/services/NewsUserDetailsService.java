package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.NewsUserDetails;
import com.etcenteprise.newsoftheearth.entities.User;
import com.etcenteprise.newsoftheearth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component("userdetailsservice")
@Service
public class NewsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return user.map(NewsUserDetails::new).get();
    }
}
