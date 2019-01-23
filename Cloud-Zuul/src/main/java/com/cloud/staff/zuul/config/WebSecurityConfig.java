package com.cloud.staff.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloud.staff.zuul.basic.AuthenticationTokenFilter;
import com.cloud.staff.zuul.basic.EntryPointUnauthorizedHandler;
import com.cloud.staff.zuul.basic.RestAccessDeniedHandler;


/**
 * CSRF保护默认是开启的，可以禁用掉即可(否则服务注册失败)
 *
 * @author ly
 * @Date 2018年11月5日 14点15分
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsService userDetailsService;
    private AuthenticationTokenFilter tokenFilter;
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    private RestAccessDeniedHandler restAccessDeniedHandler;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, AuthenticationTokenFilter authenticationTokenFilter, EntryPointUnauthorizedHandler entryPointUnauthorizedHandler, RestAccessDeniedHandler restAccessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.tokenFilter = authenticationTokenFilter;
        this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
    }
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        //请求放过
        .antMatchers("/*").permitAll()
        //swagger放过
        .antMatchers("/swagger-ui.html","/swagger-resources/configuration/ui","/swagger-resources","/api/v2/api-docs",
        		"/dockhospital/v2/api-docs","/user/v2/api-docs","/Inquiry/v2/api-docs","/reservation/v2/api-docs",
        		"/nursing/v2/api-docs","/pay/v2/api-docs").permitAll()
        
        .anyRequest().authenticated().and().headers().frameOptions().disable();
        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);
    }
}
