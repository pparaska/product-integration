package com.cg.dcp.ecom.productintegration.model;

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
public class Allergens {

	private String allergenName;
	private String allergenType;
	private String allergenRiskLevel;
}


