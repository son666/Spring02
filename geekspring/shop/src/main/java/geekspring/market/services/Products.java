package geekspring.market.services;

import geekspring.market.entites.Product;
import geekspring.market.utils.FilterProducts;
import geekspring.market.utils.PageProducts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@FeignClient("geek-spring-cloud-eureka-client-product-service")
public interface Products {

    @RequestMapping("/getAllProducts")
    List<Product> getAllProducts();

    @RequestMapping(method = RequestMethod.POST, value="/sendParam")
    PageProducts sendParam(@RequestBody FilterProducts filterProducts);


}
