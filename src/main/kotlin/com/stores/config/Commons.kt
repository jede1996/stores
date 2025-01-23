package com.stores.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class DetallesResponseError(
    val codigo: String?, val mensaje: String?, val detalle: String?,
)

data class Respuesta(
    val status: Int, val respuesta: Any,
)

enum class ResponseStatus(val status: Int, val httpStatus: HttpStatus) {
    ERROR_INESPERADO(-2, HttpStatus.INTERNAL_SERVER_ERROR), ERROR_PROTOCOLO(
        -1, HttpStatus.INTERNAL_SERVER_ERROR
    ),
    EXITO(0, HttpStatus.OK), PETICION_INCORRECTA(2, HttpStatus.OK),
}

enum class CatalogoResponses(
    val estatus: ResponseStatus, val subcodigo: Int, val mensajeDefault: String,
) {
    NOMBRE_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO, 0, "El nombre no fue enviado"
    ),
    AP_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO, 0, "El apellido paterno no fue enviado"
    ),
    FN_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "La fecha de nacimiento no fue enviado"),
    GENERO_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO, 0, "La genero no fue enviado"
    ),
    NOTIFICACION_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "El estado de notificaciones no fue enviado"),
    BODY_NULL(
        ResponseStatus.ERROR_PROTOCOLO, 0, "El body no fue enviado"
    ),
    ERROR_INESPERADO(ResponseStatus.PETICION_INCORRECTA, 1, "Error inesperado no fue enviado"),
    CONSULTA_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "La consulta no fue enviado"
    ),
    NICKNAME_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El nickname no fue enviado"
    ),
    TIPO_BLOQUEO_REQUERIDO(ResponseStatus.PETICION_INCORRECTA, 0, "El bloqueo no fue enviado"),
    STATUS_BLOQUEO_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El estado no fue enviado"
    ),
    MASCOTA_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "La mascota no fue enviado"
    ),
    PASS_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "La contraseña no fue enviado"
    ),
    CODIGO_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El codigo no fue enviado"
    ),
    MAIL_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El correo no fue enviado"
    ),
    APLICACION_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "La aplicacion no fue enviado"
    ),
    ASUNTO_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El asunto no fue enviado"
    ),
    MENSAJE_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El mensaje no fue enviado"
    ),
    PROGRAMACION_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "la programacion no fue enviado"
    ),
    USUARIO_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El usuario no fue enviado"
    ),
    COMENTARIO_REQUERIDO(ResponseStatus.PETICION_INCORRECTA, 0, " no fue enviado"), CALIFICACION_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "El comentario no fue enviado"
    ),
    OPINION_REQUERIDO(
        ResponseStatus.PETICION_INCORRECTA, 0, "La opinion no fue enviado"
    ),
    PRODUCTO_REQUERIDO(ResponseStatus.PETICION_INCORRECTA, 0, "El producto no fue enviado"),
}

fun buildresponse(
    response: Any? = null,
    descripcion: CatalogoResponses? = null,
    detalle: String = "",
): ResponseEntity<Respuesta> {
    if (response != null) return ResponseEntity(Respuesta(0, response), ResponseStatus.EXITO.httpStatus)
    return ResponseEntity(
        Respuesta(
            descripcion!!.estatus.status, DetallesResponseError(
                "${descripcion.estatus.status}.${descripcion.subcodigo}", descripcion.mensajeDefault, detalle
            )
        ), descripcion.estatus.httpStatus
    )
}
