package hu.codev.logistics.mapper;

import org.mapstruct.Mapper;

import hu.codev.logistics.dto.TransportPlanDTO;
import hu.codev.logistics.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	TransportPlanDTO transportToDto(TransportPlan delay);


}
