package com.bew.SpringProj.model.dto;

import java.util.List;

public record OrderRequest(
		String customerName,
		String name,
		String email,
		List<OrderItemRequest> items) {}
