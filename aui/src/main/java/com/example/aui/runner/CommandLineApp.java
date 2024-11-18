package com.example.aui.runner;

import com.example.aui.entities.Sport;
import com.example.aui.entities.Sportsman;
import com.example.aui.services.SportService;
import com.example.aui.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineApp implements CommandLineRunner {
    private final SportService sportService;
    private final SportsmanService sportsmanService;

    @Autowired
    public CommandLineApp(SportService sportService, SportsmanService sportsmanService) {
        this.sportService = sportService;
        this.sportsmanService = sportsmanService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        var running = true;
        while (running) {
            options();
            String option = scanner.nextLine();

            if (option.equals("1")) {
                listSports();
            } else if (option.equals("11")) {
                extendedListSports();
            } else if (option.equals("21")) {
                extendedListSportsman();
            } else if (option.equals("2")) {
                listSportsman();
            } else if (option.equals("3")) {
                addSport();
            } else if (option.equals("4")) {
                addSportsman();
            } else if (option.equals("5")) {
                deleteAll();
            } else if (option.equals("6")) {
                deleteAllSportsmen();
            } else if (option.equals("7")) {
                deleteSport();
            } else if (option.equals("8")) {
                deleteSportsman();
            } else if (option.equals("q")) {
                System.exit(0);
            }
        }
    }

    public void options() {
        var options = "Choose an option: ";
        options += "[1] List Sports ";
        options += "[11] List Sports with more details ";
        options += "[2] List Sportsmen ";
        options += "[21] List Sportsmen with more details ";
        options += "[3] Add Sport ";
        options += "[4] Add Sportsman ";
        options += "[5] Delete All ";
        options += "[6] Delete All Sportsmen ";
        options += "[7] Delete Sport ";
        options += "[8] Delete Sportsman ";
        options += "[q] Quit";
        System.out.println(options);
    }

    public void listSports() {
        List<Sport> sports = sportService.findAll();
        sports.forEach(System.out::println);
    }

    public void extendedListSports() {
        List<Sport> sports = sportService.findAll();
        sports.forEach(Sport::listSportsmen);
    }

    public void listSportsman() {
        List<Sportsman> sportsmen = sportsmanService.findAll();
        sportsmen.forEach(sportsman -> System.out.println(sportsman.getName()));
    }

    public void extendedListSportsman() {
        List<Sportsman> sportsmen = sportsmanService.findAll();
        sportsmen.forEach(System.out::println);
    }

    public void addSport() {
        System.out.println("Enter sport name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Sport sport = new Sport(UUID.randomUUID(), name, null);
        sportService.save(sport);
    }

    public void addSportsman() {
        System.out.println("Enter sportsman in the following format [name/rating/sport]:");
        Scanner scanner = new Scanner(System.in);
        String[] sportsmanData = scanner.nextLine().split("/");
        String name = sportsmanData[0];
        int rating = Integer.parseInt(sportsmanData[1]);
        String sportName = sportsmanData[2];
        Sport sport = sportService.findByName(sportName);
        if (sport == null) {
            System.out.println("Sport not found");
            return;
        }
        Sportsman sportsman = new Sportsman(UUID.randomUUID(), name, rating, sport);
        sportsmanService.save(sportsman);
    }

    public void deleteSport() {
        System.out.println("Enter sport name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        var sport = sportService.findByName(name);
        sportService.delete(sport);
    }

    public void deleteSportsman() {
        System.out.println("Enter sportsman name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        var sportsman = sportsmanService.findByName(name);
        sportsmanService.delete(sportsman);
    }

    public void deleteAllSportsmen() {
        sportsmanService.deleteAll();
    }

    public void deleteAll() {
        sportsmanService.deleteAll();
        sportService.deleteAll();
    }
}

