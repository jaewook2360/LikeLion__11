package likelion.springbootcillian.repository;

import likelion.springbootcillian.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}