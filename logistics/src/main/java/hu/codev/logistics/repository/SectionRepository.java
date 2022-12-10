package hu.codev.logistics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.Section;
import hu.codev.logistics.model.TransportPlan;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Address> {
	
	@Query("SELECT COUNT(*) FROM Section AS s WHERE transportPlan = :transportPlan AND ( fromMilestone = :milestone OR toMilestone = :milestone ) ")
	Long countByTransportPlanAndFromMilestoneOrToMilestone(TransportPlan transportPlan, Milestone milestone);

	@Query("SELECT s FROM Section AS s WHERE transportPlan = :transportPlan AND ( fromMilestone = :milestone OR toMilestone = :milestone ) ")
	Section findByTransportPlanAndFromMilestoneOrToMilestone(TransportPlan transportPlan,
			Milestone milestone);

	Optional<Section> findByTransportPlanAndNumber(TransportPlan transportPlan, long l);
}
