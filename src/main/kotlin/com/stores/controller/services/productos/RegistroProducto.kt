package com.stores.controller.services.productos

import com.stores.config.*
import com.stores.entities.Producto
import com.stores.repository.ProductoRepository
import com.stores.request.RequestProducto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistroProducto  @Autowired constructor(
    private val tracer : ServiceInterceptor,
    private val productoRepository: ProductoRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroInventario(request: RequestProducto): ResponseEntity<Any>{
        var registroNuevo = false
        val idProducto = cifrado(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de productos: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            var productoConsultado = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): Optional<Producto> {
                return productoRepository.findByNombre(cifrado(request.nombre))
            })

            if (productoConsultado.isPresent) return buildresponse(error = CatalogoResponses.PRODUCTO_EXISTENTE)

            registroNuevo = true
            registraProducto(idProducto, request)
            productoConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Producto> {
                return productoRepository.findById(idProducto)
            })

            val respuesta: Producto = tracer.duration(Servicios().preparacionRespuesta, fun(): Producto {
                return productoConsultado.get()
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            if (registroNuevo) {
                anulaRegistro(idProducto)
            }
            logs.error("Error al realizar la peticion: ${e.message}")
            if (e.message!!.contains("duplicate key error collection")) {
                return buildresponse(error = CatalogoResponses.VALOR_EXISTENTE, detalle = regresaLlaveDuplicada(e))
            }
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun registraProducto(idProducto: String, request: RequestProducto) {
        tracer.duration(Servicios().registroUsuario, fun() {
            productoRepository.save(
                Producto(
                    idProducto,
                    cifrado(request.nombre),
                    cifrado(request.descripcion),
                    cifrado(request.categoria),
                    cifrado(request.subcategoria),
                    cifrado(request.estado),
                    request.stock,
                    request.precios,
                    cifrado(request.proveedor),
                    Date(),
                    Date()
                )
            )
        })
    }

    fun anulaRegistro(idUser: String) {
        tracer.duration(Servicios().anulaRegistro, fun() {
            productoRepository.deleteById(idUser)
        })
    }
}