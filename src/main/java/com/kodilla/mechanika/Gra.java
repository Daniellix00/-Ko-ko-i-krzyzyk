package com.kodilla.mechanika;
import com.kodilla.Plansza.Plansza;
import com.kodilla.gracze.Gracze;
import java.util.Scanner;
public class Gra {
    private Plansza plansza;
    private Gracze gracz1;
    private Gracze gracz2;
    private Gracze aktualnyGracz;
public Gra(Gracze gracz1, Gracze gracz2){

   this.plansza = new Plansza();
    this.gracz1 = gracz1;
    this.gracz2 = gracz2;
    aktualnyGracz = gracz1;

}
public  boolean wykonajRuch( ) {
    plansza.drukujPlansze();
    System.out.println(aktualnyGracz.getImie() + ", Twoj ruch");
    int wiersz, kolumna;
    if (aktualnyGracz.czyKomputer()) {
        RuchKomputera ruchKomputera = plansza.wykonajRuchKomputera(aktualnyGracz);
        System.out.println("Komputer wybrał: Wiersz " + (ruchKomputera.getWiersz() + 1) + ", Kolumna " + (ruchKomputera.getKolumna() + 1));
    } else {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Podaj indeks wiersza:");
            wiersz = scanner.nextInt();
            System.out.println("Podaj indeks kolumny:");
            kolumna = scanner.nextInt();
        } while (!plansza.wykonajRuch(wiersz - 1, kolumna - 1, aktualnyGracz)); // -1, ponieważ użytkownik podaje indeksy od 1, a indeksowanie w tablicy zaczyna się od 0
    }
        if (plansza.czyJestZwyciezca()) {
            if (aktualnyGracz.czyKomputer()) {
                System.out.println("Komputer wygrywa!");
            } else {
                System.out.println("Gratulacje, " + aktualnyGracz.getImie() + "! Wygrywasz!");
            }
            plansza.drukujPlansze();
        } else if (plansza.czyPlanszaPelna()) {
            System.out.println("Remis! Plansza jest pełna.");
        }

        // Przełącz gracza tylko jeśli gra jeszcze się nie zakończyła
        if (!plansza.czyGraSkonczona()) {
            aktualnyGracz = (aktualnyGracz == gracz1) ? gracz2 : gracz1;
        } else {
            System.out.println("Koniec gry.");
        }
        return true;
    }
    public boolean czyGraSkonczona() {
        return plansza.czyGraSkonczona();
    }
}




