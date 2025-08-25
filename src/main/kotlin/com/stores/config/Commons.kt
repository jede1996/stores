package com.stores.config

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

private val restoredSecretKey =
    SecretKeySpec(Base64.getDecoder().decode("CHF3bGpaxKP66dknejpAXXhiZO8+q2bXcu7XnS29SGo="), "AES")
private val restoredIv = Base64.getDecoder().decode("CHF3bGpaxKP66dknejpAXXhiZO8+q2bXcu7XnS29SGo=")
val mongoString = cifrado("eVkbyAoGgInqLXi1t2FBvHMSMfR+E7N9/rX+AQHsW9W7afomHxFvIHDo2MMA3Zo3pu4k8FVNhY7i4t0mcKzLjPA=", false)


fun cifrado(string: String?, cifrado: Boolean = true): String {
    if(string.isNullOrEmpty()) return ""
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
     return if(cifrado){
        cipher.init(Cipher.ENCRYPT_MODE, restoredSecretKey, GCMParameterSpec(128, restoredIv))
         Base64.getEncoder().encodeToString(cipher.doFinal(string.toByteArray()))
    }else{
        cipher.init(Cipher.DECRYPT_MODE, restoredSecretKey, GCMParameterSpec(128, restoredIv))
         String(cipher.doFinal(Base64.getDecoder().decode(string)))
    }
}

enum class Aplicaciones { LunaVet, SafariVet, LaCamaDelPerro }

enum class Generos { Hombre, Mujer, SinGenero }

enum class Roles { Administrador, Usuario, Cliente, Medico }

fun validaAplicaiones(app: String): Boolean {
    return listOf(
        Aplicaciones.LunaVet.name, Aplicaciones.SafariVet.name, Aplicaciones.LaCamaDelPerro.name
    ).contains(app)
}

fun validaRoles(app: String): Boolean {
    return listOf(Roles.Medico.name, Roles.Administrador.name, Roles.Cliente.name).contains(app)
}

data class Servicios(
    val consultaUsuarioId: String = "Consulta de usuario por id",
    val consultaUsuarioDatosBasicos: String = "Consulta por datos basicos",
    val consultaUsuarioTelefono: String = "Consulta por telefono",
    val consultaUsuarioCorreo: String = "Consulta por correo",
    val consultaMascotasPorId: String = "Consulta mascotas por Id",
    val consultaMascotasPorusuario: String = "Consulta mascotas por usuario",
    val consultaProductoPorId: String = "Consulta producto por Id",
    val consultaExtLunaVet: String = "Consulta de extendido para luna vet",
    val consultaExtSafariVet: String = "Consulta de extendido para safari vet",
    val consultaExtCamaDelPerro: String = "Consulta de extendido para cama del perro",

    val registroProducto: String = "Registro de producto",
    val registroUsuario: String = "Registro de usuario",
    val registroFotoMascota: String = "Registro de foto de la mascota",
    val registroMascota: String = "Registro de mascota",
    val registroExtLunaVet: String = "Registro de extendido para luna vet",
    val registroExtSafariVet: String = "Registro  de extendido para safari vet",
    val registroExtCamaDelPerro: String = "Registro de extendido para cama del perro",

    val actualizacionFotoMascota: String = "Actualizacion de foto de la mascota",
    val actualizacionProducto: String = "Actualizacion de producto",
    val actualizacionMascota: String = "Actualizacion de mascota",
    val actualizacionUsuario: String = "Actualizacion de usuario",
    val actualizacionExtLunaVet: String = "Actualizacion de extendido para luna vet",
    val actualizacionExtSafariVet: String = "Actualizacion  de extendido para safari vet",
    val actualizacionExtCamaDelPerro: String = "Actualizacion de extendido para cama del perro",

    val eliminaProducto: String = "Eliminacion de producto",
    val eliminaUsuario: String = "Eliminacion de usuario",
    val eliminaExtLunaVet: String = "Eliminacion de extendido para luna vet",
    val eliminaExtSafariVet: String = "Eliminacion  de extendido para safari vet",
    val eliminaExtCamaDelPerro: String = "Eliminacion de extendido para cama del perro",

    val anulaRegistro: String = "Anulacion de registro",
    val preparacionRespuesta: String = "Preparacion respuesta"
)

fun regresaLlaveDuplicada(e: Exception): String? {
    return Regex("key:\\s*(\\{[^}]*+\\})").find(e.message!!)?.groupValues?.get(1)
}

fun generaLlaveSecreta(): String {
    val secretKeyBytes = ByteArray(32)
    SecureRandom().nextBytes(secretKeyBytes)
    return Base64.getEncoder().encodeToString(secretKeyBytes)
}