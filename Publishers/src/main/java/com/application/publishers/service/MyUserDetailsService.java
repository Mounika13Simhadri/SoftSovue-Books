package com.application.publishers.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.publishers.model.User;
import com.application.publishers.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
        User user = adminRepository.findByEmail(userName);
        		if(user == null) {
        			System.out.println("user not found");
        			throw new UsernameNotFoundException("Email " + userName + " not found");
        		} 
        		else
        		{
        			System.out.println("Login Successfull");
        		}
        		
         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
         getAuthorities(user));
    }

    public static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String userRole = user.getRole();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRole);
        return authorities;
    }
}