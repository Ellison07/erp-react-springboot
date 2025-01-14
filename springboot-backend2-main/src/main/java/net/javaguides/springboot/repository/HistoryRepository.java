package net.javaguides.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>{

}
