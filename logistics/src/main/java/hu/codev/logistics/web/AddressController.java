package hu.codev.logistics.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.codev.logistics.dto.AddressDTO;
import hu.codev.logistics.mapper.AddressMapper;
import hu.codev.logistics.model.Address;
import hu.codev.logistics.service.AddressService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressMapper addressMapper;
	
	
	@GetMapping
	public List<AddressDTO> getAll(){
		return addressMapper.addressesToDtos(addressService.getAll());
	}

	@PostMapping
	public AddressDTO create(@Valid @RequestBody AddressDTO address){
		
		Address newAddress = addressService.create(addressMapper.dtoToAddress(address));
		
		return addressMapper.addressToDto(newAddress);
	}
	
}
