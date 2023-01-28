package com.example.perfume.perfume.repository;

import com.example.perfume.perfume.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
    Optional<Perfume> findById(Long id);
    Optional<Perfume> findByPerfumeName(String perfumeName);
    Optional<Perfume> findByPerfumeFeature(String perfumeFeature);

    Optional<Perfume> findByPerfumeBrand(String perfumeBrand);
}
