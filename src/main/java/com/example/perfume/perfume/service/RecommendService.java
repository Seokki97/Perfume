package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.SurveyService;
import com.example.perfume.survey.service.SurveyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendService {
    private final PerfumeRepository perfumeRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;


    public RecommendService(PerfumeRepository perfumeRepository, SurveyRepository surveyRepository, SurveyUtil surveyUtil) {
        this.perfumeRepository = perfumeRepository;
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    //브랜드나 향수를 입력해 -> 해당 향수 리스트를 보내고 사용자는 거기서 선택해 -> 그럼 다시 향수를 리턴받아,
    // 그럼 리턴받은 향수의 id의 survey데이터를 가져와서 거기에 시트러스라 적혀져 있으면 시트러스 + 여자or 남자or 젠더리스 맞게 반환해주면됨
    public String findSecondFeatureSelectedPerfume(Long id) {

        return surveyRepository.findById(id).get().getSecondAnswerOfSurvey();
    } //두번째 대답을 반환해줌

    public String findFirstFeatureSelectedPerfume(Long id) {
        return surveyRepository.findById(id).get().getFirstAnswerOfSurvey();
    } //첫번째 대답을 반환해줌

    public String findThirdFeatureSelectedPerfume(Long id) {
        return surveyRepository.findById(id).get().getThirdAnswerOfSurvey();
    }

    public List<Survey> findSimilarPerfume(Long id) {
        List<Survey> firstFeatureList = surveyRepository.findByFirstAnswerOfSurvey(findFirstFeatureSelectedPerfume(id));
        List<Survey> secondFeatureList = surveyRepository.findBySecondAnswerOfSurvey(findSecondFeatureSelectedPerfume(id));
        List<Survey> addedList = surveyUtil.addList(firstFeatureList, surveyRepository.findByFirstAnswerOfSurvey("젠더리스"));
        List<Survey> comparedList = surveyUtil.compareTwoFilteredSurveyData(addedList, secondFeatureList);
        List<Survey> thirdData = surveyRepository.findByThirdAnswerOfSurveyContaining(findThirdFeatureSelectedPerfume(id));
        return surveyUtil.compareTwoFilteredSurveyData(comparedList, thirdData);
    }

}
