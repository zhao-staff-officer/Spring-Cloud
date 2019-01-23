package com.cloud.staff.zuul.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token过滤器
 *
 * @author hackyo
 * Created on 2018/09/05 9:28.
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private static Logger log=LoggerFactory.getLogger(AuthenticationTokenFilter.class);
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    	String managemenToken=request.getHeader("Authorization");
    	String appUserToken=request.getHeader("Authorization");
    	String appDocToken=request.getHeader("Authorization");
    	boolean isAccess=false;
    	if(true) {
    		isAccess=true;
    		UserDetails userDetails = this.userDetailsService.loadUserByUsername(managemenToken);
    		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
    	}
    	log.info("请求路径：{}，是否通过：{}",request.getRequestURI(),isAccess==true?"是":"否");
        chain.doFilter(request, response);
    }
}
