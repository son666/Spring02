package geekspring.market.controllers;

import geekspring.market.entites.OrderStatus;
import geekspring.market.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/statuses")
public class OrderStatusController {
    private OrderStatusService orderStatusService;

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public String showProductPage(HttpServletRequest request, HttpServletResponse response) {
        return "/admin-edit-status";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        OrderStatus status = orderStatusService.getStatusById(id);
        if (status == null) {
            status = new OrderStatus();
            status.setId(0L);
        }
        model.addAttribute("status", status);
        return "/admin-edit-status";
    }

    @PostMapping("/edit")
    public String processStatusAddForm(@Valid @ModelAttribute("status") OrderStatus orderStatus, BindingResult theBindingResult, Model model) {
        if (orderStatus.getId() == 0 && orderStatusService.isStatusWithTitleExists(orderStatus.getTitle())) {
            theBindingResult.addError(new ObjectError("status.title", "Статус заказа с таким названием уже существует")); // todo не отображает сообщение
        }
        orderStatusService.saveStatus(orderStatus);
        return "redirect:/admin/order_statuses";
    }

    @GetMapping("/delete/{id}")
    public String processStatusDelete(@PathVariable(name = "id") Long id, Model model) {
        OrderStatus status = orderStatusService.getStatusById(id);
        if (status == null) {
            return "redirect:/admin/order_statuses";
        }
        orderStatusService.deleteStatusById(id);
        return "redirect:/admin/order_statuses";
    }
}
