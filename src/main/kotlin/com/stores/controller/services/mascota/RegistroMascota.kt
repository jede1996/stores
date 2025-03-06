package com.stores.controller.services.mascota

import com.stores.config.*
import com.stores.entities.Mascota
import com.stores.entities.Usuario
import com.stores.entities.Vacunas
import com.stores.repository.ClienteRepository
import com.stores.repository.MascotaRepository
import com.stores.request.RequestMascota
import com.stores.responses.Extendidos
import com.stores.responses.ResponseMascota
import com.stores.responses.ResponseUsuaro
import com.stores.responses.preparaResponseUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class RegistroMascota @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val mascotaRepository: MascotaRepository,
    private val clienteRepository: ClienteRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroMascota(request: RequestMascota): ResponseEntity<Respuesta> {
        var registroNuevo = false
        val idMascota = encrypt(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de mascotas: $request")

            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(encrypt(request.idPropietario))
            })

            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)

            val mascotasConsultadas = tracer.duration(Servicios().consultaMascotasPorusuario, fun(): List<Mascota> {
                return mascotaRepository.findByAllMascotasByUser(usuarioConsultado.get().usuario)
            })

            mascotasConsultadas.forEach {
                if (it.nombre == request.nombre) return buildresponse(error = CatalogoResponses.MASCOTA_EXISTENTE)
            }

            registroNuevo = true
            mascotaRepository.save(
                Mascota(
                    idMascota,
                    encrypt(usuarioConsultado.get().usuario),
                    encrypt(request.nombre),
                    encrypt(request.especie),
                    encrypt(request.raza),
                    encrypt(request.genero),
                    request.edad,
                    request.fechaNacimiento,
                    encrypt(request.caracteristicas),
                    request.esterilizado,
                    encrypt(request.chip),
                    encrypt(request.peso),
                    encrypt(request.tamanno),
                    request.vacunas,
                    request.alergias,
                    null,
                    Date(),
                    Date()
                )
            )

            val mascota = tracer.duration(Servicios().consultaMascotasPorId, fun(): Mascota {
                return mascotaRepository.findById(idMascota).get()
            })

            val respuesta: ResponseMascota = tracer.duration(Servicios().preparacionRespuesta, fun(): ResponseMascota {
                return ResponseMascota(
                    mascota.mascota,
                    mascota.propietario,
                    mascota.nombre,
                    mascota.especie,
                    mascota.raza,
                    mascota.genero,
                    mascota.edad,
                    mascota.fechaNacimiento,
                    mascota.caracteristicas,
                    mascota.esterilizado,
                    mascota.chip,
                    mascota.peso,
                    mascota.tamanno,
                    mascota.vacunas,
                    mascota.alergias,
                    mascota.fechaRegistro,
                    mascota.foto
                )
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            if (registroNuevo) {
                anulaRegistro(idMascota)
            }
            logs.error("Error al realizar la peticion: ${e.message}")
            if (e.message!!.contains("duplicate key error collection")) {
                return buildresponse(error = CatalogoResponses.VALOR_EXISTENTE, detalle = regresaLlaveDuplicada(e))
            }
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun anulaRegistro(idUser: String) {
        tracer.duration(Servicios().anulaRegistro, fun() {
            mascotaRepository.deleteById(idUser)
        })
    }

}