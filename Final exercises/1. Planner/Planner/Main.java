import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        int rozmiar;
        Scanner scnr = new Scanner(System.in);
        Scanner scnr2 = new Scanner(System.in);
        char c;
        int liczbaRR;
        int[][] dane;
        int[] dane2;
        String buf;
        if (args.length != 0){
            File plik = new File(args[0]);
            if (plik.canRead() == false) {
                System.out.println("Plik z danymi nie jest dostępny.");
                return;
            }
            else {
                try {
                    scnr = new Scanner(plik);
                    scnr2 = new Scanner(plik);
                }
                catch (FileNotFoundException ex) {
                    System.out.println("Plik z danymi nie jest dostępny.");
                    return;
                }
            }
        }

        buf = scnr.nextLine();
        if (!buf.matches("[0-9]+")) {
            System.out.println("Błąd w wierszu 1 : Zły rozmiar.");
            return;
        }
        rozmiar = scnr2.nextInt();
        dane = new int[rozmiar][2];

        for (int i = 0; i < rozmiar; i++){
            buf = scnr.nextLine();
            if (!buf.matches("[0-9]+ [0-9]+")) {
                System.out.println("Błąd w wierszu " + (i + 2) + ": Złe dane.");
                return;
            }

            dane[i][0] = scnr2.nextInt();

            if (i > 0 && dane[i-1][0] > dane[i][0]){
                System.out.println("Błąd w wierszu " + (i + 2) + ": brak zachowanej chronologii w czasach dodania do kolejki.");
                return;
            }

            dane[i][1] = scnr2.nextInt();

            if (dane[i][1] < 1){
                System.out.println("Błąd w wierszu" + (i + 2) + ": zła liczba.");
                return;
            }
        }

        buf = scnr.nextLine();
        if (!buf.matches("[0-9]+")) {
            System.out.println("Błąd w wierszu" + (rozmiar + 2) + ": Zła liczba.");
            return;
        }

        liczbaRR = scnr2.nextInt();

        if (liczbaRR < 1){
            System.out.println("Błąd w wierszu" + (rozmiar + 2) + ": zła liczba.");
            return;
        }

        dane2 = new int[liczbaRR];

        for (int i = 0; i < liczbaRR; i++){
            if (scnr2.hasNextInt() == false){
                System.out.println("Błąd w wierszu" + (rozmiar + 3) + ": Brak liczby.");
                return;
            }

            dane2[i] = scnr2.nextInt();

            if (dane2[i] < 0){
                System.out.println("Błąd w wierszu" + (rozmiar + 3) + ": zła liczba.");
                return;
            }
        }


        FCFS strat1 = new FCFS(dane, rozmiar);
        System.out.print(strat1.dajPrzebieg());
        SJF strat2 = new SJF(dane, rozmiar);
        System.out.print(strat2.dajPrzebieg());
        SRT strat3 = new SRT(dane, rozmiar);
        System.out.print(strat3.dajPrzebieg());
        PS strat4 = new PS(dane, rozmiar);
        System.out.print(strat4.dajPrzebieg());
        RR strat5;
        for (int i = 0; i < liczbaRR; i++){
            strat5 = new RR(dane, rozmiar, dane2[i]);
            System.out.print(strat5.dajPrzebieg());
        }
    }
}
