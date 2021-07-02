package org.zerock.j07.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.j07.todo.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
