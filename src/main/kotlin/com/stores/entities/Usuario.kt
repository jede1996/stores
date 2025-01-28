package com.stores.entities

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import kotlin.collections.ArrayList


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
    @Field("correo_electronico") var correosElectronicos: CorreosElectronicos,
    val telefonos: Telefonos,
    var rol: String?,
    @Field("fecha_nacimiento") var fechaNacimiento: String?,
    @Field("pais_nacimiento") val paisNacimiento: String?,
    val domicilios: Domicilios?,
    var aplicacion: String?,
    @Field("preferencias_notificaciones") val notificaciones: Boolean,
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