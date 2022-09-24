package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {

    static boolean istest = false;

    public static void main(String[] args) throws InterruptedException {

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
                case "-t" -> istest = true;
            }
        }
        if (!isCallHelp) {
            System.out.printf("Параметры программы: Работаем %d минут" + endingCalculation(workMin % 10, 1) + ", " +
                    "отдыхаем %d минут" + endingCalculation(workMin % 10, 1) +
                    ", количество повторений - %d\n\n", workMin, breakMin, count);
        }
        long startTime = System.currentTimeMillis();
        for(i = 1; i <= count; i++) {
            timer(workMin, breakMin, sizePrint);
        }
        long endTime = System.currentTimeMillis();

        long passedMin = (endTime - startTime) / (1000 * 60);
        System.out.println("Прошло " + passedMin + " минут" + endingCalculation(passedMin, 2));
    }

    private static void timer(int workTime, int breakTime, int sizeProgressBar) throws InterruptedException {
        printProgress ("Время вкалывать: ", workTime, sizeProgressBar);
        printProgress ("Время отдыхать: ", breakTime, sizeProgressBar);
    }

    private static String endingCalculation(long number, int order) throws InterruptedException {

        String ending = "";

        //Если в разряде десятков - единица (например, с 10 до 19, со 110 до 119 и т.д.), то окончание отсутствует
        if ((number % 100) >= 10 && (number % 100) < 20) {
            ending = "";
        }

        //В остальных случаях окончание зависит от последней цифры
         switch ((int) number) {
            case 1 -> {
                if (order == 1) {
                    ending = "у";
                }
                if (order == 2) {
                    ending = "а";
                }
            }
            case 2, 3, 4 -> ending = "ы";
            default -> ending = "";
        }
        return ending;
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length = 60 * time / size;
        int rep = 60 * time / length;
        int stretchb = size / (3 * time);
        for (int i = 1; i<=rep; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x / w) * 1000;
            x /= stretchb;
            x *=10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]     ( " + x + "min / " + time + "min )" + "\r");
            if (!istest) {
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
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
        System.out.println(
                "     -t - тестовый режим");
    }
}
