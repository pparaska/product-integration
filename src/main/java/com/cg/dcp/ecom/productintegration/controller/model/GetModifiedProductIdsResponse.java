package com.cg.dcp.ecom.productintegration.controller.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.cg.dcp.common.handler.ServiceResponse;

import lombok.Getter;

@Getter
public class GetModifiedProductIdsResponse extends ServiceResponse{

	private List<String> productIds;

	public GetModifiedProductIdsResponse(List<String> productIds) {
		super(HttpStatus.OK);
		this.productIds = productIds;
	}
}