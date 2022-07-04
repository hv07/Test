package com.span.test.domain.repository;

import com.span.test.dataprovider.entity.Team;

import java.util.List;
import java.util.Map;

public interface ResultPointsRepository {
    Map<String,Integer> getResultsFromConsole(List<String> games);
}

