/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;


import LengBD.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suscripcion")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("suscripciones", suscripcionService.listarSuscripciones());
        model.addAttribute("planes", suscripcionService.listarPlanes());
        model.addAttribute("pagos", suscripcionService.listarPagos());
        model.addAttribute("facturas", suscripcionService.listarFacturas());
        model.addAttribute("metodos", suscripcionService.listarMetodosPago());
        return "suscripcion_pagos/listado";
    }
}
