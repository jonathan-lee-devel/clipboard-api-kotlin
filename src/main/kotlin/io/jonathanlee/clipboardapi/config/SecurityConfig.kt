package io.jonathanlee.clipboardapi.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
            ?.authorizeRequests()
            ?.anyRequest()
            ?.authenticated()
            ?.and()
            ?.formLogin()
            ?.loginPage("http://localhost:4200/login")?.permitAll()
            ?.loginProcessingUrl("/api/login")
            ?.and()
            ?.logout()
            ?.permitAll()
            ?.logoutUrl("/api/logout")
            ?.and()
            ?.csrf()
            ?.disable()
    }

}
