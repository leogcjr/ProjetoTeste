package br.com.cvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cvc.model.Hotel;

@Service
public class IntegraBrokerService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	public List<Hotel> getHotelsByCodeCity(Integer codeCity) {
		String url = "https://cvcbackendhotel.herokuapp.com/hotels/avail/" + codeCity.toString();
		ResponseEntity<ArrayList<Hotel>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<Hotel>>() {
				});

		return responseEntity.getBody();

	}

	public Hotel hotelById(Integer idHotel) {
		String url = "https://cvcbackendhotel.herokuapp.com/hotels/" + idHotel.toString();
		ResponseEntity<ArrayList<Hotel>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<Hotel>>() {
				});

		return responseEntity.getBody().get(0);
	}
}
