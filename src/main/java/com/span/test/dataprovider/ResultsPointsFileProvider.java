package com.span.test.dataprovider;

import com.span.test.dataprovider.entity.Team;
import com.span.test.domain.repository.ResultPointsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@Service
@Slf4j
public class ResultsPointsFileProvider implements ResultPointsRepository {

    public static final Integer WIN_POINTS = 3;
    public static final Integer DRAW_POINTS = 1;
    public static final Integer LOSE_POINTS = 0;

    @Override
    public Map<String,Integer> getResultsFromConsole(List<String> games) {
        List<Team> results = new ArrayList<>();
        games.forEach(game -> results.addAll(extractGameResults(game)));
        return rankingTable(results);
    }

    private Map<String,Integer> rankingTable(List<Team> results){
        Map<String,Integer> counter = new HashMap<String,Integer>();
        for(Team s : results){
            if(counter.containsKey(s.getTeamName())){
                counter.put(s.getTeamName(),counter.get(s.getTeamName())+s.getPointsByGame());
            }else{
                counter.put(s.getTeamName(),s.getPointsByGame());
            }

        }
        return counter;
    }

    private List<Team> extractGameResults(String game){
        List<String> gameResults = Arrays.asList(game.split(","));
        List<Team> teamsResults = new ArrayList<>();
        if(gameResults.size() == 2){
            String firstTeam = gameResults.get(0);
            String secondTeam = gameResults.get(1);
            if(validateGames(gameResults.get(0)) && validateGames(gameResults.get(1))){

                int firstTeamScore = parseInt(firstTeam.substring(firstTeam.length() - 1));
                int secondTeamScore = parseInt(secondTeam.substring(secondTeam.length() - 1));

                teamsResults.add(Team.builder()
                        .teamName(firstTeam.substring(0,firstTeam.length()-2))
                        .pointsByGame(firstTeamScore > secondTeamScore ? WIN_POINTS : (firstTeamScore == secondTeamScore ? DRAW_POINTS : LOSE_POINTS))
                        .build());
                teamsResults.add(Team.builder()
                        .teamName(secondTeam.substring(1,secondTeam.length()-2))
                        .pointsByGame(secondTeamScore > firstTeamScore ? WIN_POINTS : (secondTeamScore == firstTeamScore ? DRAW_POINTS : LOSE_POINTS))
                        .build());
            }
        }
        return teamsResults;
    }

    private boolean validateGames(String game){
        Pattern pattern = Pattern.compile("(?=.*[^ ])[a-zA-Z0-9 ]+ \\d{1,}$");
        Matcher match = pattern.matcher(game);
        return match.matches();
    }
}
