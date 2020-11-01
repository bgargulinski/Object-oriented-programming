import java.util.Locale;

public class RR extends Strategia{
    private int q;
    public RR(int[][] dane, int rozmiar, int q){
        int[][] daneKopia = new int[rozmiar][2];
        for (int i = 0; i < rozmiar; i++){
            daneKopia[i][0] = dane[i][0];
            daneKopia[i][1] = dane[i][1];
        }
        super.dane = daneKopia;
        super.rozmiar = rozmiar;
        this.q = q;
    }

    private String Przebieg (){
        String s = "Strategia: RR-" + this.q + "\n";
        double suma = 0;
        double srednia1 = 0;
        double srednia2 = 0;
        int czasDoZmiany = 0;
        int obecnyCzas = 0;
        double pamiec = 0;
        boolean[] oznaczenia = new boolean[super.rozmiar];
        int k = 0;
        while (k < super.rozmiar){
            k = 0;
            for (; k < super.rozmiar && oznaczenia[k] == true ; k++){}
            if (k < super.rozmiar) {
                if (this.dane[k][0] > suma) {
                    srednia1 += this.dane[k][0] - suma;
                    suma = this.dane[k][0];
                }
                for (int i = 0; i < super.rozmiar && super.dane[i][0] <= suma; i++) {
                    if (oznaczenia[i] == false) {
                        if (super.dane[i][1] <= this.q) {
                            oznaczenia[i] = true;
                            suma += super.dane[i][1];
                            srednia1 += suma;
                            pamiec = -1 * suma;
                            s += "[" + (i + 1);
                            s += " " + super.dane[i][0] + " " + String.format(Locale.US, "%.2f" , suma) + "]";
                        }
                        else {
                            super.dane[i][1] -= this.q;
                            suma += this.q;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < super.rozmiar; i++){
            srednia1 -= super.dane[i][0];
        }
        srednia2 +=  srednia1 + pamiec;
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
