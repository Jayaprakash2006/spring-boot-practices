package com.example.wordlyweek.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;
import com.example.wordlyweek.service.WriterJpaService;
import com.example.wordlyweek.service.MagazineJpaService;

@RestController
public class WriterController {

    @Autowired
    WriterJpaService service;

    @GetMapping("/magazines/writers")
    public ArrayList<Writer> getWriters() {
        return service.getWriters();
    }

    @PostMapping("/magazines/writers")
    public Writer addWriter(@RequestBody Writer writer) {
        return service.addWriter(writer);
    }

    @GetMapping("magazines/writers/{writerId}")
    public Writer getWriter(@PathVariable("writerId") int writerId) {
        return service.getWriter(writerId);
    }

    @PutMapping("/magazines/writers/{writerId}")
    public Writer updateWriter(@PathVariable("writerId") int writerId, @RequestBody Writer writer) {
        return service.updateWriter(writerId, writer);
    }

    @DeleteMapping("/magazines/writers/{writerId}")
    public void deleteWriter(@PathVariable("writerId") int writerId) {
        service.deleteWriter(writerId);
    }

    @GetMapping("/writers/{writerId}/magazines")
    public List<Magazine> getWriterMagazines(@PathVariable("writerId") int writerId) {
        return service.getWriterMagazines(writerId);
    }
}