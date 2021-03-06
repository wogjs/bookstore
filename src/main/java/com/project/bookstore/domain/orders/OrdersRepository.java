package com.project.bookstore.domain.orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUsers_IdOrderByOrderCodeDesc(String userid);
}
