package com.example.springboot.repository;

import com.example.springboot.model.Tag;
import com.example.springboot.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Modifying
    @Query("update Todo t set t.isActive=?1 where t.id=?2")
    void updateActive(Boolean active, Long id);

    @Query("select t from Todo t where t.name=?1")
    List<Todo> checkDuplicate(String name);
}
