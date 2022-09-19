package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Pomodoro {

    public static void main(String[] args) {

        /*
        -w 1 -b 1 -count 1

         */
        System.out.println("Привет! Введи команду");
        System.out.println("Для получения подсказки введите команду -help");
        //Ввод данных и нарезка

        // Валидация наличия необходимых команд
        boolean validationCheck = false;
        String[] cmd;

        do {
            cmd = new Scanner(System.in).nextLine().split(" ");
            if (!Arrays.asList(cmd).contains("-w")) {
                System.out.println("Среди команд отсутствует команда -w. Повторите ввод.");
                continue;
            }
            if (!Arrays.asList(cmd).contains("-b")) {
                System.out.println("Среди команд отсутствует команда -b. Повторите ввод.");
                continue;
            }
            if (!Arrays.asList(cmd).contains("-count")) {
                System.out.println("Среди команд отсутствует команда -count. Повторите ввод.");
                continue;
            }
            validationCheck = true;
        }
        while (!validationCheck);

        int workMin = 25; //Время работы
        int breakMin = 5; //Время отдыха
        int count = 1; //Количество подходов
        boolean isCallHelp = false;
        int sizePrint = 30;

        int i = 0;
        for(i=0; i<cmd.length; i++) {
            switch(cmd[i]) {
                case "-help" -> {
                    isCallHelp = true;
                    helpMessage();
                }
                case "-w" -> workMin = Integer.parseInt(cmd[++i]);
                case "-b" -> breakMin = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
            }
        }
        if (!isCallHelp) {
            System.out.printf("Параметры программы: Работаем %d минут, " +
                    "отдыхаем %d минут, количество повторений - %d\n\n", workMin, breakMin, count);
        }
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();

        System.out.println("Прошло " + (endTime - startTime)/(1000*60) + " минут");
    }

    private static void timer(int workTime, int breakTime, int sizeProgressBar) {

    }

    private static void helpMessage() {
        System.out.println(
                "\n\nЭто Pomodoro! Сделай свое время более эффективным!\n");
        System.out.println(
                "Список команд:\n");
        System.out.println(
                "     -help - информация о программе");
        System.out.println(
                "     -w - время работы в минутах");
        System.out.println(
                "     -b - время отдыха в минутах");
        System.out.println(
                "     -count - количество повторов");
    }
}
