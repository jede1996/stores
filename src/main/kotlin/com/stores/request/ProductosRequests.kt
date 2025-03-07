package com.stores.request

import com.stores.entities.Precios
import com.stores.entities.Stock
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RequestProducto(
    @field:NotNull(message = "PRODUCTO_REQUERIDO") @field:NotEmpty(message = "PRODUCTO_REQUERIDO") val producto: String,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String,
    val descripcion: String,
    val categoria: String,
    val subcategoria:String,
    val estado: String,
    val stock: Stock,
    val precios: Precios,
    val proveedor: String,
    val aplicacion: String
)

data class RequestConsultaProducto(
    @field:NotNull(message = "PRODUCTO_REQUERIDO") @field:NotEmpty(message = "PRODUCTO_REQUERIDO") val producto: String? = null
)

data class RequestConsulta(
    val aplicacion: String? = null
)