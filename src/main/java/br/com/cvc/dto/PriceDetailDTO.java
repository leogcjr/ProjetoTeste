package br.com.cvc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetailDTO implements Serializable {

	private static final long serialVersionUID = -359276875739564766L;
	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChild;

	

}
