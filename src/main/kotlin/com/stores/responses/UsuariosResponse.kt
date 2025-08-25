package com.stores.responses

import com.stores.config.Roles
import com.stores.config.cifrado
import com.stores.entities.Usuario

data class ResponseUsuaro (
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val genero: String,
    val fechaNacimiento: String,
    val extendidos: Extendidos?,
)

data class Extendidos(
    val lunaVet: ExtendidosRespuesta?,
    val safariVet: ExtendidosRespuesta?,
    val laCamaDelPerro: ExtendidosRespuesta?,
)

data class ExtendidosRespuesta(
    var nickname: String,
    var rol: Roles,
)

fun preparaResponseUsuario(usaurio: Usuario, extendidos: Extendidos): ResponseUsuaro  {
    return ResponseUsuaro(
        cifrado(usaurio.nombre, false),
        cifrado(usaurio.apellidoPaterno, false),
        cifrado(usaurio.apellidoMaterno, false),
        cifrado(usaurio.genero, false),
        cifrado(usaurio.fechaNacimiento, false),
        extendidos
    )
}
