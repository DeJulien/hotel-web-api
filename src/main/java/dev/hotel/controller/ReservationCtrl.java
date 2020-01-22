package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationJson;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
public class ReservationCtrl {
	private static final Logger LOG = LoggerFactory.getLogger(ReservationCtrl.class);

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public ReservationCtrl(ReservationRepository reservationRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	@RequestMapping(method = RequestMethod.POST, path = "reservations")
	// @ResponseBody
	public ResponseEntity<String> post(@RequestBody ReservationJson reservationRecu) {

		Client client = new Client();
		Reservation reservation = new Reservation();
		List<Chambre> chambres = new ArrayList<>();

		if (clientRepository.findById(reservationRecu.getClientId()).isPresent()) {

			client = clientRepository.findById(reservationRecu.getClientId()).get();

			reservation.setClient(client);

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client non existant");

		}

		for (UUID chambre : reservationRecu.getChambres()) {

			if (chambreRepository.findById(chambre).isPresent()) {
				Chambre ch = this.chambreRepository.findById(chambre).get();
				chambres.add(ch);
			}
		}

		if (!chambres.isEmpty()) {
			LOG.info("");
			reservation.setDateDebut(reservationRecu.getDateDebut());
			reservation.setDateFin(reservationRecu.getDateFin());
			reservation.setChambres(chambres);
			this.reservationRepository.save(reservation);
			return ResponseEntity.status(HttpStatus.CREATED).body("Reservation creer");
		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chambre non trouve");
		}

	}

}
