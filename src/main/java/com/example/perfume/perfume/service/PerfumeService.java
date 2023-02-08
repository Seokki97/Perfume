package com.example.perfume.perfume.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.exception.BrandNotFoundException;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.repository.SurveyRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;

    private final PerfumeCSVFileLoading perfumeCsvFileLoading;

    private final SurveyRepository surveyRepository;

    public PerfumeService(PerfumeRepository perfumeRepository, PerfumeCSVFileLoading perfumeCsvFileLoading,SurveyRepository surveyRepository) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeCsvFileLoading = perfumeCsvFileLoading;
        this.surveyRepository = surveyRepository;
    }


    public PerfumeResponseDto makePerfumeList(Long id, int firstIndex, PerfumeList perfumeList) {
        PerfumeResponseDto perfumeResponseDto = new PerfumeResponseDto(id,
                perfumeList.getPerfumeName().get(firstIndex),
                perfumeList.getPerfumeFeature().get(firstIndex),
                perfumeList.getPerfumeBrand().get(firstIndex),
                perfumeList.getPerfumeImageUrl().get(firstIndex));
        return perfumeResponseDto;
    }

    public void savePerfumeData(Long id, PerfumeList perfumeList) throws IOException {
        perfumeList = perfumeCsvFileLoading.extractAllPerfumeData(perfumeList);

        for (int firstIndex = 0; firstIndex < perfumeList.getMaxSize(); firstIndex++) {

            Perfume perfumeDataSet = makePerfumeList(id, firstIndex, perfumeList).toEntity();

            perfumeRepository.save(perfumeDataSet);
        }
    }

    public Perfume findPerfumeByName(PerfumeRequestDto perfumeRequestDto) {

        Perfume perfume = perfumeRepository.findByPerfumeNameContaining(perfumeRequestDto.getPerfumeName())
                .orElseThrow(PerfumeNotFoundException::new);
        return perfume;
    }

    public Perfume findPerfumeByBrand(PerfumeRequestDto perfumeRequestDto) {
        Perfume perfume = perfumeRepository.findByBrandNameContaining(perfumeRequestDto.getBrandName())
                .orElseThrow(BrandNotFoundException::new);

        return perfume;
    }
    //브랜드나 향수를 입력해 -> 해당 향수 리스트를 보내고 사용자는 거기서 선택해 -> 그럼 다시 향수를 리턴받아,
    // 그럼 리턴받은 향수의 id의 survey데이터를 가져와서 거기에 시트러스라 적혀져 있으면 시트러스 + 여자or 남자or 젠더리스 맞게 반환해주면됨


    public void deleteAllData() {
        perfumeRepository.deleteAll();
    }

    public List<Perfume> showAllPerfumeData() {

        return perfumeRepository.findAll().stream().collect(Collectors.toList());
    }

}
