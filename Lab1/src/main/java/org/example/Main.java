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
        List<Category> categories = createData();

        System.out.println("Task 2");
        task2(categories);

        System.out.println();

        System.out.println("Task 3");
        Set<Element> allElements = task3(categories);

        System.out.println();

        System.out.println("Task 4");
        task4(allElements);

        System.out.println();

        System.out.println("Task 5");
        task5(allElements);

        System.out.println();

        System.out.println("Task 6");
        task6(categories);

        System.out.println();

        System.out.println("Task 7");
        task7(categories);
    }

    static List<Category> createData() {
        Category category1 = new Category();
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setName("Category 2");

        List<Element> elements1 = new ArrayList<>();
        elements1.add(new Element("Element A", 25, category1));
        elements1.add(new Element("Element B", 20, category1));

        List<Element> elements2 = new ArrayList<>();
        elements2.add(new Element("Element C", 15, category2));
        elements2.add(new Element("Element D", 10, category2));

        category1.setElements(elements1);
        category2.setElements(elements2);

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        return categories;
    }

    private static void task2(List<Category> categories) {
        categories.forEach(category -> {
            System.out.println(category);
            category.getElements().forEach(System.out::println);
        });
    }

    private static Set<Element> task3(List<Category> categories) {
        Set<Element> allElements = categories.stream()
                .flatMap(category -> category.getElements().stream())
                .collect(Collectors.toSet());

        allElements.forEach(System.out::println);

        return allElements;
    }

    private static void task4(Set<Element> allElements) {
        allElements.stream()
                .filter(element -> element.getValue() > 15)
                .sorted(Comparator.comparing(Element::getName))
                .forEach(System.out::println);
    }

    private static void task5(Set<Element> allElements) {
        List<ElementDto> dtoList = allElements.stream()
                .map(element -> ElementDto.builder()
                        .name(element.getName())
                        .value(element.getValue())
                        .categoryName(element.getCategory().getName())
                        .build())
                .sorted(Comparator.comparing(ElementDto::getName))
                .collect(Collectors.toList());

        dtoList.forEach(System.out::println);
    }

    private static void task6(List<Category> categories) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("categories.dat"))) {
            oos.writeObject(categories);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("categories.dat"))) {
            List<Category> deserializedCategories = (List<Category>) ois.readObject();
            deserializedCategories.forEach(category -> {
                System.out.println(category);
                category.getElements().forEach(System.out::println);
            });
        }
    }

    private static void task7(List<Category> categories) {
        ForkJoinPool customThreadPool = new ForkJoinPool(categories.size());

        customThreadPool.submit(() -> {
            categories.parallelStream().forEach(category -> {
                try {
                    System.out.println("Processing " + category.getName());
                    Thread.sleep(500);
                    category.printAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }).join();

        customThreadPool.shutdown();
    }
}