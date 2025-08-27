package com.stores.config

import com.stores.repository.ExtCamaDelPerroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SegurityConfig(
    private val camaDelPerroRepository: ExtCamaDelPerroRepository,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    @Autowired private val daoAuthenticationProvider: AuthenticationProvider
) {

    @Bean
    fun segurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors {  }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "swagger", "paths/**", "swagger-ui/**",
                    "auth/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(daoAuthenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("http://localhost:3000")
        config.allowedMethods = listOf("GET", "POST")
        config.allowedHeaders = listOf("*")
        config.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }
}
