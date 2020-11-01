import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AgentTowarzyski extends Agent {
    public AgentTowarzyski(int id, Random rng){
        super.czyZarażony = false;
        super.czyOdporny = false;
        super.czyZdrowy = true;
        super.czyMartwy = false;
        super.id = id;
        super.znajomi = new ArrayList<Agent>();
        super.rng = rng;
    }

    final public void UmówSię(double prawdSpotkania, int aktualnyDzień, int dniDoKońca, HashMap<Integer, ArrayList<Spotkanie>> terminarz){
        if (dniDoKońca == 0)
            return;
        double prawdSpotkaniaTmp = prawdSpotkania;
        ArrayList<Spotkanie> listaTmp;
        int dzień = 0;
        ArrayList<Agent> potencjalni = (ArrayList<Agent>) super.znajomi.clone();;
        if (super.czyZarażony == false) {
            for (int i = 0; i < super.znajomi.size(); i++) {
                Agent agentTmp = super.znajomi.get(i);
                potencjalni.addAll(agentTmp.dajZnajomych());
            }
        }
        HashSet<Agent> setTmp = new HashSet<Agent>(potencjalni);
        potencjalni = new ArrayList<Agent>(setTmp);

        while (super.rng.nextDouble() <= prawdSpotkaniaTmp) {
            int n = new Integer(this.rng.nextInt(potencjalni.size()));
            Agent agent1 = this;
            Agent agent2 = potencjalni.get(n);
            if(dniDoKońca > 1)
                dzień  = aktualnyDzień + 1 + super.rng.nextInt(dniDoKońca - 1);
            else if (dniDoKońca == 1)
                dzień = aktualnyDzień + 1;

            Spotkanie spotkanieTmp  = new Spotkanie(agent1, agent2 , dzień, super.rng);
            if (!terminarz.containsKey(dzień)){
                listaTmp = new ArrayList<Spotkanie>();
                listaTmp.add(spotkanieTmp);
                terminarz.put(dzień, listaTmp);
            }
            else{
                listaTmp = terminarz.get(dzień);
                listaTmp.add(spotkanieTmp);
            }
        }
    }
}

