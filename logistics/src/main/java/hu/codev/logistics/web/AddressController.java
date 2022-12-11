package hu.codev.logistics.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.codev.logistics.dto.AddressDTO;
import hu.codev.logistics.mapper.AddressMapper;
import hu.codev.logistics.model.Address;
import hu.codev.logistics.service.AddressService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;

	@Autowired
	AddressMapper addressMapper;
	
	@PostMapping("/search")
	public List<AddressDTO> searchData(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable, @RequestBody Address search, HttpServletResponse response){
		Page<Address> address = addressService.find(pageable, search);
		
		response.addIntHeader("X-Total-Count", (int) address.getTotalElements());
		
		return addressMapper.pageAddressesToDtos(address);
	}

	@GetMapping
	public List<AddressDTO> getAll() {
		return addressMapper.addressesToDtos(addressService.getAll());
	}

	@GetMapping("/{id}")
	public AddressDTO getById(@PathVariable long id) {
		return addressMapper.addressToDto(addressService.getById(id));
	}

	@PostMapping
	public AddressDTO create(@Valid @RequestBody AddressDTO address) {

		Address newAddress = addressService.create(addressMapper.dtoToAddress(address));

		return addressMapper.addressToDto(newAddress);
	}
	
	@PutMapping("/{id}")
	public AddressDTO update(@PathVariable long id, @Valid @RequestBody AddressDTO address) {
	
		Address updatedAddress = addressService.update(id, addressMapper.dtoToAddress(address));
		
		return addressMapper.addressToDto(updatedAddress);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		addressService.delete(id);
	}

}
