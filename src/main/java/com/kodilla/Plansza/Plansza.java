package com.kodilla.Plansza;
import com.kodilla.gracze.Gracze;
import com.kodilla.mechanika.RuchKomputera;
import java.util.*;
public class Plansza {
    private char[][] plansza;
    public char[][] getPlansza() {
        return plansza;
    }
    private Scanner scanner = new Scanner(System.in);
public Plansza(){ inicjalizujPlansze();
}
    public void drukujPlansze() {
        if (plansza == null) {
            inicjalizujPlansze();
        }
        // Drukowanie nagłówków i wierszy
        System.out.print("\t");
        for (int i = 1; i <= plansza.length; i++) {
        System.out.print(i + "\t");
    }
        System.out.println();
        for (int wiersz = 0; wiersz < plansza.length; wiersz++) {
        System.out.print((wiersz + 1) + ":\t");
        for (int kolumna = 0; kolumna < plansza.length; kolumna++) {
            System.out.print(plansza[wiersz][kolumna] + "\t");
        }
        System.out.println();
    }
}
   private void   inicjalizujPlansze() {

        System.out.println("Witajcie! Niech pierwszy gracz poda wymiar planszy");
        boolean poprawny = false;

        do {
            try {
                int wymiar = scanner.nextInt();
                plansza = new char[wymiar][wymiar];
                for (int i = 0; i < wymiar; i++) {
                    for (int j = 0; j < wymiar; j++) {
                        plansza[i][j] = ' ';
                    }
                }
                poprawny = true;

            } catch (InputMismatchException e) {
                System.out.println("Blad. Wprowadz liczbe calkowita");
                scanner.nextLine(); // Konsumuj błędne dane wejściowe
            }
        } while (!poprawny);
    }
    public boolean wykonajRuch(int wiersz, int kolumna, Gracze aktualnyGracz) {
        if (plansza == null) {
            System.out.println("Blad: Plansza nie zostala zainicjowana.");
            return false;
        }

        if (wiersz < 0 || wiersz >= plansza.length || kolumna < 0 || kolumna >= plansza[0].length) {
            System.out.println("Blad: Podane wspolrzedne znajduja sie poza zakresem planszy.");
            return false;
        }

        if (plansza[wiersz][kolumna] == ' ') {
            plansza[wiersz][kolumna] = aktualnyGracz.getSymbol();
            return true;
        } else {
            System.out.println("To pole jest już zajete. Wybierz inne.");
            return false;
        }
    }
    public RuchKomputera wykonajRuchKomputera(Gracze komputer) {
        List<RuchKomputera> dostepneRuchy = new ArrayList<>();

        // Sprawdź dostępne puste pola
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                if (plansza[i][j] == ' ') {
                    dostepneRuchy.add(new RuchKomputera(i, j));
                }
            }
        }

        if (!dostepneRuchy.isEmpty()) {
           RuchKomputera ruchKomputera = dostepneRuchy.get(new Random().nextInt(dostepneRuchy.size()));
            plansza[ruchKomputera.getWiersz()][ruchKomputera.getKolumna()] = komputer.getSymbol();
            return ruchKomputera;
        } else {
            // Jeśli plansza jest pełna, zwróć dowolny ruch (nie ma już dostępnych pustych pól)
            return new RuchKomputera(0, 0);
        }
    }
    public boolean czyJestZwyciezca() {

        return sprawdzWiersze() || sprawdzKolumny() || sprawdzPrzekatne();
    }
    private boolean sprawdzWiersze() {
        for (int wiersz = 0; wiersz < plansza.length; wiersz++) {
            char pierwszyZnak = plansza[wiersz][0];
            if (pierwszyZnak != ' ') {
                boolean wygrana = true;
                for (int kolumna = 1; kolumna < plansza[wiersz].length; kolumna++) {
                    if (plansza[wiersz][kolumna] != pierwszyZnak) {
                        wygrana = false;
                        break;
                    }
                }
                if (wygrana) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean sprawdzKolumny() {
        for (int kolumna = 0; kolumna < plansza[0].length; kolumna++) {
            char pierwszyZnak = plansza[0][kolumna];
            if (pierwszyZnak != ' ') {
                boolean wygrana = true;
                for (int wiersz = 1; wiersz < plansza.length; wiersz++) {
                    if (plansza[wiersz][kolumna] != pierwszyZnak) {
                        wygrana = false;
                        break;
                    }
                }
                if (wygrana) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean sprawdzPrzekatne() {
        char pierwszyZnak = plansza[0][0];
        if (pierwszyZnak != ' ') {
            boolean wygranaPrzekatna1 = true;
            boolean wygranaPrzekatna2 = true;

            for (int i = 1; i < plansza.length; i++) {
                // Sprawdź przekątne planszy
                if (plansza[i][i] != pierwszyZnak) {
                    wygranaPrzekatna1 = false;
                }
                int drugiIndeks = plansza.length - 1 - i;
                if (plansza[i][drugiIndeks] != pierwszyZnak) {
                    wygranaPrzekatna2 = false;
                }
            }

            return wygranaPrzekatna1 || wygranaPrzekatna2;
        }
        return false;
    }
    public boolean czyPlanszaPelna() {
        for (int wiersz = 0; wiersz < plansza.length; wiersz++) {
            for (int kolumna = 0; kolumna < plansza[wiersz].length; kolumna++) {
                if (plansza[wiersz][kolumna] == ' ') {
                    return false; // Znaleziono puste pole, plansza nie jest pełna
                }
            }
        }
        return true; // Nie znaleziono pustego pola, plansza jest pełna
    }
    public boolean czyGraSkonczona() {
        return czyJestZwyciezca() || czyPlanszaPelna();
    }
}