package com.store.luna.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import kotlin.collections.ArrayList


@Document("mascotas")
data class Mascota(
    @Field("_id")
    val idMascota: String?,
    val idPropietario: String?,
    var nombre: String?,
    var especie: String?,
    var raza: String?,
    var genero: String?,
    var edad: Int?,
    var fechaNacimiento: Int?,
    var caracteristicas: String,
    var esterilizado: Boolean,
    var chip: String,
    var peso: String?,
    var tamanno: String?,
    var vacunas: ArrayList<Vacunas?>?,
    var alergias: ArrayList<String?>?,
    var consultas: ArrayList<Consultas?>?,
    @Field("fecha_registro")
    val fechaRegistro: Date?,
    @Field("fecha_modificacion")
    var fechaModificacion: Date?
)

data class Vacunas(
    var nombre: String,
    var fechaAplicacion: Date
)