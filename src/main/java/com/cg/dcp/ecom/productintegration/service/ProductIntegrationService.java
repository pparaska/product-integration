package com.cg.dcp.ecom.productintegration.service;

import java.util.Date;
import java.util.List;

import com.cg.dcp.ecom.productintegration.model.ProductIntegrationDocument;

public interface ProductIntegrationService {

	public List<String> getProductIdsByModifiedDate(Date fromDate);
	
	public List<ProductIntegrationDocument> getProductsByProductIds(List<String> productIds);

}
