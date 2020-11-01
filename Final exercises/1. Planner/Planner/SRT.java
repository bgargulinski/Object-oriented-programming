import java.util.Locale;

public class SRT extends Strategia{
    public SRT(int[][] dane, int rozmiar){
        int[][] daneKopia = new int[rozmiar][2];
        for (int i = 0; i < rozmiar; i++){
            daneKopia[i][0] = dane[i][0];
            daneKopia[i][1] = dane[i][1];
        }
        super.dane = daneKopia;
        super.rozmiar = rozmiar;
    }

    private String Przebieg (){
            String s = "Strategia: SRT\n";
            double suma = 0;
            double srednia1 = 0;
            double srednia2 = 0;
            int rekordZap;
            int rekordId = 0;
            int czasDoZmiany = 0;
            int obecnyCzas = 0;
            double pamiec = 0;
            int j = 0;
            boolean[] oznaczenia = new boolean[super.rozmiar];
            int k = 0;
            while (k < super.rozmiar){
                for (; k < super.rozmiar && oznaczenia[k] == true ; k++){}
                if (k < super.rozmiar)
                    obecnyCzas = super.dane[k][0];
                if (czasDoZmiany == 0) {
                    for (j = k; j < super.rozmiar; j++) {
                        if (oznaczenia[j] == false && dane[j][0] > obecnyCzas) {
                            czasDoZmiany = dane[j][0] - obecnyCzas;
                            break;
                        }
                    }
                }
                if (czasDoZmiany == 0 || j == super.rozmiar)
                    czasDoZmiany = Integer.MAX_VALUE;

                if (k < super.rozmiar) {
                    rekordZap = super.dane[k][1];
                    rekordId = k;
                    for (int l = k + 1; l < super.rozmiar && super.dane[l][0] <= suma; l++) {
                        if (oznaczenia[l] == false){
                            if (rekordZap > super.dane[l][1]) {
                                rekordZap = super.dane[l][1];
                                rekordId = l;
                            }
                        }

                    }
                    if (oznaczenia[rekordId] == false){
                        if (super.dane[rekordId][0] > suma) {
                            srednia1 += super.dane[rekordId][0];
                            srednia2 += super.dane[rekordId][0] - suma;
                            suma = dane[rekordId][0];
                        }
                        if (czasDoZmiany >= dane[rekordId][1]) {
                            czasDoZmiany -= dane[rekordId][1];
                            suma += dane[rekordId][1];
                            oznaczenia[rekordId] = true;
                        }
                        else {
                            suma += czasDoZmiany;
                            dane[rekordId][1] -= czasDoZmiany;
                            czasDoZmiany = 0;
                        }
                        srednia1 += suma;
                        pamiec = -1 * suma;
                        s += "[" + (rekordId + 1);
                        s += " " + super.dane[rekordId][0] + " " + String.format(Locale.US, "%.2f", suma) + "]";
                    }
                    else
                        suma++;
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
