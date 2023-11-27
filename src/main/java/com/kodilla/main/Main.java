package com.kodilla.main;
import com.kodilla.gracze.Gracze;
import com.kodilla.mechanika.Gra;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gracze gracz1= Gracze.utworzGracza(scanner,1,' ', false);
        Gracze gracz2 = Gracze.utworzGracza(scanner,2, gracz1.getSymbol(), false);
        Gra gra = new Gra(gracz1, gracz2);

        while (!gra.czyGraSkonczona()) {
            gra.wykonajRuch();
        }
}}
    



