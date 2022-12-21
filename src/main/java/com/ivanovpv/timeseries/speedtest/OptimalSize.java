package com.ivanovpv.timeseries.speedtest;

import com.ivanovpv.timeseries.interfaces.TrendRepository;
import com.ivanovpv.timeseries.repository.MySQLRepository;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OptimalSize {
    public static TrendRepository repository = new MySQLRepository();

    public static void main(String[] args) {

        Generator g = new Generator();

        //Указываем путь к каждому файлу теста
        File test1 = new File("C:\\Users\\Pavel\\Desktop\\tests\\Gen1.txt"); //случайный доступ
        File test2 = new File("C:\\Users\\Pavel\\Desktop\\tests\\Gen100.txt"); //тест блоками по 100 последовательных
        File test3 = new File("C:\\Users\\Pavel\\Desktop\\tests\\Gen1000.txt"); //тест блоками по 1000 последовательных
        File test4 = new File("C:\\Users\\Pavel\\Desktop\\tests\\Gen10000.txt"); //тест блоками по 10000 последовательных
        File[] tests = {test1, test2, test3, test4};


        File file_results = new File("C:\\Users\\Pavel\\Desktop\\tests\\resultsInflux.txt");  //путь файла итогов тестирования
        String[] numbers = null;

        int[] rows = new int[100000];
        int[] params = new int[100000];

        int[] buff_rows;
        int[] buff_params;

        for (int k = 0; k < 4; k++) { //цикл прохождения всех 4 тестов
            try (BufferedReader br = new BufferedReader(new FileReader(tests[k]))) {
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                    numbers = line.split("	");
                    rows[count] = Integer.parseInt(numbers[1]);
                    params[count] = Integer.parseInt(numbers[0]);
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int j = 0; j < 9; j++) { //цикл просмотра всех 9 трендов

                repository.setCurTest(j+1);
                buff_params = Scaling.scalingParams(params, repository.getCurParamNames().size()); //масштабирование по параметрам (столбцам)
                buff_rows = Scaling.scalingRows(rows, repository.getRowCountForTrend()); //масштабирование по строкам

                System.out.println();

                long start = System.currentTimeMillis();
                long ms = System.currentTimeMillis();
                for (int i = 0; i < rows.length; i++) {
                    repository.getValueForTrend(buff_rows[i], buff_params[i]);

                    if (i % 10000 == 0 && i != 0) {
                        ms = System.currentTimeMillis() - ms;
                        Logger.getLogger(OptimalSize.class.getName()).log(Level.INFO,
                                "Read 10000 lines in " + ms + " ms. Already read " + i + " lines from file " + tests[k].getName() + " in test " + repository.getValueForTest(j, 1));
                        ms = System.currentTimeMillis();
                    }
                }
                long end = System.currentTimeMillis();

                double result = end - start;
                System.out.println(result);
                String format = String.format("%f", result / 100000);
                g.setFile("ТРЕНД " + (j + 1) + " " + repository.getRowCountForTrend() + "/" + repository.getCurParamNames().size() + ". Время на 100000 обращений к БД  " + result + " ms Время на одно обращение " + format + " ms" + "\n", file_results);
            }
        }
    }
}

