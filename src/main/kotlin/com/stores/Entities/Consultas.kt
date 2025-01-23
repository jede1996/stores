package com.stores.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


@Document("consultas")
data class Consultas(
    @Field("_id") val consulta: String?,
    val usuario: String,
    val cliente: String,
    val mascota: String,
    val motivo: String,
    val sintomas: String,
    val diagnostico: String,
    val tratamiento: String,
    val peso: String,
    val temperatura: String,
    @Field("notas_propietario") val notasPropietario: String,
    @Field("notas_doctor") val notasDoctor: String,
    @Field("procedimiento_realizados") val procedimientosRealizados: String,
    @Field("monto_por_cobrar") val montoPorCobrar: String,
    val pagado: Boolean,
    val agendado: Boolean,
    val duracion: String?,
    @Field("tipo_servicio") val tipoServicio: String,
    @Field("estado_cita") val estadoCita: String,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
)

data class Procedimientos(
    val consulta: String = "consulta",
    val vacunacion: String = "vacunacion",
    val cirugia: String = "cirugia.",
    val desparacitacion: String = "desparacitacion",
)

data class EstadoCita(
    val pendiente: String = "Registrada, pero no confirmada por el veterinario.",
    val confirmada: String = "Aprobada y asignada a un veterinario.",
    val cancelada: String = "Anulada por el propietario o el sistema.",
    val completada: String = "La cita se llevó a cabo.",
)