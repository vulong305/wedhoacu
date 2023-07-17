package webhoacu.spring.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import webhoacu.spring.service.UserDetailsServiceImpl;
 

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//	        .antMatchers("/users").authenticated()
//        ** để tự động lấy nốt VD: NV, SP, HD, ...
        	.antMatchers("/delete**/**").hasAuthority("Admin")
        	.antMatchers("/showFormForUpdate**/**").hasAnyAuthority("Admin", "Editor")
	        .anyRequest().permitAll()
            .and()
            .formLogin()
            	.permitAll()
	            .loginPage("/login")
	            .usernameParameter("taiKhoan")
	            .defaultSuccessUrl("/page_thongke")
            .and()
            .logout()
            	.logoutUrl("/logout")
            	.permitAll()
            .and()
            .rememberMe().tokenRepository(persistentTokenRepository())
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            ;
    }

	private PersistentTokenRepository persistentTokenRepository() {
		// TODO Auto-generated method stub
		return null;
	}
}