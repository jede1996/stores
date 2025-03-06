package com.stores.responses

import com.stores.entities.Vacunas
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import kotlin.collections.ArrayList

data class ResponseMascota(
    val mascota: String,
    val propietario: String,
    var nombre: String,
    var especie: String,
    var raza: String?,
    var genero: String,
    var edad: Int?,
    var fechaNacimiento: Date?,
    var caracteristicas: String,
    var esterilizado: Boolean,
    var chip: String,
    var peso: String?,
    var tamanno: String?,
    var vacunas: ArrayList<Vacunas?>?,
    var alergias: ArrayList<String?>?,
    val fechaRegistro: Date?,
    val foto: String?
)