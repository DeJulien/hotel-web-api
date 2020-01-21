package dev.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Client[] findByNom(String nom);

	List<Client> findByNomAndPrenoms(String nom, String prenoms);

}
