package com.example.springboot.service.impl;

import com.example.springboot.model.Tag;
import com.example.springboot.model.Todo;
import com.example.springboot.repository.TagRepository;
import com.example.springboot.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Primary
public class TagServiceImpl implements TagService {

    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTag(){
        return tagRepository.findAll();
    }

    @Override
    public Boolean checkDuplicate(String name){
        return tagRepository.checkDuplicate(name).isEmpty();
    }

    @Override
    public Tag save(Tag tag) {
        log.debug("Save tag : ", tag);
        return tagRepository.save(tag);
    }


    @Override
    public Optional<Tag> find(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        tagRepository.updateActive(false,id);
    }

}
