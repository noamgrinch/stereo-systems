package com.grinch.ElementsService.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grinch.ElementsService.BusinessLogic.Entites.Element;

@Repository
public interface ElementsRepository extends JpaRepository<Element,Long>{
	Optional<Element> findByNameAndManufacturerReference_Id(String name, Long id);
	List<Element> findByManufacturerReference_Id(Long id);
}
