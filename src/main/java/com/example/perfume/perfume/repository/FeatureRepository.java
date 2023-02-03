package com.example.perfume.perfume.repository;

import com.example.perfume.perfume.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Optional<Feature> findByAnswerOfSurveyContaining(List<String> answerOfSurvey);

}
