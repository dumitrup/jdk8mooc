/**
 * Copyright © 2014, Oracle and/or its affiliates. All rights reserved.
 * <p>
 * JDK 8 MOOC Lesson 1 homework
 */
package uk.panaghiea.lesson1;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Speakjava (simon.ritter@oracle.com)
 */
public class Lesson1 {
    private Integer elementsPrinted;
    private ExecutorService executor;

    public Lesson1() {
        elementsPrinted = 0;
    }

    /**
     * Run the exercises to ensure we got the right answers
     */
    public void runExercises() throws InterruptedException {
        System.out.println("JDK 8 Lambdas and Streams MOOC Lesson 1");
        System.out.println("Running exercise 1 solution...");
        exercise1();
        System.out.println("Running exercise 2 solution...");
        exercise2();
        System.out.println("Running exercise 3 solution...");
        exercise3();
        System.out.println("Running exercise 4 solution...");
        exercise4();
        System.out.println("Running exercise 5 solution...");
        exercise5();
    }

    /**
     * All exercises should be completed using Lambda expressions and the new
     * methods added to JDK 8 where appropriate. There is no need to use an
     * explicit loop in any of the code. Use method references rather than full
     * lambda expressions wherever possible.
     */
    /**
     * Exercise 1
     *
     * Create a string that consists of the first letter of each word in the list
     * of Strings provided.
     */
    private void exercise1() {
        List<String> list = Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot");
        System.out.println(getFirstLetters(list));
    }

    public String getFirstLetters(List<String> words) {
        final StringBuilder firstLetters = new StringBuilder();
        words.forEach(s -> firstLetters.append(s.charAt(0)));
        return firstLetters.toString();
    }

    /**
     * Exercise 2
     *
     * Remove the words that have odd lengths from the list.
     */
    private void exercise2() {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));
        System.out.println(removeOddLengthWords(list));
    }

    public List<String> removeOddLengthWords(List<String> words) {
        words.removeIf(s -> (s.length() & 1) == 1);
        return words;
    }

    /**
     * Exercise 3
     *
     * Replace every word in the list with its upper case equivalent.
     */
    private void exercise3() {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));
        System.out.println(replaceWordsWithUpperCase(list));
    }

    public List<String> replaceWordsWithUpperCase(List<String> words) {
        words.replaceAll(String::toUpperCase);
        return words;
    }

    /**
     * Exercise 4
     *
     * Convert every key-value pair of the map into a string and append them all
     * into a single string, in iteration order.
     */
    private void exercise4() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 3);
        map.put("b", 2);
        map.put("a", 1);
        System.out.println(convertMapToString(map));
    }

    public String convertMapToString(Map<String, Integer> map) {
        StringBuilder convertedMap = new StringBuilder();
        map.forEach((k, v) -> convertedMap.append(k).append(v));
        return convertedMap.toString();
    }

    /**
     * Exercise 5
     *
     * Create a new thread that prints the numbers from the list.
     */
    private void exercise5() throws InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        printNumbersWithExecutor(list);
        printNumbersWithThread(list);
    }

    public Future<Integer> printNumbersWithExecutor(List<Integer> numbers) throws InterruptedException {
        Future<Integer> future = executor.submit(
                () -> { numbers.forEach( s -> {System.out.print(s); elementsPrinted++;}); return elementsPrinted;}
        );
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        return future;
    }

    public void printNumbersWithThread(List<Integer> numbers) {
        new Thread(()-> {numbers.forEach(System.out::print);}).start();
    }

    /**
     * Main entry point for application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Lesson1 lesson = new Lesson1();
        lesson.setExecutor(Executors.newFixedThreadPool(1));
        lesson.runExercises();
    }
    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
}
