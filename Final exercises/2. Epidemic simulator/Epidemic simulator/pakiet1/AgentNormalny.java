import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AgentNormalny extends Agent {
    public AgentNormalny(int id, Random rng){
        super.czyZarażony = false;
        super.czyOdporny = false;
        super.czyZdrowy = true;
        super.czyMartwy = false;
        super.id = id;
        super.znajomi = new ArrayList<Agent>();
        super.rng = rng;
    }

    final public void UmówSię(double prawdSpotkania, int aktualnyDzień, int dniDoKońca, HashMap<Integer, ArrayList<Spotkanie>> terminarz){
        double prawdSpotkaniaTmp = prawdSpotkania;
        ArrayList<Spotkanie> listaTmp;
        int dzień;

        if (super.czyZarażony == true)
            prawdSpotkaniaTmp = prawdSpotkaniaTmp / 2;
        while (super.rng.nextDouble() <= prawdSpotkaniaTmp) {
            int n = new Integer(this.rng.nextInt(super.znajomi.size()));
            Agent agent1 = this;
            Agent agent2 = super.znajomi.get(n);
            if (dniDoKońca > 1)
                dzień = aktualnyDzień + 1 + super.rng.nextInt(dniDoKońca - 1);
            else if (dniDoKońca == 1)
                dzień = aktualnyDzień + 1;
            else
                return;

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
