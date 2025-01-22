package com.stores.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*
import kotlin.collections.ArrayList


@Component
class ConfigInterceptor : WebMvcConfigurer {
    @Autowired
    var serviceInterceptor: ServiceInterceptor? = null
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(serviceInterceptor!!)
    }
}

@RequestScope
@Component
class ServiceInterceptor : HandlerInterceptor {

    private val logs = LoggerFactory.getLogger(this::class.java)

    val servicios: ArrayList<String>? = ArrayList()

    var tiempoInicial: Long = 0

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        MDC.put("logid", UUID.randomUUID().toString())
        MDC.put("statusFinal", null)
        MDC.put("llave", "}")
        tiempoInicial = System.currentTimeMillis()
        logs.info("Inicio de petición: ${request.method} ${request.requestURI}")
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest, response: HttpServletResponse, handler: Any, exception: Exception?
    ) {
        MDC.put(
            "statusFinal",
            ", \"Servicios\": ${servicios.toString()}, \"TiempoTotal\": ${(System.currentTimeMillis() - tiempoInicial)}"
        )
        logs.info("Fin de la petición: ${request.method} ${request.requestURI}. [Status ${response.status}]")
    }

    fun <T> duration(servicioName: String, function: () -> T): T {
        val startCheckpoint = System.currentTimeMillis()
        return try {
            function.invoke()
        } finally {
            servicios?.add(" {\"servicio\": \"${servicioName}\",\"Tiempo\": ${(System.currentTimeMillis() - startCheckpoint)}}")
        }
    }
}
