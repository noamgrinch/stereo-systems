package com.grinch.SpeakersService.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
@Repository
public interface SpeakersRepository extends JpaRepository<Speaker,Long>{
	Optional<Speaker> findByName(String name);
	Optional<Speaker> findByNameAndManufacturerReference_Id(String name,Long id);
}
