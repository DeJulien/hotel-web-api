package dev.hotel.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationJson;
import dev.hotel.service.ReservationService;

@RestController
public class ReservationCtrl {

	private ReservationService reservationService;

	public ReservationCtrl(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.POST, path = "reservations")
	// @ResponseBody
	public ResponseEntity<Reservation> post(@RequestBody @Valid ReservationJson reservationRecu) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.reservationService.creerReservation(reservationRecu.getDateDebut(),
						reservationRecu.getDateFin(), reservationRecu.getChambres(), reservationRecu.getClientId()));

	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<String> reservationPresent(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur " + exception.getMessage());
	}

	@GetMapping
	public List<Reservation> listerResa() {
		return this.reservationService.listerReservations();

	}

}
