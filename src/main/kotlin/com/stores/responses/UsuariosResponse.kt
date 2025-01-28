package com.stores.responses

import com.stores.config.decrypt
import com.stores.entities.Usuario

data class ResponseUsuaro(
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val genero: String,
    val rol: String,
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
    var usuario: String,
    var nickname: String,
    var rol: String,
)

fun preparaRespopnseUsuario(usaurio: Usuario, extendidos: Extendidos): ResponseUsuaro {
    return ResponseUsuaro(
        decrypt(usaurio.nombre),
        decrypt(usaurio.apellidoPaterno),
        decrypt(usaurio.apellidoMaterno),
        decrypt(usaurio.genero),
        decrypt(usaurio.rol),
        decrypt(usaurio.fechaNacimiento),
        decrypt(usaurio.aplicacion),
        decrypt(usaurio.correosElectronicos.direccion),
        decrypt(usaurio.telefonos.telefono),
        extendidos
    )
}
