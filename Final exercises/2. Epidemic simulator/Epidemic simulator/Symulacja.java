import pakiet1.*;

public class Symulacja {
    public static void main(String[] args){
        Symulacyjka symuluj = new Symulacyjka();
        symuluj.setProperties();
        symuluj.readProperties();
        symuluj.Symuluj();
    }
}
