package com.example.perfume.survey.repository;

import com.example.perfume.survey.domain.Survey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("SELECT s FROM survey s JOIN FETCH s.perfume WHERE s.surveyId = :surveyId")
    Optional<Survey> findBySurveyId(@Param("surveyId") Long surveyId);

    @Query("SELECT s FROM survey s WHERE s.genderAnswer LIKE %:genderAnswer% AND s.scentAnswer = :scentAnswer AND s.moodAnswer LIKE %:moodAnswer% AND s.seasonAnswer LIKE %:seasonAnswer% AND s.styleAnswer LIKE %:styleAnswer%")
    List<Survey> findSurveysByAnswers(@Param("genderAnswer") String genderAnswer,
                                      @Param("scentAnswer") String scentAnswer, @Param("moodAnswer") String moodAnswer,
                                      @Param("seasonAnswer") String seasonAnswer,
                                      @Param("styleAnswer") String styleAnswer);

    @Query("SELECT s FROM survey s WHERE s.genderAnswer LIKE %:genderAnswer% AND s.scentAnswer = :scentAnswer AND s.moodAnswer LIKE %:moodAnswer%")
    List<Survey> findSurveysByGenderScentAndMood(@Param("genderAnswer") String genderAnswer,
                                                 @Param("scentAnswer") String scentAnswer,
                                                 @Param("moodAnswer") String moodAnswer);

    @Query("SELECT s FROM survey s WHERE s.genderAnswer LIKE %:genderAnswer% AND s.scentAnswer = :scentAnswer AND s.moodAnswer LIKE %:moodAnswer% AND s.styleAnswer LIKE %:styleAnswer%")
    List<Survey> findSurveysByGenderScentMoodAndStyle(@Param("genderAnswer") String genderAnswer,
                                                      @Param("scentAnswer") String scentAnswer,
                                                      @Param("moodAnswer") String moodAnswer,
                                                      @Param("styleAnswer") String styleAnswer);

    @Query(value = "SELECT * FROM survey s WHERE s.gender_answer LIKE :genderAnswer AND s.scent_answer = :scentAnswer AND s.mood_answer LIKE :moodAnswer AND s.season_answer LIKE :seasonAnswer AND s.style_answer LIKE :styleAnswer ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Survey> findSurveysByRecommend(@Param("genderAnswer") String genderAnswer,
                                            @Param("scentAnswer") String scentAnswer,
                                            @Param("moodAnswer") String moodAnswer,
                                            @Param("seasonAnswer") String seasonAnswer,
                                            @Param("styleAnswer") String styleAnswer);

}