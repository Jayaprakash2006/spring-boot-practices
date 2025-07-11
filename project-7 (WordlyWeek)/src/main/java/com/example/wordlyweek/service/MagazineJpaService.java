package com.example.wordlyweek.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import com.example.wordlyweek.model.Magazine;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.repository.MagazineRepository;
import com.example.wordlyweek.repository.MagazineJpaRepository;
import com.example.wordlyweek.repository.WriterJpaRepository;

@Service
public class MagazineJpaService implements MagazineRepository {

    @Autowired
    MagazineJpaRepository db;

    @Autowired
    WriterJpaRepository writer_db;

    @Override
    public ArrayList<Magazine> getMagazines() {
        return (ArrayList<Magazine>) db.findAll();
    }

    @Override
    public Magazine addMagazine(Magazine magazine) {
        try {
            List<Writer> writers = magazine.getWriters();
            List<Integer> writersId = new ArrayList<>();
            for(Writer writer: writers) {
                writersId.add(writer.getWriterId());
            }
            List<Writer> new_writers = writer_db.findAllById(writersId);
            magazine.setWriters(new_writers);
            Magazine saved = db.save(magazine);

            for(Writer writer: new_writers) {
                writer.getMagazines().add(saved);
            }
            writer_db.saveAll(new_writers);
            return saved;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Magazine getMagazine(int magazineId) {
        try {
            return db.findById(magazineId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Magazine updateMagazine(int magazineId, Magazine magazine) {
        try {
            Magazine ext = db.findById(magazineId).get();
            if(magazine.getMagazineName() != null) ext.setMagazineName(magazine.getMagazineName());
            if(magazine.getPublicationDate() != null) ext.setPublicationDate(magazine.getPublicationDate());
            if(magazine.getWriters() != null) {
                List<Writer> writers = magazine.getWriters();
                List<Integer> writersId = new ArrayList<>();

                for(Writer writer: writers) {
                    writersId.add(writer.getWriterId());
                }

                List<Writer> new_writer = writer_db.findAllById(writersId);

                for(Writer writer: ext.getWriters()) {
                    writer.getMagazines().remove(ext);
                }
                ext.setWriters(new_writer);

                for(Writer writer: new_writer) {
                    writer.getMagazines().add(ext);
                }

                writer_db.saveAll(new_writer);

            }
                return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteMagazine(int magazineId) {
        try {
            Magazine magazine = db.findById(magazineId).get();
            List<Writer> writers = magazine.getWriters();
            for(Writer writer: writers) {
                writer.getMagazines().remove(magazine);
            }
            writer_db.saveAll(writers);
            db.deleteById(magazineId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Writer> getMagazineWriters(int magazineId) {
        try {
            Magazine magazine = db.findById(magazineId).get();
            List<Writer> writers = writer_db.findByMagazines(magazine);
            return writers;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}