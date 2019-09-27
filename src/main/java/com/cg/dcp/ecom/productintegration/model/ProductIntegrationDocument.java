package com.cg.dcp.ecom.productintegration.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cg.dcp.product.model.ProductDocument;
import com.cg.dcp.productcategory.model.ProductCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(ProductIntegrationDocument.COLLECTION_NAME)
public class ProductIntegrationDocument extends ProductDocument {

	public static final String COLLECTION_NAME = "productIntegration";

	//To supress categoryId in the response schema
	
	@ApiModelProperty(value = "Category of the current product.", required = false)
	private ProductCategory category;
	
	private String activeFlag;
	private String updateTime;
	private List<Allergens> allergens;
	private List<Nutritional> nutritionals;
}
