package com.stores.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date


@Document("extendido_luna_vet")
data class ExtLunaVet(
    @Field("_id") val usuario: String?,
    var nickname: String,
    var contrasenna: String,
    var rol: String,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)

@Document("extendido_cama_del_perro")
data class ExtCamaDelPerro(
    @Field("_id") val usuario: String?,
    var nickname: String,
    var contrasenna: String,
    var rol: String,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)