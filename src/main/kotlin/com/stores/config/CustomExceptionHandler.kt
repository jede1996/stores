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
    val message: String?, val details: List<String?>?
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
        ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any>? {
        var errorMessage: String? = ""
        for (error in ex.bindingResult.allErrors) {
            errorMessage = error.defaultMessage.toString()
            break
        }
        return when (errorMessage!!.toString()) {
            "NOMBRE_REQUERIDO" -> buildresponse(descripcion = CatalogoResponses.NOMBRE_REQUERIDO)
            "AP_REQUERIDO" -> buildresponse(descripcion = CatalogoResponses.AP_REQUERIDO)
            "AM_REQUERIDO" -> buildresponse(descripcion = CatalogoResponses.AM_REQUERIDO)
            "FN_REQUERIDO" -> buildresponse(descripcion = CatalogoResponses.FN_REQUERIDO)
            "GENERO_REQUERIDO" -> buildresponse(descripcion = CatalogoResponses.GENERO_REQUERIDO)
            "NOTIFICACION_REQUERIDO" -> buildresponse(descripcion = CatalogoResponses.NOTIFICACION_REQUERIDO)

            else -> buildresponse(descripcion = CatalogoResponses.ERROR_INESPERADO)
        }
    }
}
