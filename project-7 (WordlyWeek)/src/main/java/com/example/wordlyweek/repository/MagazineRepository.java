package com.example.wordlyweek.repository;

import java.util.List;
import java.util.ArrayList;

import com.example.wordlyweek.model.Magazine;
import com.example.wordlyweek.model.Writer;

public interface MagazineRepository {

    ArrayList<Magazine> getMagazines();
    Magazine addMagazine(Magazine magazine);
    Magazine getMagazine(int magazineId);
    Magazine updateMagazine(int magazineId, Magazine magazine);
    void deleteMagazine(int magazineId);
    List<Writer> getMagazineWriters(int magazineId);

}