package br.com.cvc.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO implements Serializable {


	private static final long serialVersionUID = 3538548116778588811L;
	private Integer id;
	private String cityName;
	private List<RoomsDTO> rooms;

	

}
