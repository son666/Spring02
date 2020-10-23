package geekspring.market.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Optional;

@Data
public class FilterProducts {
    String word;
    Double min;
    Double max;
    int currentPage;
    int pageSize;

    public FilterProducts(@JsonProperty("word") String word,
                          @JsonProperty("min") Double min,
                          @JsonProperty("max") Double max,
                          @JsonProperty("currentPage") int currentPage,
                          @JsonProperty("pageSize") int pageSize) {
        this.word = word;
        this.min = min;
        this.max = max;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
