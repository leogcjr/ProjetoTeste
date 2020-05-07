package br.com.cvc.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	private Integer id;
	private String name;
	private String cityName;
	private Integer cityCode;
	private List<Rooms> rooms;

	

}
