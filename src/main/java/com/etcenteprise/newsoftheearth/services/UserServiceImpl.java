package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.Role;
import com.etcenteprise.newsoftheearth.entities.User;
import com.etcenteprise.newsoftheearth.entities.UserVerificationToken;
import com.etcenteprise.newsoftheearth.repositories.RoleRepository;
import com.etcenteprise.newsoftheearth.repositories.UserRepository;
import com.etcenteprise.newsoftheearth.repositories.UserVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserVerificationTokenRepository userVerificationTokenRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(user.getPassword());
        user.setCreated(new java.util.Date());
        user.setUpdated(new java.util.Date());
        user.setUserStatus(false);
        List<Role> role = roleRepository.findAll();
        Set<Role> uRole = new HashSet();
        for (Role userRole : role) {
            if (userRole.getRole().equals("ADMIN")) {
                uRole.add(userRole);
            }
        }
        user.setRoles(uRole);
        userRepository.save(user);

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean verifyingUser(User user, String token) {
        Long id = user.getId();
        UserVerificationToken tokenDetails = userVerificationTokenRepository.findByUserId(user);
        if (tokenDetails.getToken().equals(token)) {
            Query updateUser = entityManager.createQuery("update User set userStatus=true where id=:id");
            updateUser.setParameter("id", id);
            updateUser.executeUpdate();
            return true;
        }
        return false;
    }

}
