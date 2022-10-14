package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.IjaraBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IjaraBookRepazitory extends JpaRepository<IjaraBook,Integer> {
    boolean existsByNomiAndMuallif(String nomi, String muallif);
}
