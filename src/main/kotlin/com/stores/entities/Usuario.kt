package com.stores.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

data class Domicilios(
    var calle: String?,
    val colonia: String?,
    @Field("numero_exterior") val numeroExterior: String?,
    @Field("numero_interior") val numeroInterior: String?,
    @Field("codigo_postal") val codigoPostal: String?,
    val poblacion: String?,
    val estado: String?,
    val pais: String?,
    val tipo: String?,
)

@Document("usuario")
data class Usuario(
    @Field("_id") var usuario: String?,
    var nombre: String?,
    @Field("apellido_paterno") var apellidoPaterno: String?,
    @Field("apellido_materno") var apellidoMaterno: String?,
    var genero: String?,
    @Field("correo_electronico") var correo: CorreosElectronicos,
    var telefono: Telefonos,
    var rol: String?,
    @Field("fecha_nacimiento") var fechaNacimiento: String?,
    @Field("pais_nacimiento") val paisNacimiento: String?,
    val domicilios: Domicilios?,
    var aplicacion: String?,
    @Field("preferencias_notificaciones") var notificaciones: Boolean,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)

data class Telefonos(
    var verificado: Boolean?,
    var telefono: String?
)

data class CorreosElectronicos(
    var verificado: Boolean?,
    var direccion: String?
)


data class Roles(
    val administrador: String = "administrador",
    val usuario: String = "usuario",
    val cliente: String = "cliente",
)


data class UsersConsultado(
    val id: String,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    var telefono: String?,
    var correo: String?
)

fun responseUsuarios(users: List<UsersConsultado>): List<UsersConsultado> {
    val regex = """"\w+":\s*"([^"]+)"""".toRegex()
    return users.map { user ->
        user.copy(
            telefono = regex.find(user.telefono!!)?.groups?.get(1)?.value,
            correo = regex.find(user.correo!!)?.groups?.get(1)?.value
        )
    }
}
