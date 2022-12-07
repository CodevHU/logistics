package hu.codev.logistics.service;

import org.springframework.data.jpa.domain.Specification;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Address_;

public class AddressSpecifications {

	public static Specification<Address> hasCity(String city) {
		return  (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.CITY)), city.toLowerCase() + "%");
	}
	
	public static Specification<Address> hasStreet(String street) {
		return  (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.STREET)), street.toLowerCase() + "%");
	}

	public static Specification<Address> hasCountry(String country) {
		return  (root, cq, cb) -> cb.equal(root.get(Address_.ISO_CODE), country);
	}
	
	public static Specification<Address> hasPostalCode(String postalCode) {
		return  (root, cq, cb) -> cb.equal(root.get(Address_.POST_CODE), postalCode);
	}

}
