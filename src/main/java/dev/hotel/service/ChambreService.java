package dev.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;

@Service
public class ChambreService {

	private ChambreRepository chambreRepository;

	public ChambreService(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;

	}

	@GetMapping
	public List<Chambre> listerChambre() {
		return chambreRepository.findAll();

	}

}
