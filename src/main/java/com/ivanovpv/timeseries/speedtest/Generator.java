/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivanovpv.timeseries.speedtest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author User
 */
public class Generator {
    int row, column;
    File file;

    public void Generator() {
    }

    public void Gen_1() {
        for (int i = 0; i < 1000; i++) {
            row = (int) (Math.random() * 1000000);
            column = (int) (Math.random() * 500);
            setFile(column + "	" + row + "\n", file);
        }
    }

    public void Gen_100() {
        for (int j = 0; j < 1000; j++) {
            row = (int) (Math.random() * 1000000);
            column = (int) (Math.random() * 500);
            setFile(column + "	" + row + "\n", file);
            for (int i = 0; i < 99; i++) {
                if (row == 999999)
                    row = -100; //избегаем выхода за границы
                row++;
                setFile(column + "	" + row + "\n", file);
            }
        }
    }

    public void Gen_1000() {
        for (int j = 0; j < 100; j++) {
            row = (int) (Math.random() * 1000000);
            column = (int) (Math.random() * 500);
            setFile(column + "	" + row + "\n", file);
            ;
            for (int i = 0; i < 999; i++) {
                if (row == 999999)
                    row = -1000; //избегаем выхода за границы
                row++;
                setFile(column + "	" + row + "\n", file);
            }
        }
    }

    public void Gen_10000() {
        for (int j = 0; j < 10; j++) {
            row = (int) (Math.random() * 1000000);
            column = (int) (Math.random() * 500);
            setFile(column + "	" + row + "\n", file);
            for (int i = 0; i < 9999; i++) {
                if (row == 999999)
                    row = -10000; //избегаем выхода за границы
                row++;
                setFile(column + "	" + row + "\n", file);
            }
        }
    }

    public void setFile(String str, File file) {
        try (FileWriter writer = new FileWriter(file + ".txt", true); BufferedWriter buff = new BufferedWriter(writer)) {
            buff.write(str);
            buff.flush();
        } catch (IOException e) {
            throw new UnsupportedOperationException("Ошибка записи файла: " + e.getMessage());
        }
    }
}
