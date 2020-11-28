package com.example.springboot.service;

import com.example.springboot.model.Tag;
import com.example.springboot.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> getAllTag();
    Boolean checkDuplicate(String name);
    Tag save(Tag tag);
    Optional<Tag> find(Long id);
    void delete(Long id);
}
