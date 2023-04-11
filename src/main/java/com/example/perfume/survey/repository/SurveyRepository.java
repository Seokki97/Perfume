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

    @Query("SELECT s FROM survey s WHERE s.genderAnswer LIKE %:genderAnswer% AND s.scentAnswer = :scentAnswer AND s.moodAnswer LIKE %:moodAnswer% AND s.seasonAnswer LIKE %:seasonAnswer% AND s.styleAnswer LIKE %:styleAnswer%")
    List<Survey> findSurveysByAnswers(@Param("genderAnswer") String genderAnswer, @Param("scentAnswer") String scentAnswer, @Param("moodAnswer") String moodAnswer, @Param("seasonAnswer") String seasonAnswer, @Param("styleAnswer") String styleAnswer);

    @Query("SELECT s FROM survey s WHERE s.genderAnswer LIKE %:genderAnswer% AND s.scentAnswer = :scentAnswer AND s.moodAnswer LIKE %:moodAnswer%")
    List<Survey> findSurveysByGenderScentAndMood(@Param("genderAnswer") String genderAnswer, @Param("scentAnswer") String scentAnswer, @Param("moodAnswer") String moodAnswer);

    @Query("SELECT s FROM survey s WHERE s.genderAnswer LIKE %:genderAnswer% AND s.scentAnswer = :scentAnswer AND s.moodAnswer LIKE %:moodAnswer% AND s.styleAnswer LIKE %:styleAnswer%")
    List<Survey> findSurveysByGenderScentMoodAndStyle(@Param("genderAnswer") String genderAnswer, @Param("scentAnswer") String scentAnswer, @Param("moodAnswer") String moodAnswer, @Param("styleAnswer") String styleAnswer);

}