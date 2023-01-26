package src.genome;

import src.network.Network;
import src.utils.CustomHashSet;

public class Genome {
    private CustomHashSet<ConnectionGene> connections = new CustomHashSet<>();
    private CustomHashSet<NodeGene> nodes = new CustomHashSet<>();

    private Network network;

    public Genome(Network network) {
        this.network = network;
    }

    public static double distance(Genome g1, Genome g2){
        return 0;
    }

    public static Genome crossOver(Genome g1, Genome g2){
        return null;
    }

    public void mutate(){

    }

    public CustomHashSet<ConnectionGene> getConnections() {
        return connections;
    }

    public CustomHashSet<NodeGene> getNodes() {
        return nodes;
    }

    public Network getNetwork() {
        return network;
    }
}
