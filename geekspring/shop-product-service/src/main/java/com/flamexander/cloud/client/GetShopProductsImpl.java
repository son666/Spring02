package com.flamexander.cloud.client;

import com.flamexander.cloud.client.entites.Product;
import com.flamexander.cloud.client.repositories.specifications.ProductSpecs;
import com.flamexander.cloud.client.services.ProductService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class GetShopProductsImpl implements GetShopProducts {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    private ProductService productService;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public PageProducts sendParam(FilterProducts filterProducts) {
        String word = filterProducts.getWord();
        Double min = filterProducts.getMin();
        Double max = filterProducts.getMax();
        int currentPage = filterProducts.getCurrentPage();
        int pageSize = filterProducts.getPageSize();

        StringBuilder filters = new StringBuilder();
        Specification<Product> spec = Specification.where(null);
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
            filters.append("&word=" + word);
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }
        Page<Product> pageProducts = productService.getProductsWithPagingAndFiltering(currentPage, pageSize, spec);

        return new PageProducts(pageProducts.getContent(), pageProducts.getTotalPages(), filters);
    }
}