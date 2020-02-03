package com.demo.springboot.service.impl;

import com.demo.springboot.domain.dto.Questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCode {

    private static ArrayList<Questions> questionsList;

    private static final String fileName = "C:\\Users\\Józef\\IdeaProjects\\project_06_koderzy_server\\src\\main\\java\\com\\demo\\springboot\\domain\\csv\\quiz.csv";
    private static String SEPERATOR = "/n";
    private static final String SPLIT_CHAR = ";";

    public static ArrayList<Questions> readData() { // wczytywywanie csv
        BufferedReader br = null;
        questionsList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((SEPERATOR = br.readLine()) != null) {
                String[] tab = SEPERATOR.split(SPLIT_CHAR);
                Questions pyt = new Questions(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7]);
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
    public static int checkAnswer(String yourAnswer, String correctAnswers, int points) {
        correctAnswers = correctAnswers.replaceAll(",", "");

        char[] yourAnswerArray = yourAnswer.toCharArray();
        char[] correctAnswersArray = correctAnswers.toCharArray();

        int countCorrect = 0;
        if (yourAnswer.length() == correctAnswers.length()) {
            for (int i = 0; i < yourAnswer.length(); i++) {
                if(yourAnswerArray[i]==correctAnswersArray[i]){
                    countCorrect++;
            }
        }
    }
        if (countCorrect==correctAnswers.length()){
            return points;
        }
        else{
            return 0;
        }

    }

    }

