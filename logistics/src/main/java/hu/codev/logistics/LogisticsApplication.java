package hu.codev.logistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.codev.logistics.service.InitDbService;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner {
	
	@Autowired
	InitDbService initDBService;

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		initDBService.insertTestData();		
	}

}
