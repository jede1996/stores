package com.store.luna.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date



@Document("extendido_luna_vet")
data class ExtLunaVet(
    @Field("_id")
    val id: String?,
    var nickname: String,
    var contrasenna: String,
    var rol: String,
    @Field("fecha_registro")
    val fechaRegistro: Date?,
    @Field("fecha_modificacion")
    var fechaModificacion: Date?
)


