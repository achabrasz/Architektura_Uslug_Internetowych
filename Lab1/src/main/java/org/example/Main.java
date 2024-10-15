package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        List<Sport> sports = createData();

        System.out.println("Task 2");
        task2(sports);

        System.out.println();

        System.out.println("Task 3");
        Set<Sportsman> allSportsmen = task3(sports);

        System.out.println();

        System.out.println("Task 4");
        task4(allSportsmen);

        System.out.println();

        System.out.println("Task 5");
        task5(allSportsmen);

        System.out.println();

        System.out.println("Task 6");
        task6(sports);

        System.out.println();

        System.out.println("Task 7");
        task7(sports);
    }

    private static List<Sport> createData() {
        Sport sport1 = new Sport();
        sport1.setName("Football");

        List<Sportsman> sportsmen1 = new ArrayList<>();
        sportsmen1.add(new Sportsman("Cristiano Ronaldo", 95, sport1));
        sportsmen1.add(new Sportsman("Robert Lewandowski", 94, sport1));
        sportsmen1.add(new Sportsman("Leo Messi", 94, sport1));
        sportsmen1.add(new Sportsman("Neymar", 91, sport1));
        sportsmen1.add(new Sportsman("Kylian Mbappe", 92, sport1));

        sport1.setSportsmen(sportsmen1);

        Sport sport2 = new Sport();
        sport2.setName("Basketball");

        List<Sportsman> sportsmen2 = new ArrayList<>();
        sportsmen2.add(new Sportsman("Lebron James", 95, sport2));
        sportsmen2.add(new Sportsman("Steph Curry", 94, sport2));
        sportsmen2.add(new Sportsman("Michael Jordan", 94, sport2));
        sportsmen2.add(new Sportsman("Kobe Bryant", 93, sport2));
        sportsmen2.add(new Sportsman("Shaquille O'Neal", 91, sport2));

        sport2.setSportsmen(sportsmen2);

        Sport sport3 = new Sport();
        sport3.setName("Tennis");

        List<Sportsman> sportsmen3 = new ArrayList<>();
        sportsmen3.add(new Sportsman("Roger Federer", 96, sport3));
        sportsmen3.add(new Sportsman("Rafael Nadal", 95, sport3));
        sportsmen3.add(new Sportsman("Novak Djokovic", 97, sport3));
        sportsmen3.add(new Sportsman("Andy Murray", 90, sport3));

        sport3.setSportsmen(sportsmen3);

        Sport sport4 = new Sport();
        sport4.setName("Ice Hockey");

        List<Sportsman> sportsmen4 = new ArrayList<>();
        sportsmen4.add(new Sportsman("Wayne Gretzky", 99, sport4));
        sportsmen4.add(new Sportsman("Sidney Crosby", 96, sport4));
        sportsmen4.add(new Sportsman("Alexander Ovechkin", 95, sport4));
        sportsmen4.add(new Sportsman("Mario Lemieux", 97, sport4));
        sportsmen4.add(new Sportsman("Patrick Roy", 93, sport4));

        sport4.setSportsmen(sportsmen4);

        Sport sport5 = new Sport();
        sport5.setName("Swimming");

        List<Sportsman> sportsmen5 = new ArrayList<>();
        sportsmen5.add(new Sportsman("Michael Phelps", 99, sport5));
        sportsmen5.add(new Sportsman("Ryan Lochte", 93, sport5));
        sportsmen5.add(new Sportsman("Caeleb Dressel", 94, sport5));
        sportsmen5.add(new Sportsman("Katie Ledecky", 95, sport5));

        sport5.setSportsmen(sportsmen5);

        List<Sport> sports = new ArrayList<>();
        sports.add(sport1);
        sports.add(sport2);
        sports.add(sport3);
        sports.add(sport4);
        sports.add(sport5);

        return sports;
    }

    private static void task2(List<Sport> sports) {
        sports.forEach(sport -> {
            System.out.println(sport);
            sport.getSportsmen().forEach(System.out::println);
        });
    }

    private static Set<Sportsman> task3(List<Sport> sports) {
        Set<Sportsman> allSportsmen = sports.stream()
                .flatMap(sport -> sport.getSportsmen().stream())
                .collect(Collectors.toSet());

        allSportsmen.forEach(System.out::println);

        return allSportsmen;
    }

    private static void task4(Set<Sportsman> allSportsmen) {
        allSportsmen.stream()
                .filter(sportsman -> sportsman.getRating() > 94)
                .sorted(Comparator.comparing(Sportsman::getName))
                .forEach(System.out::println);
    }

    private static void task5(Set<Sportsman> allSportsmen) {
        List<SportsmanDto> dtoList = allSportsmen.stream()
                .map(sportsman -> SportsmanDto.builder()
                        .name(sportsman.getName())
                        .rating(sportsman.getRating())
                        .sport(sportsman.getSport().getName())
                        .build())
                .sorted(Comparator.comparing(SportsmanDto::getName))
                .collect(Collectors.toList());

        dtoList.forEach(System.out::println);
    }

    private static void task6(List<Sport> sports) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sports.dat"))) {
            oos.writeObject(sports);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sports.dat"))) {
            List<Sport> deserializedSports = (List<Sport>) ois.readObject();
            deserializedSports.forEach(sport -> {
                System.out.println(sport);
                sport.getSportsmen().forEach(System.out::println);
            });
        }
    }

    private static void task7(List<Sport> sports) {
        ForkJoinPool customThreadPool = new ForkJoinPool(sports.size()/2);

        customThreadPool.submit(() -> {
            sports.parallelStream().forEach(sport -> {
                try {
                    System.out.println("Processing " + sport.getName());
                    Thread.sleep(500);
                    sport.printAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }).join();

        customThreadPool.shutdown();
    }
}