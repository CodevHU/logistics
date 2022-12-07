package hu.codev.logistics.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.codev.logistics.dto.AddressDTO;
import hu.codev.logistics.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	Address dtoToAddress(AddressDTO address);

	AddressDTO addressToDto(Address newAddress);

	List<AddressDTO> addressesToDtos(List<Address> addresses);

}
