package com.store.luna.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import kotlin.collections.ArrayList

@Document("proveedor")
data class Proveedor(
    @Field("_id")
    val id: String?,
    val nombre: String?,
    @Field("apellido_paterno")
    val apellidoPaterno: String?,
    @Field("apellido_materno")
    val apellidoMaterno: String?,
    val genero: String?,
    @Field("correo_electronico")
    var correosElectronicos: ArrayList<CorreosElectronicos>?,
    val telefonos: ArrayList<Telefonos>?,
    @Field("fecha_registro")
    val fechaRegistro: Date?,
    @Field("fecha_modificacion")
    var fechaModificacion: Date?
)
