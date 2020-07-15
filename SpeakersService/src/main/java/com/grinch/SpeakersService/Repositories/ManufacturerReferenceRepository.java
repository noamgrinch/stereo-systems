package com.grinch.SpeakersService.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grinch.SpeakersService.BusinessLogic.Entites.ManufacturerReference;

public interface ManufacturerReferenceRepository extends JpaRepository<ManufacturerReference,Long>{
	Optional<ManufacturerReference> findByManufacturerId(Long ManufacturerId);
}
