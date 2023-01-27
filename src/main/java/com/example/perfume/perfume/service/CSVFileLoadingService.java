package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.dto.PerfumeDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CSVFileLoadingService {
    private final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume3.csv";
    private final PerfumeRepository perfumeRepository;

    public FileReader importFile() throws FileNotFoundException {
        FileReader perfumeData = new FileReader(FILE_PATH);
        return perfumeData;
    }

    public BufferedReader readPerfumeData() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(importFile());
        return bufferedReader;
    }

    public List<String> makePerfumeArray(String[] array, String line) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            array = line.split(",");
            list.add(array[i]);
        }
        return list;
    }

    public List<String> splitPerfumeData() throws IOException {
        String line;
        List<String> list = new ArrayList<>();
        while ((line = readPerfumeData().readLine()) != null) {
            String[] array = line.split(",");
            list = makePerfumeArray(array, line);
        }
        return list;
    }

    public void savePerfumeList(PerfumeDto perfumeDto,Feature feature) throws IOException{
        for(int i = 0 ; i<splitPerfumeData().size(); i+=3){ // 0 1 2 // 3 4 5 6 7 8 9
            perfumeDto.setPerfumeName(splitPerfumeData().get(i));
            perfumeDto.setPerfumeFeature(splitPerfumeData().get(i+1));
            perfumeDto.setBrandName(splitPerfumeData().get(i+2));
            perfumeRepository.save(perfumeDto.toEntity(feature));
        }
        //0 3 6 9 12 13 14 15 16 17 18 19 20 21 i%3==0
    }

}
