package hu.codev.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long>, JpaSpecificationExecutor<Address> {

	@Query("SELECT COUNT(*) FROM TransportPlan AS t WHERE t.id = ?1 AND t.section IN (SELECT id FROM Section AS s WHERE s.fromMilestone = ?2 OR s.toMilestone = ?2)")
	Long countTransportPlanByMilestone(long id, Milestone milestoneid);

}
