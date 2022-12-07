package hu.codev.logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.codev.logistics.exception.IdMustBeEmptyException;
import hu.codev.logistics.model.Address;
import hu.codev.logistics.repository.AddressRepository;
import jakarta.transaction.Transactional;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Transactional
	public Address create(Address address) {
		
		System.out.println(address.getId());
		
		if(address.getId() != 0)
			throw new IdMustBeEmptyException();
		
		return addressRepository.save(address);
	}

	public List<Address> getAll() {
		return addressRepository.findAll();
	}
	
}
