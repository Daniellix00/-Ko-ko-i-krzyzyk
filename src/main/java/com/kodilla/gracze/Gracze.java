package com.kodilla.gracze;
import java.util.Scanner;
public class Gracze {
    private String imie;
    private char symbol;
    private boolean czyKomputer;

    public Gracze(String imie, char symbol, boolean czyKomputer) {
        this.imie = imie;
        this.symbol = symbol;
        this.czyKomputer = czyKomputer;
    }
    public static Gracze utworzGracza(Scanner scanner, int numerGracza, char symbolGracza1,boolean czyKomputer){
        try {
            System.out.println("Podaj imie gracza " + numerGracza + ':');
            String imie = scanner.nextLine();
            char symbol;
            do {
                symbol = wczytajSymbol(scanner, numerGracza);
                if (symbol == symbolGracza1) {
                System.out.println("Symbol zajety! Wybierz inny symbol.");
                }
            } while (symbol == symbolGracza1);

            return new Gracze(imie, symbol, czyKomputer);
        } catch (Exception e) {
            System.out.println("Wprowadzono nieprawidłowe dane. Spróbuj ponownie.");
            return null;
        }
    }
    private static char wczytajSymbol(Scanner scanner, int numerGracza) {
        char symbol = 0;
        boolean poprawnySymbol = false;
        while (!poprawnySymbol) {
            try {
                System.out.println("Podaj symbol gracza " + numerGracza + " (X lub O):");
                String input = scanner.nextLine().toUpperCase();
                if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                    symbol = input.charAt(0);
                    poprawnySymbol = true;
                } else {
                    System.out.println("Wprowadź poprawny symbol (X lub O).");
                }
            } catch (Exception e) {
                System.out.println("Wprowadzono nieprawidłowe dane. Spróbuj ponownie.");
            }
        }
        return symbol;
    }
    public String getImie() {
        return imie;
    }
    public char getSymbol() {
        return symbol;
    }
    public  boolean czyKomputer(){
    return czyKomputer;
}}