import java.util.Locale;


public class FCFS extends Strategia {

    public FCFS(int[][] dane, int rozmiar){
        super.dane = dane;
        super.rozmiar = rozmiar;
    }

    private String Przebieg (){
        String s = "Strategia: FCFS\n";
        double suma = 0;
        double srednia1 = 0;
        double srednia2 = 0;
        for (int i = 0; i < super.rozmiar; i++){
            if (super.dane[i][0] > suma) {
                srednia1 += super.dane[i][0];
                srednia2 += super.dane[i][0] - suma;
                suma = dane[i][0];
            }
            suma += dane[i][1];
            srednia1 += suma - dane[i][0];
            if (i == this.rozmiar - 1)
                srednia2 = -1 * suma;
            s += "[" + (i + 1);
            s += " " + super.dane[i][0] + " " + String.format(Locale.US, "%.2f", suma) + "]";
        }

        srednia2 +=  srednia1;
        srednia2 /= super.rozmiar;
        srednia1 /= super.rozmiar;

        s += "\n";
        s += "Średni czas obrotu: " + String.format(Locale.US, "%.2f" , srednia1) + "\n";
        s += "Średni czas oczekiwania: " + String.format(Locale.US, "%.2f" , srednia2) +"\n";

        return s;
    }

    public String dajPrzebieg(){
        return this.Przebieg();
    }


}
