package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Pomodoro {

    public static void main(String[] args) {

        /*
        -w 1 -b 1 -count 1

         */
        System.out.println("Привет! Введи команду");
        //Ввод данных и нарезка
        String[] cmd = new Scanner(System.in).nextLine().split(" ");
        /*
        Логирование нарезанного массива
        System.out.println(Arrays.toString(cmd));
        */

        int i = 0;
        for(i=0; i<cmd.length; i++) {
            switch(cmd[i]) {
                case "-help" -> {
                    System.out.println("Это Pomodoro!");
                }
                case "-w" -> {
                    System.out.println("Это было -w");

                }
                case "-b" -> {
                    System.out.println("Это было -b");

                }
                case "-count" -> {
                    System.out.println("Это было -count");

                }
            }
        }


    }
}
