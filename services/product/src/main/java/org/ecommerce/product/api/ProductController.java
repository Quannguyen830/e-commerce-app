package org.ecommerce.product.api;

import lombok.RequiredArgsConstructor;
import org.ecommerce.product.api.dto.ProductPurchaseRequest;
import org.ecommerce.product.api.dto.ProductPurchaseResponse;
import org.ecommerce.product.api.dto.ProductRequest;
import org.ecommerce.product.api.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProduct() {
        return ResponseEntity.ok(productService.listProduct());
    }

    @PostMapping("/purchases")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> productPurchaseRequests
    ) {
        return ResponseEntity.ok(productService.purchaseProduct(productPurchaseRequests));
    }
}
