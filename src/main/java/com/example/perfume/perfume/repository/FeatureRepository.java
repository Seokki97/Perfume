package com.example.perfume.perfume.repository;

import com.example.perfume.perfume.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
}