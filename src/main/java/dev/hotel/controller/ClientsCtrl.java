package dev.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController

public class ClientsCtrl {
	private static final Logger LOG = LoggerFactory.getLogger(ClientsCtrl.class);
	private ClientRepository clientRepository;

	public ClientsCtrl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@RequestMapping(method = RequestMethod.GET, path = "lClients")
	public List<Client> retournePlusieursEmployes() {
		List<Client> listClient = clientRepository.findAll();
		return listClient;

	}

	@RequestMapping(method = RequestMethod.GET, path = "clients")
	public Client[] rechercheNom(@RequestParam("nom") String nomRequeteHttp) {
		return this.clientRepository.findByNom("Pierre");

	}

	@RequestMapping(method = RequestMethod.POST, path = "clients")
	// @ResponseBody
	public ResponseEntity<String> post(@RequestBody Client clientRecu) {

		Optional<Client> clients = this.clientRepository.findByNomAndPrenoms(clientRecu.getNom().toUpperCase(),
				clientRecu.getPrenoms());
		if (!clients.isPresent()) {
			LOG.info("Client Enregistré");
			Client nouveauClient = new Client();
			nouveauClient.setNom(clientRecu.getNom());
			nouveauClient.setPrenoms(clientRecu.getPrenoms());
			this.clientRepository.save(nouveauClient);
			return ResponseEntity.status(HttpStatus.CREATED).body("Client creer");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client possedant deja le meme nom prenoms");
		}

	}

}
