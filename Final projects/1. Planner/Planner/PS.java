import java.util.Locale;

public class PS extends Strategia {
    private double[][] dane;
    public PS(int[][] dane, int rozmiar){
        double[][] daneKopia = new double[rozmiar][2];
        for (int i = 0; i < rozmiar; i++){
            daneKopia[i][0] = dane[i][0];
            daneKopia[i][1] = dane[i][1];
        }
        this.dane = daneKopia;
        super.rozmiar = rozmiar;
    }

    private String Przebieg (){
        String s = "Strategia: PS\n";
        double suma = 0;
        double srednia1 = 0;
        double srednia2 = 0;
        double obecnyCzas = 0;
        double czasDoZmiany = 0;
        int j = 0;
        int liczbaProcesow;
        double rekord;
        boolean[] oznaczenia = new boolean[super.rozmiar];
        int k = 0;

        for(int i = 0; i < super.rozmiar + 2; i++){
            for (; k < super.rozmiar && oznaczenia[k] == true ; k++){}
            if (k < super.rozmiar)
                obecnyCzas = this.dane[k][0];
            if (czasDoZmiany == 0) {
                for (j = k; j < super.rozmiar; j++) {
                    if (oznaczenia[j] == false && dane[j][0] > suma) {
                        czasDoZmiany = dane[j][0] - obecnyCzas;
                        break;
                    }
                }
            }
            if (czasDoZmiany == 0 || j == super.rozmiar)
                czasDoZmiany = Integer.MAX_VALUE;
            if (k < super.rozmiar) {
                if (this.dane[k][0] > suma)
                    suma = this.dane[k][0];
                liczbaProcesow = 0;
                for (int l = k; l < super.rozmiar && this.dane[l][0] <= suma; l++) {
                    if (oznaczenia[l] == false)
                        liczbaProcesow++;
                }
                rekord = Integer.MAX_VALUE;
                for (int l = k; l < super.rozmiar && this.dane[l][0] <= suma; l++) {
                    if (oznaczenia[l] == false && this.dane[l][1] < rekord)
                        rekord = this.dane[l][1];
                }
                if (rekord > czasDoZmiany / liczbaProcesow)
                    rekord = czasDoZmiany / liczbaProcesow;
                suma += rekord * liczbaProcesow;
                czasDoZmiany -= rekord * liczbaProcesow;
                for (int l = k; l < super.rozmiar && this.dane[l][0] <= suma; l++) {
                    if (oznaczenia[l] == false) {
                        this.dane[l][1] -= rekord;
                        if (this.dane[l][1] <= 0) {
                            oznaczenia[l] = true;
                            srednia1 += suma;
                            s += "[" + (l + 1);
                            s += " " + this.dane[l][0] + " " + String.format(Locale.US, "%.2f" , suma) + "]";
                        }
                    }
                }
            }
        }

        for (int i = 0; i < super.rozmiar; i++){
            srednia1 -= this.dane[i][0];
        }
        srednia2 = 0;
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
