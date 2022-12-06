package hu.codev.logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.repository.AddressRepository;
import jakarta.transaction.Transactional;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Transactional
	public Address create(Address address) {
		return addressRepository.save(address);
	}
	
}
