package hu.codev.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, JpaSpecificationExecutor<Address> {

}
