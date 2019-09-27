package com.cg.dcp.ecom.productintegration.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dcp.ecom.productintegration.controller.model.GetModifiedProductIdsResponse;
import com.cg.dcp.ecom.productintegration.controller.model.GetProductsIntegrationResonse;
import com.cg.dcp.ecom.productintegration.model.ProductIntegrationDocument;
import com.cg.dcp.ecom.productintegration.service.ProductIntegrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/integration")
@Api(tags = "ProductIntegration", value = "CRUD operations for productIntegration")
public class ProductIntegrationController {

	@Autowired
	private ProductIntegrationService service;

	private final Logger logger = LoggerFactory.getLogger(ProductIntegrationController.class);

	@ApiOperation(value = "Retrive ProductIds")
	@ApiParam(value = "Return a list of product ids that have been modified since the given date", required = true)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retrived ProductIds"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/products/modifiedids/")
	public ResponseEntity<Object> getProductIdsByModifiedDate(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
		logger.trace("START-ProductController-getProductIdsByFromDate");
		List<String> productIds = service.getProductIdsByModifiedDate(fromDate);
		logger.trace("END-ProductController-getProductIdsByFromDate");
		return new GetModifiedProductIdsResponse(productIds).build();
	}

	@ApiOperation(value = "Retrieve all the products by productId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Product by ProductId"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/products/")
	public ResponseEntity<Object> getProductsByProductId(@RequestBody List<String> productIds) {
		logger.trace("START-ProductIntegrationController-getProductByProductIds");
		List<ProductIntegrationDocument> products = service.getProductsByProductIds(productIds);
		return new GetProductsIntegrationResonse(products).build();
	}
}
