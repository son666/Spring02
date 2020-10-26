package geekspring.market.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import geekspring.market.entites.Product;
import lombok.Data;

import java.util.List;

@Data
public class PageProducts {
    List<Product> itemsProduct;
    int totalPages;
    StringBuilder filters;

    public PageProducts(@JsonProperty("itemsProduct") List<Product> itemsProduct, @JsonProperty("totalPages") int totalPages, @JsonProperty("filters") StringBuilder filters) {
        this.itemsProduct = itemsProduct;
        this.totalPages = totalPages;
        this.filters = filters;

    }
}
