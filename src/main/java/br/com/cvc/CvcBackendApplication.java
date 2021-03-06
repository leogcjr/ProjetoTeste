package br.com.cvc;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CvcBackendApplication {
    
    @PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }
    
	public static void main(String[] args) {
		SpringApplication.run(CvcBackendApplication.class, args);
	}

}
