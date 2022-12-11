package hu.codev.logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.codev.logistics.exception.DeleteIdNotIssetException;
import hu.codev.logistics.exception.IdMustBeEmptyException;
import hu.codev.logistics.model.Address;
import hu.codev.logistics.repository.AddressRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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

	public void delete(long id) {
		
		addressRepository.findById(id)
				.orElseThrow(() -> new DeleteIdNotIssetException());
		
		addressRepository.deleteById(id);
	}

	@Transactional
	public Address update(long id, @Valid Address address) {

		if (address.getId() != 0 && address.getId() != id)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		address.setId(id);

		return addressRepository.save(address);
	}

	public Page<Address> find(Pageable page, Address search) {

		if (!StringUtils.hasText(search.getCity()) && !StringUtils.hasText(search.getStreet())
				&& !StringUtils.hasText(search.getPostCode()) && !StringUtils.hasText(search.getIsoCode()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		Specification<Address> spec = Specification.where(null);

		if (StringUtils.hasText(search.getCity())) {
			spec = spec.and(AddressSpecifications.hasCity(search.getCity()));
		}

		if (StringUtils.hasText(search.getStreet())) {
			spec = spec.and(AddressSpecifications.hasStreet(search.getStreet()));
		}

		if (StringUtils.hasText(search.getPostCode())) {
			spec = spec.and(AddressSpecifications.hasPostalCode(search.getPostCode()));
		}

		if (StringUtils.hasText(search.getIsoCode())) {
			spec = spec.and(AddressSpecifications.hasCountry(search.getIsoCode()));
		}

		return addressRepository.findAll(spec,page);
	}

}
