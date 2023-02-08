package com.example.perfume.perfume.repository;

import com.example.perfume.perfume.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {


    Optional<Perfume> findById(Long id);
    Optional<Perfume> findByPerfumeNameContaining(String perfumeName);


    Optional<Perfume> findByBrandNameContaining(String perfumeBrand);

    boolean existsByPerfumeName(String perfumeName);

}
