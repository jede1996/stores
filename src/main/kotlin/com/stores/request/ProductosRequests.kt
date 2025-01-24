package com.stores.request

import com.stores.Entities.Precios
import com.stores.Entities.Stock
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class RequestProducto(
    @field:NotNull(message = "PRODUCTO_REQUERIDO") @field:NotEmpty(message = "PRODUCTO_REQUERIDO") val producto: String?,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String?,
    val descripcion: String?,
    val categoria: String?,
    val subcategoria:String?,
    val estado: String?,
    val stock: Stock?,
    val precios: Precios?,
    val proveedor: String?
)

class RequestConsultaProducto(
    @field:NotNull(message = "PRODUCTO_REQUERIDO") @field:NotEmpty(message = "PRODUCTO_REQUERIDO") val producto: String?
)