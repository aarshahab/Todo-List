package com.example.springboot.controller;

import com.example.springboot.model.Tag;
import com.example.springboot.model.Todo;
import com.example.springboot.service.TagService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public List<Tag> getTags() {
        return tagService.getAllTag();
    }

    @PostMapping("/tag")
    public ResponseEntity<?> saveTag(@RequestBody Tag tag) {
        if(!tagService.checkDuplicate(tag.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate tag name");
        }
        tagService.save(tag);
        return ResponseEntity.created(URI.create("")).body(tag);
    }

    @PutMapping("/tag/{id}")
    public ResponseEntity<?> updateTag(@PathVariable(value = "id") Long id,
                                         @Valid @RequestBody Tag tagDetail){

        Optional<Tag> tag = tagService.find(id);
        if (!tag.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tag id not found");
        }
        if(!tagService.checkDuplicate(tagDetail.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate tag name");
        }
        tagDetail.setId(id);
        Tag updateTag = tagService.save(tagDetail);
        return ResponseEntity.ok().body(updateTag);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<?> getTagById(@PathVariable(value = "id") Long id) {
        Optional<Tag> tag = tagService.find(id);
        if (!tag.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todo id not found");
        return ResponseEntity.ok().body(tag);
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(value = "id") Long id) {
        Optional<Tag> tag = tagService.find(id);
        if (!tag.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todo id not found");
        tagService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
}
