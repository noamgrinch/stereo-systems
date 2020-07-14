package com.grinch.SpeakersService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grinch.SpeakersService.BusinessLogic.Entites.ManufacturerReference;

public interface ManufacturerReferenceRepository extends JpaRepository<ManufacturerReference,Long>{

}
