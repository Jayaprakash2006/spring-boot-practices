package com.example.wordlyweek.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.NoSuchElementException;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;
import com.example.wordlyweek.repository.WriterRepository;
import com.example.wordlyweek.repository.WriterJpaRepository;
import com.example.wordlyweek.repository.MagazineJpaRepository;

@Service
public class WriterJpaService implements WriterRepository {

    @Autowired
    WriterJpaRepository db;

    @Autowired
    MagazineJpaRepository magazine_db;

    @Override
    public ArrayList<Writer> getWriters() {
        return (ArrayList<Writer>) db.findAll();
    }

    @Override
    public Writer addWriter(Writer writer) {
        try {
            List<Magazine> magazines = writer.getMagazines();
            List<Integer> magazinesId = new ArrayList<>();
            for(Magazine magazine: magazines) {
                magazinesId.add(magazine.getMagazineId());
            }
            List<Magazine> new_magazines = magazine_db.findAllById(magazinesId);

            if(magazinesId.size() != new_magazines.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            writer.setMagazines(new_magazines);
            return db.save(writer);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Writer getWriter(int writerId) {
        try {
            return db.findById(writerId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Writer updateWriter(int writerId, Writer writer) {
        try {
            Writer ext = db.findById(writerId).get();
            if(writer.getWriterName() != null) ext.setWriterName(writer.getWriterName());
            if(writer.getBio() != null) ext.setBio(writer.getBio());
            if(writer.getMagazines() != null) {
                ext.setMagazines(null);
                List<Magazine> magazines = writer.getMagazines();
                List<Integer> magazinesId = new ArrayList<>();
                for(Magazine magazine: magazines) {
                    magazinesId.add(magazine.getMagazineId());
                }
                List<Magazine> new_magazine = magazine_db.findAllById(magazinesId);

                if(magazinesId.size() != new_magazine.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }

                ext.setMagazines(new_magazine);
            }
            return db.save(ext);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteWriter(int writerId) {
        try {
            Writer writer = db.findById(writerId).get();
            List<Magazine> magazines = writer.getMagazines();
            for(Magazine magazine: magazines) {
                magazine.getWriters().remove(writer);
            }
            magazine_db.saveAll(magazines);
            db.deleteById(writerId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Magazine> getWriterMagazines(int writerId) {
        try {
            Writer writer = db.findById(writerId).get();
            List<Magazine> magazines = magazine_db.findByWriters(writer);
            return magazines;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}