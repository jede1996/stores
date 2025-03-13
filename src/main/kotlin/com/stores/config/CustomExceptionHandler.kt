package com.stores.config

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

data class ErrorResponse(
    val message: String?, val details: List<String?>?,
)

@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest?): ResponseEntity<Any?> {
        val details: MutableList<String?> = ArrayList()
        details.add(ex.localizedMessage)
        val error = ErrorResponse("Server Error", details)
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest,
    ): ResponseEntity<Any>? {
        var errorMessage: String? = ""
        for (error in ex.bindingResult.allErrors) {
            errorMessage = error.defaultMessage.toString()
            break
        }
        return when (errorMessage!!.toString()) {
            "CONSULTA_REQUERIDO" -> buildresponse(error = CatalogoResponses.CONSULTA_REQUERIDO)
            "NICKNAME_REQUERIDO" -> buildresponse(error = CatalogoResponses.NICKNAME_REQUERIDO)
            "TIPO_BLOQUEO_REQUERIDO" -> buildresponse(error = CatalogoResponses.TIPO_BLOQUEO_REQUERIDO)
            "STATUS_BLOQUEO_REQUERIDO" -> buildresponse(error = CatalogoResponses.STATUS_BLOQUEO_REQUERIDO)
            "MASCOTA_REQUERIDO" -> buildresponse(error = CatalogoResponses.MASCOTA_REQUERIDO)
            "PASS_REQUERIDO" -> buildresponse(error = CatalogoResponses.PASS_REQUERIDO)
            "CODIGO_REQUERIDO" -> buildresponse(error = CatalogoResponses.CODIGO_REQUERIDO)
            "MAIL_REQUERIDO" -> buildresponse(error = CatalogoResponses.MAIL_REQUERIDO)
            "APLICACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.APLICACION_REQUERIDO)
            "ASUNTO_REQUERIDO" -> buildresponse(error = CatalogoResponses.ASUNTO_REQUERIDO)
            "MENSAJE_REQUERIDO" -> buildresponse(error = CatalogoResponses.MENSAJE_REQUERIDO)
            "PROGRAMACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.PROGRAMACION_REQUERIDO)
            "USUARIO_REQUERIDO" -> buildresponse(error = CatalogoResponses.USUARIO_REQUERIDO)
            "COMENTARIO_REQUERIDO" -> buildresponse(error = CatalogoResponses.COMENTARIO_REQUERIDO)
            "CALIFICACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.CALIFICACION_REQUERIDO)
            "OPINION_REQUERIDO" -> buildresponse(error = CatalogoResponses.OPINION_REQUERIDO)
            "PRODUCTO_REQUERIDO" -> buildresponse(error = CatalogoResponses.PRODUCTO_REQUERIDO)
            "NOMBRE_REQUERIDO" -> buildresponse(error = CatalogoResponses.NOMBRE_REQUERIDO)
            "AP_REQUERIDO" -> buildresponse(error = CatalogoResponses.AP_REQUERIDO)
            "GENERO_REQUERIDO" -> buildresponse(error = CatalogoResponses.GENERO_REQUERIDO)
            "FECHA_REQUERIDO" -> buildresponse(error = CatalogoResponses.GENERO_REQUERIDO)
            "FN_REQUERIDO" -> buildresponse(error = CatalogoResponses.FN_REQUERIDO)
            "NOTIFICACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.NOTIFICACION_REQUERIDO)
            else -> buildresponse(error = CatalogoResponses.ERROR_INESPERADO)
        }
    }
}
