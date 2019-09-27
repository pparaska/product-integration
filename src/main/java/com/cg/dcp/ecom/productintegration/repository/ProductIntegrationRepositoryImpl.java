package com.cg.dcp.ecom.productintegration.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cg.dcp.common.RequestContext;
import com.cg.dcp.common.repository.SchemaConstants;
import com.cg.dcp.ecom.productintegration.model.ProductIntegrationDocument;
import com.cg.dcp.ecom.productintegration.service.ProductIntegrationSchemaContsant;
import com.cg.dcp.product.model.Product;
import com.cg.dcp.product.model.ProductDocument;
import com.cg.dcp.productcategory.model.ProductCategory;
import com.cg.dcp.productcategory.model.ProductCategoryDocument;

@Repository
public class ProductIntegrationRepositoryImpl implements ProductIntegrationRepository {

	@Autowired
	private MongoTemplate productMongoTemplate;

	@Autowired
	private MongoTemplate productCategoryMongoTemplate;

	@Override
	public List<String> getModifiedProductIds(Date fromDate) {
		// check date validation
		Query query = new Query()
				.addCriteria(Criteria.where(SchemaConstants.TENANT.getFieldName()).is(RequestContext.getTenant()))
				.addCriteria(Criteria.where(ProductIntegrationSchemaContsant.FROM_DATE.getFieldName()).gte(fromDate));
		query.fields().include(SchemaConstants.ID.getFieldName());

		List<Product> products = productMongoTemplate.find(query, Product.class, ProductDocument.COLLECTION_NAME);

		List<String> productIds = new ArrayList<String>();
		for (int i = 0; i < products.size(); i++) {
			String productId = products.get(i).getId();
			productIds.add(productId);
		}

		return productIds;
	}

	@Override
	public List<ProductIntegrationDocument> getProductsByProductIds(List<String> productIds) {
		Query query = new Query()
				.addCriteria(Criteria.where(SchemaConstants.TENANT.getFieldName()).is(RequestContext.getTenant()))
				.addCriteria(Criteria.where(SchemaConstants.ID.getFieldName()).in(productIds));
		return productMongoTemplate.find(query, ProductIntegrationDocument.class, ProductDocument.COLLECTION_NAME);
	}

	@Override
	public ProductCategory getProductCategoryById(String categoryId) {
		Query query = new Query()
				.addCriteria(Criteria.where(SchemaConstants.TENANT.getFieldName()).is(RequestContext.getTenant()))
				.addCriteria(Criteria.where(SchemaConstants.ID.getFieldName()).is(categoryId));
		return productCategoryMongoTemplate.findOne(query, ProductCategory.class,
				ProductCategoryDocument.COLLECTION_NAME);
	}

}
