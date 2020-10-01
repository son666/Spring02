package geekspring.market.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/product-page")
    public String productPage() {
        return "product-page";
    }

    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/resources/favicon.ico";
    }
}
