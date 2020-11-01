import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Agent {
    protected int id;
    protected boolean czyZarażony;
    protected boolean czyOdporny;
    protected boolean czyZdrowy;
    protected boolean czyMartwy;
    protected ArrayList<Agent> nieznajomi;
    protected ArrayList<Agent> znajomi;
    protected Random rng;

    public abstract void UmówSię(double prawdSpotkania, int aktualnyDzień, int dniDoKońca, HashMap<Integer, ArrayList<Spotkanie>> terminarz);

    public boolean DodajZnajomego(){
        if (this.nieznajomi.size() > 0) {
            int n = this.rng.nextInt(this.nieznajomi.size());
            Agent agent = this.nieznajomi.get(n);
            agent.DodajZnajomego(this);
            agent.UsuńNieznajomego(this);
            this.DodajZnajomego(agent);
            this.UsuńNieznajomego(agent);
            return true;
        }
        else
            return false;
    }

    public void setNieznajomi(ArrayList<Agent> agenci){
        this.nieznajomi = (ArrayList<Agent>)agenci.clone();
        this.nieznajomi.remove(this);
    }

    public void UsuńNieznajomego(Agent agent){
        this.nieznajomi.remove(agent);
    }

    public void UsuńZnajomego(Agent agent){
        this.znajomi.remove(agent);
    }

    public void DodajZnajomego(Agent agent){
        this.znajomi.add(agent);
    }

    public int DajId(){
        return this.id;
    }

    public ArrayList<Agent> dajZnajomych(){
        return this.znajomi;
    }

    public void Zabij(){
        this.czyZarażony = false;
        this.czyMartwy = true;
        for (int i = 0; i < znajomi.size(); i++){
            Agent tmp = this.znajomi.get(i);
            tmp.dajZnajomych().remove(this);
        }
    }

    public boolean czyŻyje(){
        return !(this.czyMartwy);
    }
    public void Zaraź(){
        this.czyZarażony = true;
        this.czyZdrowy = false;
    }

    public void Zdrów(){
        this.czyZarażony = false;
        this.czyOdporny = true;
    }

    public boolean CzyPodatnyNaZarażenie(){
        if (this.czyZdrowy == true)
            return true;
        else
            return false;
    }

    public boolean CzyZdrowy(){
        if (this.czyZdrowy == true)
            return true;
        else
            return false;
    }

    public boolean CzyChory(){
        if (this.czyZarażony == true)
            return true;
        else
            return false;
    }

    public boolean CzyOdporny(){
        if (this.czyOdporny == true)
            return true;
        else
            return false;
    }
}
