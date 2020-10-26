package com.flamexander.cloud.client;

import com.flamexander.cloud.client.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

public interface GetShopProducts {

    @RequestMapping("/getAllProducts")
    List<Product> getAllProducts();

    @RequestMapping(method = RequestMethod.POST, value="/sendParam")
    PageProducts sendParam(@RequestBody FilterProducts filterProducts);
}
