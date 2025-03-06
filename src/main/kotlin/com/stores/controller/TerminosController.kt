package com.stores.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("terminos-condiciones")
class TerminosController {

    @GetMapping("veterinaria/luna")
    fun lunaVet(model: Model): String {
        model.addAttribute("telefono", "7442130868").addAttribute(
            "direccion", "Av. Las Torres Etapa 38, coloso ruta axtel, El Coloso, 39810 Acapulco de Juárez, Gro."
        ).addAttribute("nombre", "Luna Vet")
        return "plantillaVeterinaria"
    }

    @GetMapping("veterinaria/safari")
    fun safariVet(model: Model): String {
        model.addAttribute("telefono", "7444557430")
            .addAttribute("direccion", "Peña Blanca, El Coloso, 39810 Acapulco de Juárez, Gro.")
            .addAttribute("nombre", "Safari Vet")
        return "plantillaVeterinaria"
    }

    @GetMapping("veterinaria/perro")
    fun camaDelPerro(model: Model): String {
        model.addAttribute("telefono", "7442130868").addAttribute("direccion", "Coloso avenida las torres etapa 38")
            .addAttribute("nombre", "Luna Vet")
        return "plantillaVeterinaria"
    }
}