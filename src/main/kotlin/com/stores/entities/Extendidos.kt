package com.stores.entities


import com.stores.config.Roles
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date


@Document("extendido_luna_vet")
data class ExtLunaVet(
    @Field("_id") var usuario: String,
    var usernameLuna: String,
    var passwordLuna: String,
    var rol: Roles,
    @Field("fecha_registro") val fechaRegistro: Date,
    @Field("fecha_modificacion") var fechaModificacion: Date
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String {
        return this.passwordLuna
    }

    override fun getUsername(): String {
        return this.usernameLuna
    }
}

@Document("extendido_safafi_vet")
data class ExtSafariVet(
    @Field("_id") var usuario: String,
    var usernameSafary: String,
    var passwordSafary: String,
    var rol: Roles,
    @Field("fecha_registro") val fechaRegistro: Date,
    @Field("fecha_modificacion") var fechaModificacion: Date,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String {
        return this.passwordSafary
    }

    override fun getUsername(): String {
        return this.usernameSafary
    }
}

@Document("extendido_cama_del_perro")
data class ExtCamaDelPerro(
    @Field("_id") var usuario: String,
    var usernameCamaPerro: String,
    var passwordCamaPerro: String,
    var rol: Roles,
    @Field("fecha_registro") val fechaRegistro: Date,
    @Field("fecha_modificacion") var fechaModificacion: Date,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String {
        return this.passwordCamaPerro
    }

    override fun getUsername(): String {
        return this.usernameCamaPerro
    }
}