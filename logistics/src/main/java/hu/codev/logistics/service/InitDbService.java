package hu.codev.logistics.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.codev.logistics.model.Address;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.Section;
import hu.codev.logistics.model.TransportPlan;
import hu.codev.logistics.repository.AddressRepository;
import hu.codev.logistics.repository.MilestoneRepository;
import hu.codev.logistics.repository.SectionRepository;
import hu.codev.logistics.repository.TransportPlanRepository;

@Service
public class InitDbService{
	

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Transactional
	public void insertTestData() {
				
		Address address1 = addressRepository.save(new Address(0L,"HU","Budapest","Jászberényi út","1106","47/b",47.486210,19.202930));
		Address address2 = addressRepository.save(new Address(0L,"HU","Szigetszentmiklós","Sas utca","2310","20",47.332460,19.031570));
		
		Address address3 = addressRepository.save(new Address(0L,"SK","Petržalka","Jasovská","851 07","3129/21",48.094830,17.113040));
		Address address4 = addressRepository.save(new Address(0L,"HU","Szeged","Budapesti út","6700","8",46.2780859,20.0898026));
		
		Milestone milestone1 = milestoneRepository.save(new Milestone(0L, address1, LocalDateTime.now().plusDays(5)));
		Milestone milestone2 = milestoneRepository.save(new Milestone(0L, address2, LocalDateTime.now().plusDays(6)));
		
		Milestone milestone3 = milestoneRepository.save(new Milestone(0L, address3, LocalDateTime.now().plusDays(7)));
		Milestone milestone4 = milestoneRepository.save(new Milestone(0L, address4, LocalDateTime.now().plusDays(8)));
		
		Milestone milestone5 = milestoneRepository.save(new Milestone(0L, address3, LocalDateTime.now().plusDays(7)));
		Milestone milestone6 = milestoneRepository.save(new Milestone(0L, address4, LocalDateTime.now().plusDays(8)));
		
		TransportPlan transport1 = transportPlanRepository.save(new TransportPlan(0L, 380000, null));
		TransportPlan transport2 = transportPlanRepository.save(new TransportPlan(0L, 780000, null));
		
		sectionRepository.save(new Section(0L, milestone1, milestone2, transport1, 0));
		sectionRepository.save(new Section(0L, milestone3, milestone4, transport1, 1));
		sectionRepository.save(new Section(0L, milestone1, milestone4, transport1, 2));
		
		sectionRepository.save(new Section(0L, milestone3, milestone2, transport2, 0));
		sectionRepository.save(new Section(0L, milestone3, milestone1, transport2, 1));
		sectionRepository.save(new Section(0L, milestone2, milestone4, transport2, 2));
		sectionRepository.save(new Section(0L, milestone5, milestone6, transport2, 3));
		
		
	}
	

}