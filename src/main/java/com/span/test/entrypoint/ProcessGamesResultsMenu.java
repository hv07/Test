package com.span.test.entrypoint;

import com.span.test.entrypoint.api.GamesResultsMenu;
import com.span.test.usecase.GetGameResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class ProcessGamesResultsMenu implements CommandLineRunner, GamesResultsMenu {

    private final GetGameResults getGameResults;

    @Autowired
    public ProcessGamesResultsMenu (GetGameResults getGameResults){this.getGameResults = getGameResults;}

    @Override
    public void getResultsFromConsole() {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        List<String> games = new ArrayList<String>();

        log.info("******************** Type Match result ********************" );

        while(!exit){
            String input = scan.nextLine();
            if(input.equals("finish")){
                exit = true;
            }else{
                games.add(input);
                log.info("Enter another result game or type finish for exit");
            }
        }
        getGameResults.ShowGamePointsResult(games);

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("******************** Welcome!! ******************** \n\n");
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option;
        while(!exit){
            log.info("Please chose an option for read the results of games \n\n 1.- Enter the data \n\n 2.- Exit ");
            try{
                option = sn.nextInt();
                switch (option) {
                    case 1:
                        getResultsFromConsole();
                        break;
                    case 2:
                        exit = true;
                        break;
                    default:
                        log.info("Just numbers between 1 and 2");
                }

            } catch (InputMismatchException e) {
                log.info("You should insert a number");
                sn.next();
            }
        }
        sn.close();
    }

}
