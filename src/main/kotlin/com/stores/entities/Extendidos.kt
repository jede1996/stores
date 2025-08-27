package com.stores.entities


import com.stores.config.Roles
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

interface IExtndidos{
    var usuario: String
    var usernameExt: String
    var passwordExt: String
    var rol: Roles
    val fechaRegistro: Date
    var fechaModificacion: Date
}

@Document("extendido_luna_vet")
data class ExtLunaVet(
    @Field("_id") override var usuario: String,
    override var usernameExt: String,
    override var passwordExt: String,
    override var rol: Roles,
    @Field("fecha_registro") override val fechaRegistro: Date,
    @Field("fecha_modificacion") override var fechaModificacion: Date
): UserDetails, IExtndidos {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String {
        return this.passwordExt
    }

    override fun getUsername(): String {
        return this.usernameExt
    }
}

@Document("extendido_safafi_vet")
data class ExtSafariVet(
    @Field("_id") override var usuario: String,
    override var usernameExt: String,
    override var passwordExt: String,
    override var rol: Roles,
    @Field("fecha_registro") override val fechaRegistro: Date,
    @Field("fecha_modificacion") override var fechaModificacion: Date,
): UserDetails, IExtndidos {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String {
        return this.passwordExt
    }

    override fun getUsername(): String {
        return this.usernameExt
    }
}

@Document("extendido_cama_del_perro")
data class ExtCamaDelPerro(
    @Field("_id") override var  usuario: String,
    override var usernameExt: String,
    override var passwordExt: String,
    override var rol: Roles,
    @Field("fecha_registro") override val fechaRegistro: Date,
    @Field("fecha_modificacion") override var fechaModificacion: Date,
): UserDetails, IExtndidos {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String {
        return this.passwordExt
    }

    override fun getUsername(): String {
        return this.usernameExt
    }
}