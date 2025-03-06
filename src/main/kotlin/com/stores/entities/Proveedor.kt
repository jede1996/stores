package com.stores.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("proveedor")
data class Proveedor(
    @Field("_id") val proveedor: String,
    var empresa: String?,
    var nombre: String?,
    @Field("apellido_paterno") var apellidoPaterno: String?,
    @Field("apellido_materno") var apellidoMaterno: String?,
    @Field("correo_electronico") var correo: String?,
    var telefono: String,
    var aplicacion: String,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?
)
