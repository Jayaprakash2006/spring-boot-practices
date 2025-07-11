package com.example.wordlyweek.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import com.example.wordlyweek.model.Magazine;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.service.MagazineJpaService;
import com.example.wordlyweek.service.WriterJpaService;

@RestController
public class MagazineController {

    @Autowired
    MagazineJpaService service;

    @GetMapping("/magazines")
    public ArrayList<Magazine> getMagazines() {
        return service.getMagazines();
    }

    @PostMapping("/magazines")
    public Magazine addMagazine(@RequestBody Magazine magazine) {
        return service.addMagazine(magazine);
    }

    @GetMapping("/magazines/{magazineId}")
    public Magazine getMagazine(@PathVariable("magazineId") int magazineId) {
        return service.getMagazine(magazineId);
    }

    @PutMapping("/magazines/{magazineId}")
    public Magazine updateMagazine(@PathVariable("magazineId") int magazineId, @RequestBody Magazine magazine) {
        return service.updateMagazine(magazineId, magazine);
    }

    @DeleteMapping("/magazines/{magazineId}")
    public void deleteMagazine(@PathVariable("magazineId") int magazineId) {
        service.deleteMagazine(magazineId);
    }

    @GetMapping("/magazines/{magazineId}/writers")
    public List<Writer> getMagazineWriters(@PathVariable("magazineId") int magazineId) {
        return service.getMagazineWriters(magazineId);
    }
}