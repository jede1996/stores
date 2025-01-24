package com.stores.repository

import com.stores.entities.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ConsultasRepository : MongoRepository<Consultas, String>
interface ExtLunaVetRepository : MongoRepository<ExtLunaVet, String>
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
    ): Usuario?

    @Query("{ telefonos.telefono: ?0 }")
    fun findByTelefono(telefono: String): Usuario?

    @Query("{ correosElectronicos.direccion: ?0 }")
    fun findByCorreo(correo: String): Usuario?
}

interface OpinionesRepository : MongoRepository<Opiniones, String>
interface NotificacionesRepository : MongoRepository<Notificaciones, String>