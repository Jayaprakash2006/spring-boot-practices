package com.example.wordlyweek.repository;


import java.util.List;
import java.util.ArrayList;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;

public interface WriterRepository {

    ArrayList<Writer> getWriters();
    Writer addWriter(Writer writer);
    Writer getWriter(int writerId);
    Writer updateWriter(int writerId, Writer writer);
    void deleteWriter(int writerId);
    List<Magazine> getWriterMagazines(int writerId);

}