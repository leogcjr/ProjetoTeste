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
public class RoomsDTO implements Serializable {

	private static final long serialVersionUID = 424504788909873526L;
	private Integer roomId;
	private String categoryName;
	private BigDecimal totalPrice;
	private PriceDetailDTO priceDetail;


}
