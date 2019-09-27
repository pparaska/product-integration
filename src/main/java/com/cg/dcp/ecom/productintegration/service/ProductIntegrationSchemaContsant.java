package com.cg.dcp.ecom.productintegration.service;

public enum ProductIntegrationSchemaContsant {

	CATEGORY_ID("categoryId"),
	ASSOCIATIONS("associations"),
	FEATURES("features"),
	PRODUCT_ASSOCIATIONS("productAssociations"),
	PRODUCT_FEATURES("productFeatures"),
	PRODUCT_ASSOCIATION_FIELDS("associationFields"), 
	PRODUCT_FEATURE_FIELDS("featureFields"),
	PRODUCT_ASSOCIATION_TYPE_FIELDS("associationTypeFields"),
	PRODUCT_ID("productIds"),
	MODIFIED_DATE("modifiedDate"),
	FROM_DATE("fromDate"),
	ALLERGEN_TYPE("allergen_type"),
	ALLERGEN_RISK_LEVEL("allergen_risk_level"),
	PER_100_G("per_100g"),
	PER_SERVING("per_serving");
	
	private String fieldName;

	ProductIntegrationSchemaContsant(String fieldName){
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return this.fieldName;
	}
}
