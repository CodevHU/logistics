package hu.codev.logistics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.Section;
import hu.codev.logistics.model.TransportPlan;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Address> {
	
	Long countByTransportPlanAndFromMilestoneOrToMilestone(TransportPlan transportPlan, Milestone fromMilestone, Milestone toMilestone);

	Section findByTransportPlanAndFromMilestoneOrToMilestone(TransportPlan transportPlan,
			Milestone milestone, Milestone milestone2);

	Optional<Section> findByTransportPlanAndNumber(TransportPlan transportPlan, long l);
}
