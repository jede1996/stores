package com.stores.request

import com.stores.Entities.Precios
import com.stores.Entities.Stock
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

class RequestProducto(
    val id: String?,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val subcategoria:String,
    val estado: String,
    val stock: Stock,
    val precios: Precios,
    val idProveedor: String
)

class RequestConsultaProducto(
    @field:NotNull(message = "USER_ID_REQUERIDO") @field:NotEmpty(message = "USER_ID_REQUERIDO") val id: String?
)