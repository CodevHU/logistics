package hu.codev.logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

		if (address.getId() != 0)
			throw new IdMustBeEmptyException();

		return addressRepository.save(address);
	}

	public List<Address> getAll() {
		return addressRepository.findAll();
	}

	public Address getById(long id) {

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return address;
	}

	public boolean delete(long id) {

		addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		addressRepository.deleteById(id);

		return true;
	}

}
