package geekspring.market.controllers;

import com.google.api.Http;
import geekspring.market.entites.Greeting;
import geekspring.market.entites.Message;
import geekspring.market.services.ShoppingCartService;
import geekspring.market.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;


@Controller
public class ShopControllerWs {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @MessageMapping("/getTotalOrderItems")
    @SendTo("/topic/totalOrderItems")
    public Greeting getTotalOrderItems(SimpMessageHeaderAccessor headerAccessor, @RequestBody Message message) throws Exception {
        HttpSession session = shoppingCartService.getSession();
        int totalOrderItemsInCart = shoppingCartService.getCurrentCart(session).getTotalOrderItems();
        Greeting cartItems = null;
        if (!message.getName().equals("getCurrentOrderTotalItems")) {
            Thread.sleep(1000); // simulated delay
            cartItems = new Greeting(message.getName() + " добавлен в коризну!", totalOrderItemsInCart);
        } else {
            cartItems = new Greeting("", totalOrderItemsInCart);
        }
        return cartItems;
    }
}
