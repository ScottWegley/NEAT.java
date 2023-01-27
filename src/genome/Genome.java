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

    public static double distance(Genome inG1, Genome inG2) {

        int innovG1 = inG1.getConnections().getElement(inG1.getConnections().getSize() - 1).getInnovationNumber();
        int innovG2 = inG2.getConnections().getElement(inG2.getConnections().getSize() - 1).getInnovationNumber();

        Genome g1;
        Genome g2;

        if (innovG1 < innovG2) {
            g1 = inG2;
            g2 = inG1;
        } else {
            g1 = inG1;
            g2 = inG2;
        }

        int indexG1 = 0;
        int indexG2 = 0;

        int disjointGenes = 0;
        int excessGenes = 0;
        double weightDifference = 0;
        int similarGenes = 0;

        while (indexG1 < g1.getConnections().getSize() && indexG2 < g2.getConnections().getSize()) {
            ConnectionGene c1 = g1.getConnections().getElement(indexG1);
            ConnectionGene c2 = g2.getConnections().getElement(indexG2);

            int in1 = c1.getInnovationNumber();
            int in2 = c2.getInnovationNumber();

            if (in1 == in2) {
                indexG1++;
                indexG2++;
                similarGenes++;
                weightDifference += Math.abs(c1.getWeight() - c2.getWeight());
            } else if (in1 > in2) {
                disjointGenes++;
                indexG2++;
            } else {
                disjointGenes++;
                indexG1++;
            }
        }

        weightDifference /= similarGenes;
        excessGenes = g1.getConnections().getSize() - indexG1;

        double N = Math.max(g1.getConnections().getSize(), g2.getConnections().getSize());
        if (N < 20) {
            N = 1;
        }

        return g1.network.getC1() * disjointGenes / N + g1.network.getC2() * excessGenes / N
                + g1.network.getC3() * weightDifference;
    }

    public static Genome crossOver(Genome inG1, Genome inG2) {

        int innovG1 = inG1.getConnections().getElement(inG1.getConnections().getSize() - 1).getInnovationNumber();
        int innovG2 = inG2.getConnections().getElement(inG2.getConnections().getSize() - 1).getInnovationNumber();

        Genome g1;
        Genome g2;

        if (innovG1 < innovG2) {
            g1 = inG2;
            g2 = inG1;
        } else {
            g1 = inG1;
            g2 = inG2;
        }

        Genome genome = g1.getNetwork().emptyGenome();

        int indexG1 = 0;
        int indexG2 = 0;

        while (indexG1 < g1.getConnections().getSize() && indexG2 < g2.getConnections().getSize()) {
            ConnectionGene c1 = g1.getConnections().getElement(indexG1);
            ConnectionGene c2 = g2.getConnections().getElement(indexG2);

            int in1 = c1.getInnovationNumber();
            int in2 = c2.getInnovationNumber();

            if (in1 == in2) {

                genome.getConnections().add(g1.getNetwork().cloneConnectionGene(Math.random() > 0.5 ? c1 : c2));

                indexG1++;
                indexG2++;
            } else if (in1 > in2) {
                indexG2++;
            } else {
                genome.getConnections().add(g1.getNetwork().cloneConnectionGene(c1));
                indexG1++;
            }
        }

        while(indexG1 < g1.getConnections().getSize()){
            ConnectionGene c1 = g1.getConnections().getElement(indexG1);
            genome.getConnections().add(g1.getNetwork().cloneConnectionGene(c1));
            indexG1++;
        }

        for (ConnectionGene c : genome.getConnections()) {
            genome.getNodes().add(c.getFrom());
            genome.getNodes().add(c.getTo());
        }
        return genome;

    }

    public void mutate() {

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
