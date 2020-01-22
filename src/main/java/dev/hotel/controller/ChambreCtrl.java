package dev.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;

@RestController
public class ChambreCtrl {
	private static final Logger LOG = LoggerFactory.getLogger(ChambreCtrl.class);

	private ChambreRepository chambreRepository;

	public ChambreCtrl(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;

	}

	@RequestMapping(method = RequestMethod.GET, path = "chambre")
	public List<Chambre> retournePlusieursEmployes() {
		List<Chambre> listChambre = chambreRepository.findAll();
		return listChambre;

	}

	@RequestMapping(method = RequestMethod.POST, path = "chambre")
	// @ResponseBody
	public ResponseEntity<String> post(@RequestBody Chambre chambreRecu) {

		LOG.info("chambre Enregistré");
		Chambre nouveauChambre = new Chambre();
		nouveauChambre.setHotel(chambreRecu.getHotel());
		nouveauChambre.setNumero(chambreRecu.getNumero());
		nouveauChambre.setSurfaceEnM2(chambreRecu.getSurfaceEnM2());

		this.chambreRepository.save(nouveauChambre);
		return ResponseEntity.status(HttpStatus.CREATED).body("chambre creer");

	}

}
