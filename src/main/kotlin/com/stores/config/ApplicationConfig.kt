package com.stores.config

import com.stores.repository.ClienteRepository
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.repository.ExtSafariVetRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.regex.Pattern
import kotlin.jvm.optionals.getOrNull

@Configuration
class ApplicationConfig(
    private val clienteRepository: ClienteRepository,
    private val camaDelPerroRepository: ExtCamaDelPerroRepository,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository
) {

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun daoAuthenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailService())
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        return UserDetailsService { username: String ->
            val userConsultado = when(username.split(Pattern.compile(":"))[1]){
                Aplicaciones.LunaVet.name -> {
                    extLunaVetRepository.findByUserName(username).getOrNull()
                }
                Aplicaciones.SafariVet.name -> {
                    extSafariVetRepository.findByUserName(username).getOrNull()
                }
                Aplicaciones.LaCamaDelPerro.name -> {
                    camaDelPerroRepository.findByUserName(username).getOrNull()
                }
                else -> { null }
            }

            if (userConsultado == null) throw UsernameNotFoundException("User not found")
            userConsultado
        }
    }
}
