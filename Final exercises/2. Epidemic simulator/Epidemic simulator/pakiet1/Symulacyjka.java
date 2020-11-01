

import java.io.*;
import java.nio.channels.Channels;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Symulacyjka {
    private final int DUMMY = -1;
    private Properties tekst;
    private Properties xml;
    private long seed = DUMMY;
    private int liczbaAgentów = DUMMY;
    private double prawdTowarzyski = DUMMY;
    private double prawdSpotkania = DUMMY;
    private double prawdZarażenia = DUMMY;
    private double prawdWyzdrowienia = DUMMY;
    private double śmiertelność = DUMMY;
    private int liczbaDni = DUMMY;
    private int śrZnajomych = DUMMY;
    private String plikZRaportem = "" + DUMMY;

    public Symulacyjka(){
        this.xml = new Properties();
        this.tekst = new Properties();
    }

    public void setProperties() {
        try (FileInputStream stream = new FileInputStream("default.properties");
             Reader reader = Channels.newReader(stream.getChannel(), StandardCharsets.UTF_8.name())) {
            this.tekst.load(reader);
        } catch (FileNotFoundException e) {
            String message = "Brak pliku default.properties";
            throw new RuntimeException(message, e);
        } catch (MalformedInputException e3) {
            String message = "default.properties nie jest tekstowy";
            throw new RuntimeException(message, e3);
        }
        catch (IOException e4){
            throw  new RuntimeException(e4);
        }

        try {
            this.xml.loadFromXML(new FileInputStream("simulation-conf.xml"));
        } catch (FileNotFoundException e1) {
            String message = "simulation-conf.xml";
            throw new RuntimeException(message, e1);
        } catch (InvalidPropertiesFormatException e2) {
            String message = "simulation-conf.xml";
            throw new RuntimeException(message, e2);
        } catch (IOException e5){
            throw  new RuntimeException(e5);
        }
    }

    public void readProperties(){
        String tmp;

        tmp = tekst.getProperty("seed");
        if (tmp != null) {
            this.isLong(tmp, "seed");
            this.seed = Long.parseLong(tmp);
        }
        tmp = xml.getProperty("seed");
        if (tmp != null) {
            this.isLong(tmp, "seed");
            this.seed = Long.parseLong(tmp);
        }
        if (this.seed == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza seed");


        tmp = tekst.getProperty("liczbaAgentów");
        if (tmp != null) {
            this.isInteger(tmp, "liczbaAgentów");
            this.liczbaAgentów = Integer.parseInt(tmp);
            if (this.liczbaAgentów < 1 || this.liczbaAgentów > 1000000)
                throw new ArithmeticException("Niedozwolona wartość " + this.liczbaAgentów + " dla klucza liczbaAgentów");
        }
        tmp = xml.getProperty("liczbaAgentów");
        if (tmp != null) {
            this.isInteger(tmp, "liczbaAgentów");
            this.liczbaAgentów = Integer.parseInt(tmp);
            if (this.liczbaAgentów < 1 || this.liczbaAgentów > 1000000)
                throw new ArithmeticException("Niedozwolona wartość " + this.liczbaAgentów + " dla klucza liczbaAgentów");
        }
        if (this.liczbaAgentów == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza liczbaAgentów");


        tmp = tekst.getProperty("prawdTowarzyski");
        if (tmp != null) {
            this.isDouble(tmp, "prawdTowarzyski");
            this.prawdTowarzyski = Double.parseDouble(tmp);
            if (this.prawdTowarzyski < 0 || this.prawdTowarzyski > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdTowarzyski + " dla klucza prawdTowarzyski");
        }
        tmp = xml.getProperty("prawdTowarzyski");
        if (tmp != null) {
            this.isDouble(tmp, "prawdTowarzyski");
            this.prawdTowarzyski = Double.parseDouble(tmp);
            if (this.prawdTowarzyski < 0 || this.prawdTowarzyski > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdTowarzyski + " dla klucza prawdTowarzyski");
        }
        if (this.prawdTowarzyski == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza prawdTowarzyski");


        tmp = tekst.getProperty("prawdSpotkania");
        if (tmp != null) {
            this.isDouble(tmp, "prawdSpotkania");
            this.prawdSpotkania = Double.parseDouble(tmp);
            if (this.prawdSpotkania < 0 || this.prawdSpotkania >= 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdSpotkania + " dla klucza prawdSpotkania");
        }
        tmp = xml.getProperty("prawdSpotkania");
        if (tmp != null) {
            this.isDouble(tmp, "prawdSpotkania");
            this.prawdSpotkania = Double.parseDouble(tmp);
            if (this.prawdSpotkania < 0 || this.prawdSpotkania >= 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdSpotkania + " dla klucza prawdSpotkania");
        }
        if (this.prawdSpotkania == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza prawdSpotkania");


        tmp = tekst.getProperty("prawdZarażenia");
        if (tmp != null) {
            this.isDouble(tmp, "prawdZarażenia");
            this.prawdZarażenia = Double.parseDouble(tmp);
            if (this.prawdZarażenia < 0 || this.prawdZarażenia > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdZarażenia + " dla klucza prawdZarażenia");
        }
        tmp = xml.getProperty("prawdZarażenia");
        if (tmp != null) {
            this.isDouble(tmp, "prawdZarażenia");
            this.prawdZarażenia = Double.parseDouble(tmp);
            if (this.prawdZarażenia < 0 || this.prawdZarażenia > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdZarażenia + " dla klucza prawdZarażenia");
        }
        if (this.prawdZarażenia == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza prawdZarażenia");


        tmp = tekst.getProperty("prawdWyzdrowienia");
        if (tmp != null) {
            this.isDouble(tmp, "prawdWyzdrowienia");
            this.prawdWyzdrowienia = Double.parseDouble(tmp);
            if (this.prawdWyzdrowienia < 0 || this.prawdWyzdrowienia > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdWyzdrowienia + " dla klucza prawdWyzdrowienia");
        }
        tmp = xml.getProperty("prawdWyzdrowienia");
        if (tmp != null) {
            this.isDouble(tmp, "prawdWyzdrowienia");
            this.prawdWyzdrowienia = Double.parseDouble(tmp);
            if (this.prawdWyzdrowienia < 0 || this.prawdWyzdrowienia > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.prawdWyzdrowienia + " dla klucza prawdWyzdrowienia");
        }
        if (this.prawdWyzdrowienia == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza prawdWyzdrowienia");


        tmp = tekst.getProperty("śmiertelność");
        if (tmp != null) {
            this.isDouble(tmp, "śmiertelność");
            this.śmiertelność = Double.parseDouble(tmp);
            if (this.śmiertelność < 0 || this.śmiertelność > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.śmiertelność + " dla klucza śmiertelność");
        }
        tmp = xml.getProperty("śmiertelność");
        if (tmp != null) {
            this.isDouble(tmp, "śmiertelność");
            this.śmiertelność = Double.parseDouble(tmp);
            if (this.śmiertelność < 0 || this.śmiertelność > 1)
                throw new ArithmeticException("Niedozwolona wartość " + this.śmiertelność + " dla klucza śmiertelność");
        }
        if (this.śmiertelność == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza śmiertelność");


        tmp = tekst.getProperty("liczbaDni");
        if (tmp != null) {
            this.isInteger(tmp, "liczbaDni");
            this.liczbaDni = Integer.parseInt(tmp);
            if (this.liczbaDni < 1 || this.liczbaDni > 1000)
                throw new ArithmeticException("Niedozwolona wartość " + this.liczbaDni + " dla klucza liczbaDni");
        }
        tmp = xml.getProperty("liczbaDni");
        if (tmp != null) {
            this.isInteger(tmp, "liczbaDni");
            this.liczbaDni = Integer.parseInt(tmp);
            if (this.liczbaDni < 1 || this.liczbaDni > 1000)
                throw new ArithmeticException("Niedozwolona wartość " + this.liczbaDni + " dla klucza liczbaDni");
        }
        if (this.liczbaDni == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza liczbaDni");


        tmp = tekst.getProperty("śrZnajomych");
        if (tmp != null) {
            this.isInteger(tmp, "śrZnajomych");
            this.śrZnajomych = Integer.parseInt(tmp);
            if (this.śrZnajomych < 0 || this.śrZnajomych >= this.liczbaAgentów)
                throw new ArithmeticException("Niedozwolona wartość " + this.śrZnajomych + " dla klucza śrZnajomych");
        }
        tmp = xml.getProperty("śrZnajomych");
        if (tmp != null) {
            this.isInteger(tmp, "śrZnajomych");
            this.śrZnajomych = Integer.parseInt(tmp);
            if (this.śrZnajomych < 1 || this.śrZnajomych > this.liczbaAgentów)
                throw new ArithmeticException("Niedozwolona wartość " + this.śrZnajomych + " dla klucza śrZnajomych");
        }
        if (this.śrZnajomych == DUMMY)
            throw new RuntimeException("Brak wartości dla klucza śrZnajomych");


        tmp = tekst.getProperty("plikZRaportem");
        if (tmp != null)
            this.plikZRaportem = tmp;
        tmp = xml.getProperty("plikZRaportem");
        if (tmp != null)
            this.plikZRaportem = tmp;
        if (this.plikZRaportem == "" + DUMMY)
            throw new RuntimeException("Brak wartości dla klucza plikZRaportem");
    }

    public boolean isInteger(String input, String dana) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e7) {
            String message = "Niedozwolona wartość " + input + " dla klucza " + dana;
            throw  new RuntimeException(message, e7);
        }
    }

    public boolean isDouble(String input, String dana) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch(Exception e6) {
            String message = "Niedozwolona wartość " + input + " dla klucza " + dana;
            throw  new RuntimeException(message, e6);
        }
    }
    public boolean isLong(String input, String dana) {
        try {
            Long.parseLong(input);
            return true;
        }
        catch(Exception e6) {
            String message = "Niedozwolona wartość " + input + " dla klucza " + dana;
            throw  new RuntimeException(message, e6);
        }
    }

    public void Symuluj(){
        HashMap<Integer, ArrayList<Spotkanie>> terminarz = new HashMap<Integer, ArrayList<Spotkanie>>();
        ArrayList<Agent> agenci = new ArrayList<Agent>();
        Random rng = new Random(this.seed);
        Formatter y;
        try{
            y = new Formatter(this.plikZRaportem);
        }
        catch (FileNotFoundException e){
            String message = "Niedozwolona wartość " + this.plikZRaportem + " dla klucza plikZRaportem";
            throw new RuntimeException(message, e);
        }

        y.format("%s", "# twoje wyniki powinny zawierać te komentarze" + "\n");
        y.close();

        try {
            FileWriter f = new FileWriter(this.plikZRaportem, true);
            Formatter x = new Formatter(f);
            x.format("%s", "seed=" + this.seed + "\n");
            x.format("%s", "liczbaAgentów=" + this.liczbaAgentów + "\n");
            x.format("%s", "prawdTowarzyski=" + this.prawdTowarzyski + "\n");
            x.format("%s", "prawdSpotkania=" + this.prawdSpotkania + "\n");
            x.format("%s", "prawdZarażenia=" + this.prawdZarażenia + "\n");
            x.format("%s", "prawdWyzdrowienia=" + this.prawdWyzdrowienia + "\n");
            x.format("%s", "śmiertelność=" + this.śmiertelność + "\n");
            x.format("%s", "liczbaDni=" + this.liczbaDni + "\n");
            x.format("%s", "śrZnajomych=" + this.śrZnajomych + "\n");
            x.format("%s", "\n" + "# agenci jako: id typ lub id* typ dla chorego" + "\n");

            int l = rng.nextInt(this.liczbaAgentów); // losuje który agent będzie chory jako pierwszy

            //tworzy Arraylistę z agentami
            for (int i = 0; i < this.liczbaAgentów; i++){
                x.format("%s", "" + (i + 1));
                if (i == l)
                    x.format("%s", "*");
                if (rng.nextDouble() <= prawdTowarzyski) {
                    agenci.add(new AgentTowarzyski(i + 1, rng));
                    x.format("%s", " towarzyski" + "\n");
                }
                else {
                    agenci.add(new AgentNormalny(i + 1, rng));
                    x.format("%s", " zwykły" + "\n");
                }
                //zaraża jednego losowego agenta
                if (i == l)
                    agenci.get(i).Zaraź();
            }
            // ustawia nieznajomych dla każdego agenta
            for (int i = 0; i < this.liczbaAgentów; i++){
                agenci.get(i).setNieznajomi(agenci);
            }
            // losuje graf znajomości
            for (int i = 0; i < ((this.śrZnajomych * this.liczbaAgentów) / 2); i++){
                int n = rng.nextInt(agenci.size());
                //dodaje do listy znajomych losowo wybranego agenta z listy nieznajomych danego agenta
                //jeśli agent nie ma nieznajomych to losuje agenta jeszcze raz
                while(!agenci.get(n).DodajZnajomego()){
                    n = rng.nextInt(agenci.size());
                }
            }

            x.format("%s", "\n" + "#graf" + "\n");
            //wypisuje graf
            for (int i = 1; i <= agenci.size(); i++){
                x.format("%s", "" + i);
                ArrayList<Agent> listaTmp = agenci.get(i-1).dajZnajomych();
                for (int j = 0; j < listaTmp.size(); j++){
                    int intTmp = listaTmp.get(j).DajId();
                    x.format("%s", " " + intTmp);
                }
                x.format("%s", "\n");
            }

            x.format("%s", "\n" + "# liczność w kolejnych dniach" + "\n" );
            x.format("%s", (this.liczbaAgentów - 1) + " 1 " + "0\n" );
            //symulacja kolejnych dni epidemii
            for (int i = 1; i <= this.liczbaDni; i++){
                int zdrowi = 0;
                int chorzy = 0;
                int odporni = 0;
                //zliczanie agentów i umieranie/zdrowienie chorych
                for (int j = 0; j < agenci.size(); j++){
                    Agent agent = agenci.get(j);
                    if (agent.CzyZdrowy())
                        zdrowi++;
                    else if (agent.CzyOdporny())
                        odporni++;
                    else if (agent.CzyChory()){
                        if(rng.nextDouble() <= śmiertelność) {
                            agent.Zabij();
                            agenci.remove(agent);
                            j--;
                        }
                        else if (rng.nextDouble() <= prawdWyzdrowienia){
                            agent.Zdrów();
                            odporni++;
                        }
                        else
                            chorzy++;
                    }
                }
                //umawianie na spotkania
                for (int k = 0; k < agenci.size(); k++){
                    Agent agent = agenci.get(k);
                    agent.UmówSię(this.prawdSpotkania, i, this.liczbaDni - i, terminarz);
                }

                ArrayList<Spotkanie> spotkaniaNaDziś = terminarz.get(i);
                if(spotkaniaNaDziś != null) {
                    //odbywanie spotkań zaplanowanych na dany dzień
                    for (int m = 0; m < spotkaniaNaDziś.size(); m++) {
                        Spotkanie spotkanieTmp = spotkaniaNaDziś.get(m);
                        spotkanieTmp.Odbyj(this.prawdZarażenia);
                    }
                }
                x.format("%s", zdrowi + " " + chorzy + " " + odporni + "\n");
            }
            x.close();
        }
        catch (Exception e){
            String message = "Niedozwolona wartość" + this.plikZRaportem + "dla klucza plikZRaportem";
            throw new RuntimeException(message, e);
        }
    }
}
