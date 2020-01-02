package com.demo.springboot.service.impl;
import java.io.*;
import java.util.ArrayList;
public class QuizCode {

    private static ArrayList<quiz.Questions> questionsList;

    private static final String fileName = "quiz.csv";
    private static String SEPERATOR = "/n";
    private static final String SPLIT_CHAR = ";";

    public static ArrayList<quiz.Questions> readData() {
        BufferedReader br = null;
        questionsList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((SEPERATOR = br.readLine()) != null) {
                String[] tab = SEPERATOR.split(SPLIT_CHAR);
                quiz.Questions pyt = new quiz.Questions(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7]);
                questionsList.add(pyt);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Plik jest pusty");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return questionsList;
    }
}