package hu.codev.logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.codev.logistics.dto.DelayDTO;
import hu.codev.logistics.model.TransportPlan;
import hu.codev.logistics.repository.MilestoneRepository;
import hu.codev.logistics.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;

@Service
public class TransportPlanService {
	
	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Transactional
	public TransportPlan delay(long id, DelayDTO delay) {
		
		transportPlanRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		milestoneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		
		
		return null;
	}

}
