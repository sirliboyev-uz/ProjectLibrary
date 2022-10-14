package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Qrcodebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodebookRepazitory extends JpaRepository<Qrcodebook,Integer> {
    boolean existsByNomiAndMuallif(String nomi, String muallif);
}
