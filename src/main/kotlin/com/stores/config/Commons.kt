package com.stores.config

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

private val restoredSecretKey =
    SecretKeySpec(Base64.getDecoder().decode("CHF3bGpaxKP66dknejpAXXhiZO8+q2bXcu7XnS29SGo="), "AES")
private val restoredIv = Base64.getDecoder().decode("CHF3bGpaxKP66dknejpAXXhiZO8+q2bXcu7XnS29SGo=")
val mongoString = decrypt("eVkbyAoGgInqLXi1t2FBvHMSMfR+E7N9/rX+AQHsW9W7afomHxFvIHDo2MMA3Zo3pu4k8FVNhY7i4t0mcKzLjPA=")

fun encrypt(data: String?): String {
    if(data.isNullOrEmpty()) return ""
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, restoredSecretKey, GCMParameterSpec(128, restoredIv))
    return Base64.getEncoder().encodeToString(cipher.doFinal(data.toByteArray()))
}

fun decrypt(encryptedData: String?): String {
    if(encryptedData.isNullOrEmpty()) return ""
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.DECRYPT_MODE, restoredSecretKey, GCMParameterSpec(128, restoredIv))
    return String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)))
}

data class Aplicaciones(
    val lunaVet: String = "LunaVet",
    val safariVet: String = "SafariVet",
    val laCamaDelPerro: String = "LaCamaDelPerro",
)


data class Roles(
    val cliente: String = "cliente",
    val medico: String = "medico",
    val administrador: String = "administrador"
)

fun validaAplicaiones(app: String): Boolean {
    return listOf(
        Aplicaciones().lunaVet, Aplicaciones().safariVet, Aplicaciones().laCamaDelPerro
    ).contains(app)
}

fun validaRoles(app: String): Boolean {
    return listOf(
        Roles().medico, Roles().administrador, Roles().cliente
    ).contains(app)
}

data class Servicios(
    val consultaMascotasPorId: String = "Consulta mascotas por Id",
    val consultaMascotasPorusuario: String = "Consulta mascotas por usuario",
    val consultaUsuarioDatosBasicos: String = "Consulta por datos basicos",
    val consultaUsuarioCorreo: String = "Consulta por correo",
    val consultaUsuarioTelefono: String = "Consulta por telefono",

    val consultaUsuarioId: String = "Consulta de usuario por id",
    val consultaExtSafariVet: String = "Consulta de extendido para safari vet",
    val consultaExtLunaVet: String = "Consulta de extendido para luna vet",
    val consultaExtCamaDelPerro: String = "Consulta de extendido para cama del perro",

    val registroUsuario: String = "Registro de usuario",
    val registroFotoMascota: String = "Registro de foto de la mascota",
    val registroMascota: String = "Registro de mascota",
    val registroExtLunaVet: String = "Registro de extendido para luna vet",
    val registroExtSafariVet: String = "Registro  de extendido para safari vet",
    val registroExtCamaDelPerro: String = "Registro de extendido para cama del perro",

    val actualizacionFotoMascota: String = "Actualizacion de foto de la mascota",
    val actualizacionMascota: String = "Actualizacion de mascota",
    val actualizacionUsuario: String = "Actualizacion de usuario",
    val actualizacionExtLunaVet: String = "Actualizacion de extendido para luna vet",
    val actualizacionExtSafariVet: String = "Actualizacion  de extendido para safari vet",
    val actualizacionExtCamaDelPerro: String = "Actualizacion de extendido para cama del perro",

    val eliminaUsuario: String = "Eliminacion de usuario",
    val eliminaExtLunaVet: String = "Eliminacion de extendido para luna vet",
    val eliminaExtSafariVet: String = "Eliminacion  de extendido para safari vet",
    val eliminaExtCamaDelPerro: String = "Eliminacion de extendido para cama del perro",

    val anulaRegistro: String = "Anulacion de registro de usaurio",
    val preparacionRespuesta: String = "Preparacion respuesta"
)

fun regresaLlaveDuplicada(e: Exception): String? {
    return Regex("key:\\s*(\\{[^}]*+\\})").find(e.message!!)?.groupValues?.get(1)
}
