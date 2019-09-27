package com.cg.dcp.ecom.productintegration.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dcp.ecom.productintegration.model.Allergens;
import com.cg.dcp.ecom.productintegration.model.Nutritional;
import com.cg.dcp.ecom.productintegration.model.ProductIntegrationDocument;
import com.cg.dcp.ecom.productintegration.repository.ProductIntegrationRepository;
import com.cg.dcp.product.model.ProductFeature;
import com.cg.dcp.productcategory.model.ProductCategory;

@Service
public class ProductIntegrationServiceImpl implements ProductIntegrationService {

	@Autowired
	private ProductIntegrationRepository productIntegrationRepository;

	@Override
	public List<String> getProductIdsByModifiedDate(Date fromDate) {
		return productIntegrationRepository.getModifiedProductIds(fromDate);
	}

	@Override
	public List<ProductIntegrationDocument> getProductsByProductIds(List<String> productIds) {
		List<ProductIntegrationDocument> products = productIntegrationRepository.getProductsByProductIds(productIds);
		LocalDate now = LocalDate.now();
		LocalDate fromDate = null;
		LocalDate throughDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		List<Allergens> allergens = new ArrayList<Allergens>();
		List<Nutritional> nutritionals = new ArrayList<Nutritional>();
		List<ProductFeature> features = products.get(0).getFeatures();

		Map<String, ProductCategory> map = new HashMap<>();

		for (ProductIntegrationDocument currentProduct : products) {
			for (int i = 0; i < products.size(); i++) {
				String currentCategoryId = currentProduct.getCategoryId();
				ProductCategory currentCategory = productIntegrationRepository
						.getProductCategoryById(currentCategoryId);
				map.put(currentCategoryId, currentCategory);
				if (!map.containsKey(currentCategoryId))
					map.put(currentCategoryId, currentCategory);
				products.get(i).setCategory(currentCategory);

				if (currentProduct.getFromDate() != null) {
					fromDate = currentProduct.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				}
				if (currentProduct.getThroughDate() != null) {
					throughDate = currentProduct.getThroughDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
				}
				boolean isActiveFlag = (fromDate == null || !fromDate.isAfter(now))
						&& (throughDate == null || !throughDate.isBefore(now));
				products.get(i).setActiveFlag(Boolean.toString(isActiveFlag));

				String updateTime = formatter.format(currentProduct.getModifiedDate());
				products.get(i).setUpdateTime(updateTime);
				if (features != null) {
					for (int index = 0; index < features.size(); index++) {
						ProductFeature currentFeature = features.get(index);
						String currentFeatureType = currentFeature.getType();
						if ("allergens".equals(currentFeatureType)) {
							Allergens newAllergen = new Allergens();
							newAllergen.setAllergenName(currentFeature.getName());

							Map<String, Object> allergenFeatureFields = currentFeature.getFields();
							if (allergenFeatureFields != null) {
								newAllergen.setAllergenType((String) allergenFeatureFields
										.get(ProductIntegrationSchemaContsant.ALLERGEN_TYPE.getFieldName()));
								newAllergen.setAllergenRiskLevel((String) allergenFeatureFields
										.get(ProductIntegrationSchemaContsant.ALLERGEN_RISK_LEVEL.getFieldName()));
							}
							allergens.add(newAllergen);
						} else if ("nutritionals".equals(currentFeatureType)) {
							Nutritional newNutritional = new Nutritional();
							newNutritional.setNutritionalType(currentFeature.getName());

							Map<String, Object> nutritionalFeatureFields = currentFeature.getFields();
							if (nutritionalFeatureFields != null) {
								// TODO shubham to correct mapping
								newNutritional.setPerHundredGram((Integer) nutritionalFeatureFields
										.get(ProductIntegrationSchemaContsant.PER_100_G.getFieldName()));
								newNutritional.setPerServing((Integer) nutritionalFeatureFields
										.get(ProductIntegrationSchemaContsant.PER_SERVING.getFieldName()));
							}

							nutritionals.add(newNutritional);
						}
					}
				}
			}

			products.get(0).setAllergens(allergens);
			products.get(0).setNutritionals(nutritionals);
		}
		// To supress categoryId in the response schema
		products.get(0).setCategoryId(null);

		return products;
	}
}
