package com.cg.dcp.ecom.productintegration.controller.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.cg.dcp.common.handler.ServiceResponse;
import com.cg.dcp.ecom.productintegration.model.ProductIntegrationDocument;

import lombok.Getter;

@Getter
public class GetProductsIntegrationResonse extends ServiceResponse{

	List<ProductIntegrationDocument> products;
	
	public GetProductsIntegrationResonse(List<ProductIntegrationDocument> products) {
		super(HttpStatus.OK);
		this.products = products;
	}

	
}
