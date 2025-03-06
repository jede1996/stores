package com.stores.repository

import com.stores.entities.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface ConsultasRepository : MongoRepository<Consultas, String>
interface ExtLunaVetRepository : MongoRepository<ExtLunaVet, String>
interface ExtSafariVetRepository : MongoRepository<ExtSafariVet, String>
interface ExtCamaDelPerroRepository : MongoRepository<ExtCamaDelPerro, String>
interface ProductoRepository : MongoRepository<Producto, String>
interface MascotaRepository : MongoRepository<Mascota, String>
interface ProveedorRepository : MongoRepository<Proveedor, String>
interface ClienteRepository : MongoRepository<Usuario, String> {
    @Query("{ nombre: ?0, apellido_paterno: ?1, apellido_materno: ?2, fecha_nacimiento: ?3 }")
    fun findByBatosBasicos(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        fechaNacimiento: String,
    ): Optional<Usuario>

    @Query(
        value = "{ aplicacion: ?0 }",
        fields = "{  'nombre': 1, 'apellido_paterno': 1, 'apellido_materno': 1, 'telefono.telefono': 1, 'correo.direccion': 1 }"
    )
    fun findByAllUsers(aplicacion: String): List<UsersConsultado>
}

interface OpinionesRepository : MongoRepository<Opiniones, String>
interface NotificacionesRepository : MongoRepository<Notificaciones, String>