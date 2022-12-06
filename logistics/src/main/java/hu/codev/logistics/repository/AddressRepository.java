package hu.codev.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.codev.logistics.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
