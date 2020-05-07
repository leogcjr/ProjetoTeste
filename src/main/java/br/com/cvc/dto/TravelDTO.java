package br.com.cvc.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelDTO implements Serializable {


	private static final long serialVersionUID = -4795645207265183645L;
	private Integer codeCity;
	private LocalDate checkin;
	private LocalDate checkout;
	private Integer adults;
	private Integer childrens;

	

}
