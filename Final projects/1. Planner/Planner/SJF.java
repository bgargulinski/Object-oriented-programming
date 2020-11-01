import java.util.Locale;

public class SJF extends Strategia {

    public SJF(int[][] dane, int rozmiar){
        super.dane = dane;
        super.rozmiar = rozmiar;
    }

    private String Przebieg (){
        String s = "Strategia: SJF\n";
        double suma = 0;
        double srednia1 = 0;
        double srednia2 = 0;
        int rekordZap;
        int rekordCzas = 0;
        int rekordId = 0;
        boolean[] oznaczenia = new boolean[super.rozmiar];
        int k = 0;
        for(int i = 0; i < this.rozmiar; i++){
            for (; k < super.rozmiar && oznaczenia[k] == true ; k++){}
            if (k < super.rozmiar) {
                rekordCzas = super.dane[k][0];
                rekordZap = super.dane[k][1];
                rekordId = k;
                for (int j = k + 1; j < super.rozmiar && super.dane[j][0] == rekordCzas; j++) {
                    if (oznaczenia[j] == false){
                        if (rekordZap > super.dane[j][1]) {
                            rekordZap = super.dane[j][1];
                            rekordId = j;
                        }
                    }
                }
                if (oznaczenia[rekordId] == false){
                    oznaczenia[rekordId] = true;
                    if (super.dane[rekordId][0] > suma) {
                        srednia1 += super.dane[rekordId][0];
                        srednia2 += super.dane[rekordId][0] - suma;
                        suma = dane[rekordId][0];
                    }
                    suma += dane[rekordId][1];
                    srednia1 += suma - dane[rekordId][0];
                    if (i == this.rozmiar - 1)
                        srednia2 = -1 * suma;
                    s += "[" + (rekordId + 1);
                    s += " " + super.dane[rekordId][0] + " " + String.format(Locale.US, "%.2f", suma) + "]";
                }
            }
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
