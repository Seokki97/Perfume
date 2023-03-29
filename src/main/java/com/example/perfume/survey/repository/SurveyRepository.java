package com.example.perfume.survey.repository;

import com.example.perfume.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Optional<Survey> findById(Long id);

    List<Survey> findByGenderAnswer(String genderAnswer);

    List<Survey> findByScentAnswer(String scentAnswer);

    List<Survey> findByStyleAnswer(String styleAnswer);

    List<Survey> findByGenderAnswerContainingAndScentAnswerAndMoodAnswerContainingAndSeasonAnswerContainingAndStyleAnswerContaining
            (String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer);

    List<Survey> findByGenderAnswerContainingAndScentAnswerAndMoodAnswerContaining
            (String genderAnswer, String scentAnswer, String moodAnswer);
}
