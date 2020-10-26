package geekspring.market.repositories;

import geekspring.market.entites.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    OrderStatus findOneByTitle(String title);
}
