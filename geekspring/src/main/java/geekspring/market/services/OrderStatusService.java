package geekspring.market.services;

import geekspring.market.entites.OrderStatus;
import geekspring.market.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public List<OrderStatus> getAllOrderStatus() {
        return (List<OrderStatus>) orderStatusRepository.findAll();
    }

    public OrderStatus getStatusById(Long id) {
        return orderStatusRepository.findById(id).orElse(null);
    }

    public boolean isStatusWithTitleExists(String statusTitle) {
        return orderStatusRepository.findOneByTitle(statusTitle) != null;
    }

    public void deleteStatusById(Long id) {
        orderStatusRepository.deleteById(id);
    }

    public void saveStatus(OrderStatus status) {
        orderStatusRepository.save(status);
    }
}
