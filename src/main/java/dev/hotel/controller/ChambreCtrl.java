package dev.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.service.ChambreService;

@RestController
public class ChambreCtrl {

	private ChambreService chambreService;

	public ChambreCtrl(ChambreService chambreService) {
		super();
		this.chambreService = chambreService;

	}

	@GetMapping
	public List<Chambre> retournePlusieursEmployes() {
		return this.chambreService.listerChambre();

	}

}
