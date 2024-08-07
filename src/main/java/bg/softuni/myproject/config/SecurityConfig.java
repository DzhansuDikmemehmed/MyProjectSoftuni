package bg.softuni.myproject.config;


import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.service.impl.FitnessUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->{
                            authorizeRequests
                                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                    .requestMatchers("/", "/users/login","/users/login-error","/users/register", "/about", "/change-language").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin(
                        formLogin->{
                            formLogin.loginPage("/users/login")
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/", true)
                                    .failureUrl("/users/login-error");
                        }

                )
                .logout(
                        logout->{
                            logout
                                    .logoutUrl("/users/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true);


                        }
                )
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

@Bean
    public FitnessUserDetailsService fitnessUserDetailsService(UserRepository userRepository){
        return new FitnessUserDetailsService(userRepository);
}

}
