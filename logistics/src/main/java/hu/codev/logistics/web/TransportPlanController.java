package hu.codev.logistics.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.codev.logistics.dto.DelayDTO;
import hu.codev.logistics.dto.TransportPlanDTO;
import hu.codev.logistics.mapper.TransportPlanMapper;
import hu.codev.logistics.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportplans")
public class TransportPlanController {

	@Autowired
	TransportPlanService transportPlanService;

	@Autowired
	TransportPlanMapper transportPlanMapper;

	@PostMapping("/{id}/delay")
	public TransportPlanDTO delay(@PathVariable long id, @RequestBody DelayDTO delay) {
		return transportPlanMapper.transportToDto(transportPlanService.delay(id, delay));
	}

}
