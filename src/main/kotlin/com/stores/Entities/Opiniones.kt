package com.stores.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


@Document("opiniones")
data class Opiniones(
    @Field("_id") val opinion: String?,
    val userId: String?,
    val comentario: String?,
    var aplicacion: String?,
    var calificacion: Int?,
    var estado: Boolean?,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)
