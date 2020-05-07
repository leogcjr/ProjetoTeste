package br.com.cvc.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.dto.HotelDTO;
import br.com.cvc.model.Hotel;
import br.com.cvc.model.Travel;
import br.com.cvc.service.HotelPriceService;
import br.com.cvc.service.IntegraBrokerService;
import io.swagger.annotations.Api;

//@RestController("/api/cvc")
//@CrossOrigin("*")
@RestController
@Api
@RequestMapping(value = "/api/cvc", produces = "application/json")
public class CvcController {

	@Autowired
	private HotelPriceService priceService;
	
	@Autowired
	private IntegraBrokerService brokerService;
	
	@GetMapping(value = "/hotel/city/{codeCity}")
	@ResponseBody List<HotelDTO> hotelAvails(@PathVariable("codeCity") @Valid Integer codeCity,
			@RequestParam(value = "checkin", required = true) @DateTimeFormat(pattern="MMddyyyy") Date checkin,
			@RequestParam(value = "checkout", required = true) @DateTimeFormat(pattern="MMddyyyy") Date checkout,
			@RequestParam @Valid Integer adults, @RequestParam @Valid Integer childs) {
		
		List<Hotel> hotelsAvails = brokerService.getHotelsByCodeCity(codeCity);
		priceService = new HotelPriceService(new Travel(checkin, checkout, adults, childs, hotelsAvails));
		List<HotelDTO> hotels = priceService.calculaHoteis();
		
		return hotels;
	}
	
	
	@GetMapping(value = "/hotel/{hotelId}")
	ResponseEntity<?> calculate(@PathVariable("hotelId") @Valid Integer hotelId) {

		Hotel hotel = brokerService.hotelById(hotelId);
		HotelDTO hotelDTO = priceService.calculaHotel(hotel);

		if (hotelDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(hotelDTO);
	}

}
