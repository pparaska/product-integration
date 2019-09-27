package com.cg.dcp.ecom.productintegration.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Nutritional implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2906748601353906178L;

	@JsonProperty("nutritional_type")
	private String nutritionalType;
	
	@JsonProperty("per_100g")
	private Integer perHundredGram;
	

	@JsonProperty("per_serving")
	private Integer perServing;
}
