package br.com.cvc.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

	private Date checkin;
	private Date checkout;
	private Integer adults;
	private Integer childs;
	private List<Hotel> hotels;


}
