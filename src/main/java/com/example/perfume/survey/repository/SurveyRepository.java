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

    List<Survey> findByMoodAnswerNotContaining(String moodAnswer);

    List<Survey> findByStyleAnswer(String styleAnswer);

    boolean existsByScentAnswer(String scentAnswer);

    boolean existsByGenderAnswer(String genderAnswer);

    boolean existsByMoodAnswer(String moodAnswer);

    List<Survey> findByGenderAnswerOrGenderAnswer(String genderAnswer,String genderLess);
    List<Survey> findBySeasonAnswerContainingOrSeasonAnswer(String seasonAnswer, String fourSeason);
}
