package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {

	Optional<Hotel> findByNom(String string);

}
