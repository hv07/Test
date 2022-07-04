package com.span.test.usecase;

import com.span.test.domain.repository.ResultPointsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GetGameResults {

    private final ResultPointsRepository resultPointsRepository;

    @Autowired
    public GetGameResults (ResultPointsRepository resultPointsRepository){
        this.resultPointsRepository = resultPointsRepository;
    }

    public void ShowGamePointsResult(List<String> games){
        LinkedHashMap<String, Integer> rankingTable = new LinkedHashMap<>();
        int position = 1;

        resultPointsRepository.getResultsFromConsole(games).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> rankingTable.put(x.getKey(), x.getValue()));

        for(Map.Entry<String,Integer> entry : rankingTable.entrySet()){
            log.info(position + "." + entry.getKey() + ", " + entry.getValue() + " pts");
            position++;
        }
    }
}
