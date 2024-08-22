package org.ecommerce.product.api;

import lombok.RequiredArgsConstructor;
import org.ecommerce.product.api.dto.ProductPurchaseRequest;
import org.ecommerce.product.api.dto.ProductPurchaseResponse;
import org.ecommerce.product.api.dto.ProductRequest;
import org.ecommerce.product.api.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productRepository.save(productMapper.toEntity(productRequest)).getId();
    }

    @Override
    public ProductResponse getProduct(Integer productId) {
        return productMapper.toProductResponse(
                productRepository.findById(productId).orElseThrow(
                        IllegalArgumentException::new
                )
        );
    }

    @Override
    public List<ProductResponse> listProduct() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequests) {
        List<Integer> productIds = productPurchaseRequests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new RuntimeException("Some products do not exist");
        }
        var sortedRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new RuntimeException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
