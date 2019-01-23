package com.cloud.staff.zuul.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.staff.zuul.model.TokenUser;


@Service("userDetailsService")
public class UserTokenDetail implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		List<String> roles = new ArrayList<String>();
		return new TokenUser("InternetHospital", "InternetHospital", roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

}
