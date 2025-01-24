package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.CorreosElectronicos
import com.stores.entities.Telefonos
import com.stores.entities.Usuario
import com.stores.repository.ClienteRepository
import com.stores.request.RequestsRegistroUsuario
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.Date
import java.util.Optional
import java.util.UUID

@Service
class RegistroUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroUsuario(
        @Valid @RequestBody request: RequestsRegistroUsuario?,
        clienteRepository: ClienteRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de registro de usuarios: ${request!!}")

            val consultado = tracer.duration(Servicios().consultaDatosBasicos, fun(): Usuario? {
                return clienteRepository.findByBatosBasicos(
                    request.nombre, request.apellidoPaterno, request.apellidoMaterno, request.fechaNacimiento!!
                )
            })

            if (consultado != null) return buildresponse(error = CatalogoResponses.USUARIO_EXISTENTE)

            val telefono = tracer.duration(Servicios().consultaTelefono, fun(): Usuario? {
                return clienteRepository.findByTelefono(request.telefono!!)
            })

            if (telefono != null) return buildresponse(error = CatalogoResponses.TELEFONO_EXISTENTE)

            val correo = tracer.duration(Servicios().consultaCorreo, fun(): Usuario? {
                return clienteRepository.findByCorreo(request.correo!!)
            })

            if (correo != null) return buildresponse(error = CatalogoResponses.CORREO_EXISTENTE)

            val cliente = Usuario(
                UUID.randomUUID().toString().replace("-", ""),
                request.nombre,
                request.apellidoPaterno,
                request.apellidoMaterno,
                request.genero,
                arrayListOf(CorreosElectronicos(false, request.correo)),
                arrayListOf(Telefonos(false, request.telefono)),
                request.rol,
                request.fechaNacimiento,
                null,
                null,
                request.notificaciones,
                Date(),
                Date()
            )

            tracer.duration("registro", fun() {
                clienteRepository.save(cliente)
            })

            val consultarRegistro = tracer.duration(Servicios().consultaId, fun(): Optional<Usuario> {
                return clienteRepository.findById(cliente.usuario!!)
            })
            return buildresponse(respuesta = consultarRegistro)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}
