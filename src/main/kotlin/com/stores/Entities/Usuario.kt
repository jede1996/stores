package com.stores.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import kotlin.collections.ArrayList


data class Telefonos(
    @Field("codigo_pais") val codigoPais: String?,
    val telefono: String?,
    val tipo: String?,
)

data class CorreosElectronicos(
    var verificado: Boolean?,
    var direccion: String?,
    val tipo: String?,
)

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

data class Preferencias(
    var terminosCondiciones: Boolean,
    var medioNotificaciones: String,
    var notificaciones: Boolean,
    val tipo: String?,
)


@Document("usaurio")
data class Usuario(
    @Field("_id") val usuario: String?,
    val nombre: String?,
    @Field("apellido_paterno") val apellidoPaterno: String?,
    @Field("apellido_materno") val apellidoMaterno: String?,
    val genero: String?,
    @Field("correo_electronico") var correosElectronicos: ArrayList<CorreosElectronicos>?,
    val telefonos: ArrayList<Telefonos>?,
    val rol: String?,
    @Field("fecha_nacimiento") val fechaNacimiento: String?,
    @Field("pais_nacimiento") val paisNacimiento: String?,
    val domicilios: ArrayList<Domicilios>?,
    @Field("preferencias_notificaciones") var preferenciasNotificaciones: ArrayList<Preferencias?>,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)


data class Roles(
    val administrador: String = "administrador",
    val usuario: String = "usuario",
    val cliente: String = "cliente",
)