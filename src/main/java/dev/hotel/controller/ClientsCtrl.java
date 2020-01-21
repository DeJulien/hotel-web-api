package dev.hotel.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
@RequestMapping("clients")
public class ClientsCtrl {

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

	@RequestMapping(method = RequestMethod.GET, path = "query")
	public Client[] rechercheNom(@RequestParam("nom") String nomRequeteHttp) {
		return this.clientRepository.findByNom("Pierre");

	}

	@RequestMapping(method = RequestMethod.POST, path = "creer")
	// @ResponseBody
	public Client post(@RequestBody Client clientRecu) {
		Client nouveau = new Client(clientRecu.getNom().toUpperCase(), clientRecu.getPrenoms());
		List<Client> listClient = this.clientRepository.findByNomAndPrenoms(clientRecu.getNom().toUpperCase(),
				clientRecu.getPrenoms());
		if (listClient == null) {
			Client nouveauClient = new Client();
			nouveauClient.setNom(clientRecu.getNom());
			nouveauClient.setPrenoms(clientRecu.getPrenoms());
			this.clientRepository.save(nouveauClient);
		} else {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
			new EntityNotFoundException("Client deja existant");
		}
		return nouveau;
	}

}
