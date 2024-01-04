package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.survey.domain.Question;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    private final SurveyUtil surveyUtil;

    private final SurveyConverter surveyConverter;

    public SurveyService(SurveyRepository surveyRepository, SurveyUtil surveyUtil, SurveyConverter surveyConverter) {
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
        this.surveyConverter = surveyConverter;
    }

    public Survey findSurveyById(Long id) {
        return surveyRepository.findBySurveyId(id).orElseThrow(SurveyNotFoundException::new);
    }

    public void saveSurveyData(Survey survey) {
        surveyRepository.save(survey);
    }

    private List<Survey> filterSurveyResultByQuestion(SurveyRequestDto surveyRequestDto) {
        if (SurveyType.NOT_SELECT_SEASON.isSeasonNotSelected(surveyRequestDto)) {
            return surveyRepository.findSurveysByGenderScentMoodAndStyle(
                    surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(),
                    surveyRequestDto.getMoodAnswer(), surveyRequestDto.getStyleAnswer());
        }
        return surveyRepository.findSurveysByAnswers(
                surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(),
                surveyRequestDto.getMoodAnswer(), surveyRequestDto.getSeasonAnswer(),
                surveyRequestDto.getStyleAnswer());
    }

    public List<Perfume> showPerfumeListBySurvey(SurveyRequestDto surveyRequestDto) {
        List<Survey> surveyList = filterSurveyResultByQuestion(surveyRequestDto);

        if (surveyUtil.isEmptyRecommendedPerfumeList(surveyList)) {
            List<Survey> surveyListByMood = surveyRepository.findSurveysByGenderScentAndMood(
                    surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(),
                    surveyRequestDto.getMoodAnswer());

            return surveyConverter.convertToPerfumeData(surveyListByMood);
        }
        return surveyConverter.convertToPerfumeData(surveyList);
    }

    public List<Perfume> showSimilarPerfumeList(Survey survey) {
        String selectedMoodAnswer = surveyUtil.showMoodAnswer(survey);
        List<Survey> findSimilarData = surveyRepository.findSurveysByGenderScentAndMood
                (survey.getGenderAnswer(), survey.getScentAnswer(), selectedMoodAnswer);

        return surveyConverter.convertToPerfumeData(findSimilarData);
    }

    public Perfume showRecommendedPerfume(Question question) {
        Survey survey = surveyRepository.findSurveysByRecommend(question.getGenderAnswer(),
                        question.getScentAnswer(), question.getMoodAnswer(),
                        question.getSeasonAnswer(), question.getStyleAnswer())
                .orElseThrow(PerfumeNotFoundException::new);

        return survey.getPerfume();
    }
}
