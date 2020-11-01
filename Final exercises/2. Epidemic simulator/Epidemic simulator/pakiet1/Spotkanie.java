import java.util.Random;

public class Spotkanie {
    private int dzień;
    private Agent agent1;
    private Agent agent2;
    private Random rng;

    public Spotkanie(Agent agent1, Agent agent2, int dzień, Random rng){
        this.agent1 = agent1;
        this.agent2 = agent2;
        this.dzień = dzień;
        this.rng = rng;
    }

    public void Odbyj(double prawdZarażenia){
        if (this.agent1.czyŻyje() == false || this.agent2.czyŻyje() == false)
            return;
        else if (this.agent1.CzyPodatnyNaZarażenie() == true && this.agent2.CzyChory() == true) {
            if (this.rng.nextDouble() <= prawdZarażenia){
                this.agent1.Zaraź();
            }
        }
        else if (this.agent2.CzyPodatnyNaZarażenie() == true && this.agent1.CzyChory() == true){
            if (this.rng.nextDouble() <= prawdZarażenia){
                this.agent2.Zaraź();
            }
        }
    }
}
