package com.grinch.ReceiversService.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grinch.ReceiversService.BusinessLogic.Entities.Receiver;
@Repository
public interface ReceiversRepository extends JpaRepository<Receiver,Long>{
	Optional<Receiver> findByNameAndManufacturerReference_Id(String name, Long id);
}
