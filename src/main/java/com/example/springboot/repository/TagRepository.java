package com.example.springboot.repository;

import com.example.springboot.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Modifying
    @Query("update Tag t set t.isActive=?1 where t.id=?2")
    void updateActive(Boolean active, Long id);

    @Query("select t from Tag t where t.name=?1")
    List<Tag> checkDuplicate(String name);
}
