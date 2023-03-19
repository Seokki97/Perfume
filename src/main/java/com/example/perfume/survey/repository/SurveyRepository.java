package com.example.perfume.survey.repository;

import com.example.perfume.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Optional<Survey> findById(Long id);

    List<Survey> findByGenderAnswer(String genderAnswer);

    List<Survey> findByScentAnswer(String scentAnswer);

    List<Survey> findByMoodAnswerContaining(String moodAnswer);

    List<Survey> findByStyleAnswer(String styleAnswer);

    List<Survey> findByGenderAnswerAndScentAnswer(String genderAnswer, String scentAnswer);

    List<Survey> findBySeasonAnswerContainingOrSeasonAnswer(String seasonAnswer, String fourSeason);

    List<Survey> findByStyleAnswerContainingOrStyleAnswer(String styleAnswer, String defaultValue);

    List<Survey> findByGenderAnswerOrGenderAnswerAndScentAnswer(String genderAnswer, String genderLess, String scentAnswer);

}
