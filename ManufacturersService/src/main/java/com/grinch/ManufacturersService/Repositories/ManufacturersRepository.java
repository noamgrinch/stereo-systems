package com.grinch.ManufacturersService.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;

@Repository
public interface ManufacturersRepository extends JpaRepository<Manufacturer,Long>{
	Optional<Manufacturer> findByName(String name);
}
