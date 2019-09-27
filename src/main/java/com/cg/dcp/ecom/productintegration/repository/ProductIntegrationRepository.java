package com.cg.dcp.ecom.productintegration.repository;

import java.util.Date;
import java.util.List;

import com.cg.dcp.ecom.productintegration.model.ProductIntegrationDocument;
import com.cg.dcp.productcategory.model.ProductCategory;

public interface ProductIntegrationRepository {

	public List<String> getModifiedProductIds(Date fromDate);

	public List<ProductIntegrationDocument> getProductsByProductIds(List<String> productIds);

	public ProductCategory getProductCategoryById(String categoryId);

}
