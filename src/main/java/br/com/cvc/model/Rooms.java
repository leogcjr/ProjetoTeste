package br.com.cvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {

	private Integer roomID;
	private String categoryName;
	private Price price;

	
}
