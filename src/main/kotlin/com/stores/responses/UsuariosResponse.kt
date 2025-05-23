package com.stores.responses

import com.stores.config.Roles
import com.stores.config.decrypt
import com.stores.entities.Usuario

data class ResponseUsuaro (
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val genero: String,
    val fechaNacimiento: String,
    val aplicacion: String,
    val correoElectronico: String,
    val telefono: String,
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
        decrypt(usaurio.nombre),
        decrypt(usaurio.apellidoPaterno),
        decrypt(usaurio.apellidoMaterno),
        decrypt(usaurio.genero),
        decrypt(usaurio.fechaNacimiento),
        decrypt(usaurio.aplicacion),
        decrypt(usaurio.correo?.direccion),
        decrypt(usaurio.telefono.telefono),
        extendidos
    )
}
