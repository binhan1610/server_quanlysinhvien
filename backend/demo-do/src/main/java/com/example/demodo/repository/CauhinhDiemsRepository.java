package com.example.demodo.repository;

import com.example.demodo.entity.CauhinhDiems;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauhinhDiemsRepository extends JpaRepository<CauhinhDiems, Integer> {
    @Query(value = "select * from cauhinh_diems where cauhinh_diems.monhoc_id = ?1", nativeQuery = true)
    ArrayList<CauhinhDiems> findByidMonhoc(Integer idmonhoc);

}
