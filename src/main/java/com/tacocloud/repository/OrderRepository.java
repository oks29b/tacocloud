package com.tacocloud.repository;

import com.tacocloud.entity.TacoOrder;
import com.tacocloud.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findTacoOrderByPlacedAt(User user, Pageable pageable);
}
