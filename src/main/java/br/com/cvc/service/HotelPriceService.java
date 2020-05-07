package br.com.cvc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cvc.dto.HotelDTO;
import br.com.cvc.dto.PriceDetailDTO;
import br.com.cvc.dto.RoomsDTO;
import br.com.cvc.model.Hotel;
import br.com.cvc.model.Travel;

@Service
public class HotelPriceService {

	private static final double txComissao = 0.70;
	
	private Integer adults = 1;
	
	private Integer childs = 1;
	
	private Long diffDays = 1l;
	
	private Travel travel;
	
	public HotelPriceService() {
	}

	public HotelPriceService(Travel pTravel) {
		travel = pTravel;
		
		if (pTravel.getAdults() != null)
			adults = pTravel.getAdults();
		
		if (pTravel.getChilds() != null)
			childs = pTravel.getChilds();
	
		if (pTravel.getCheckin() != null && pTravel.getCheckout() != null)
			diffDays = calcularDiferencaEmDias(pTravel.getCheckin(), pTravel.getCheckout());
	}

	public List<HotelDTO> calculaHoteis() {
		List<HotelDTO> hotels = new ArrayList<HotelDTO>();

		travel.getHotels().forEach(hotel -> {
			hotels.add(calculaHotel(hotel));
		});

		return hotels;
	}

	public HotelDTO calculaHotel(Hotel hotel) {
		HotelDTO hotelDTO = new HotelDTO();
		hotelDTO.setId(hotel.getId());
		hotelDTO.setCityName(hotel.getCityName());

		List<RoomsDTO> rooms = new ArrayList<RoomsDTO>();
		calculaPrice(hotel, rooms);

		hotelDTO.setRooms(rooms);

		return hotelDTO;
	}

	private void calculaPrice(Hotel hotel, List<RoomsDTO> rooms) {
		hotel.getRooms().forEach(room -> {
			RoomsDTO roomsDTO = new RoomsDTO();
			roomsDTO.setRoomId(room.getRoomID());
			roomsDTO.setCategoryName(room.getCategoryName());

			PriceDetailDTO priceDetailDTO = new PriceDetailDTO();
			priceDetailDTO.setPricePerDayAdult(new BigDecimal(room.getPrice().getAdult().doubleValue() * diffDays)
					.setScale(2, RoundingMode.HALF_EVEN));
			priceDetailDTO.setPricePerDayChild(new BigDecimal(room.getPrice().getChild().doubleValue() * diffDays)
					.setScale(2, RoundingMode.HALF_EVEN));

			roomsDTO.setPriceDetail(priceDetailDTO);

			BigDecimal priceKickbackAdult = new BigDecimal(
					(priceDetailDTO.getPricePerDayAdult().doubleValue() * adults) / txComissao);
			BigDecimal priceKickbackChilds = new BigDecimal(
					(priceDetailDTO.getPricePerDayChild().doubleValue() * childs) / txComissao);

			roomsDTO.setTotalPrice(priceKickbackAdult.add(priceKickbackChilds).setScale(2, RoundingMode.HALF_EVEN));

			rooms.add(roomsDTO);
		});
	}
	
	
	public static Long calcularDiferencaEmDias(Date dataInicial, Date dataFinal) {
		Long qtdDias = 0L;
		dataInicial = retirarHoraMinutoSegundo(dataInicial);
		dataFinal = retirarHoraMinutoSegundo(dataFinal);
		if (dataInicial != null && dataFinal != null) {
			long differenceMilliSeconds = dataFinal.getTime() - dataInicial.getTime();
			qtdDias = (Long) (differenceMilliSeconds / (1000 * 60 * 60 * 24));

		}

		return qtdDias;
	}
	
	public static Date retirarHoraMinutoSegundo(Date data) {
        Date value = null;
        if (data != null) {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(data);
            value = retirarHoraMinutoSegundo(calendar);
        } else {
            throw new IllegalArgumentException("Recebida data nula.");
        }
        return value;
    }
	
	public static Date retirarHoraMinutoSegundo(Calendar calendar) {
        Date value = null;
        if (calendar != null) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            value = calendar.getTime();
        } else {
            throw new IllegalArgumentException("Recebido calendar nulo.");
        }
        return value;
    }
}
