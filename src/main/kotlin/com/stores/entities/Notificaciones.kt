package com.stores.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document("notificaciones")
data class Notificaciones(
    @Field("_id") val notificacion: String?,
    val aplicacion: String?,
    val asunto: String?,
    val mensaje: String?,
    val programacion: Programacion,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)

data class Programacion(
    val tipo: String,
    val frecuencia: String,
    val intervalo: Int,
    val fechaInicio: Date,
    val fechaFin: Date,
)

enum class Emisores { Usaurio, Sistema }

enum class PeriodoRepeticion { Dia, Mes }
