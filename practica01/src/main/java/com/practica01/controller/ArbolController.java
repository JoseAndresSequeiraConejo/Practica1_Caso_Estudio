package com.practica01.controller;

import com.practica01.domain.Arbol;
import com.practica01.service.ArbolService;
import com.practica01.service.impl.FirebaseStorageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/arbol")
public class ArbolController {

    @Autowired
    private ArbolService arbolService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var arboles = arbolService.getArboles(false);
        model.addAttribute("arboles", arboles);
        model.addAttribute("totalArboles", arboles.size());
        return "arbol/listado";
    }

    @GetMapping("/nuevo")
    public String arbolNuevo(Arbol arbol) {
        return "arbol/modifica";
    }

    @PostMapping("/guardar")
    public String arbolGuardar(
            @Valid @ModelAttribute("arbol") Arbol arbol,
            BindingResult result,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            Model model) {

        if (result.hasErrors()) {
            return "arbol/modifica"; // Si hay errores de validación, vuelve al formulario
        }

        if (!imagenFile.isEmpty()) {
            arbolService.save(arbol); // Guarda primero para generar el ID
            arbol.setRutaImagen(firebaseStorageService.cargarImagen(imagenFile, "arbol", arbol.getId()));
        }

        arbolService.save(arbol); // Guarda nuevamente con la ruta de imagen
        return "redirect:/arbol/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String arbolEliminar(Arbol arbol) {
        arbolService.delete(arbol);
        return "redirect:/arbol/listado";
    }

    @GetMapping("/modificar/{id}")
    public String arbolModificar(Arbol arbol, Model model) {
        arbol = arbolService.getArbol(arbol);
        model.addAttribute("arbol", arbol);
        return "arbol/modifica";
    }
}
