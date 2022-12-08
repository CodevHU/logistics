package hu.codev.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.Section;
import hu.codev.logistics.model.TransportPlan;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Address> {
	
	Long countByTransportPlanAndFromMilestoneOrToMilestone(TransportPlan transportPlan, Milestone fromMilestone, Milestone toMilestone);
}
