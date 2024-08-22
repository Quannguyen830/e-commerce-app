package org.ecommerce.product.api;

import org.ecommerce.product.api.dto.ProductPurchaseRequest;
import org.ecommerce.product.api.dto.ProductPurchaseResponse;
import org.ecommerce.product.api.dto.ProductRequest;
import org.ecommerce.product.api.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest productRequest);
    ProductResponse getProduct(Integer productId);
    List<ProductResponse> listProduct();
    List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequests);
}
