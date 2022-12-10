package hu.codev.logistics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long>, JpaSpecificationExecutor<Address> {

	@Query("SELECT t FROM TransportPlan t WHERE id = :id")
	@EntityGraph(attributePaths = {"sections"})
	Optional<TransportPlan> findPlanWithSections(long id);

}
