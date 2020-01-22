package dev.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Hotel;
import dev.hotel.repository.HotelRepository;

@RestController
public class HotelCtrl {

	private static final Logger LOG = LoggerFactory.getLogger(ClientsCtrl.class);
	private HotelRepository hotelRepository;

	public HotelCtrl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	@RequestMapping(method = RequestMethod.POST, path = "hotel")
	// @ResponseBody
	public ResponseEntity<String> post(@RequestBody Hotel hotelRecu) {

		LOG.info("hotel Enregistré");
		Hotel nouveauHotel = new Hotel();
		nouveauHotel.setNom(hotelRecu.getNom());
		nouveauHotel.setNombreEtoiles(hotelRecu.getNombreEtoiles());
		this.hotelRepository.save(nouveauHotel);
		return ResponseEntity.status(HttpStatus.CREATED).body("hotel creer");

	}

}
