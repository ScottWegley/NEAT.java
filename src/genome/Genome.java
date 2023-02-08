package genome;

import network.Network;
import utils.CustomHashSet;

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

        while (indexG1 < g1.getConnections().getSize()) {
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
        if(network.getPROBABILITY_MUTATE_LINK() > Math.random()){
            mutateLink();
        }
        if(network.getPROBABILITY_MUTATE_NODE() > Math.random()){
            mutateNode();
        }
        if(network.getPROBABILITY_MUTATE_TOGGLE() > Math.random()){
            mutateLinkToggle();
        }
        if(network.getPROBABILITY_MUTATE_WEIGHT_RANDOM() > Math.random()){
            mutateWeightRandom();
        }
        if(network.getPROBABILITY_MUTATE_WEIGHT_SHIFT() > Math.random()){
            mutateWeightShift();
        }
    }

    public void mutateLink() {
        for (int i = 0; i < 100; i++) {
            
            NodeGene a = nodes.getRandomElement();
            NodeGene b = nodes.getRandomElement();
            if (a.getX() == b.getX()) {
                continue;
            }
            ConnectionGene con = new ConnectionGene((a.getX() < b.getX() ? a : b), (a.getX() < b.getX() ? b : a));

            if (connections.contains(con)) {
                continue;
            }

            con = network.generateConnectionGene(con.getFrom(), con.getTo());
            con.setWeight((Math.random() * 2 - 1) * network.getWEIGHT_RANDOM_STRENGTH());

            connections.addSorted(con);
            return;
        }
    }

    public void mutateNode() {
        ConnectionGene con = connections.getRandomElement();
        if (con == null) {
            return;
        }

        NodeGene from = con.getFrom();
        NodeGene to = con.getTo();

        NodeGene middle = network.generateNode();
        middle.setX((from.getX() + to.getX())/2);
        middle.setY((from.getY() + to.getY())/2 + Math.random() * 0.1 - 0.05);

        ConnectionGene con1 = network.generateConnectionGene(from, middle);
        ConnectionGene con2 = network.generateConnectionGene(middle, to);
        con1.setWeight(1);
        con2.setWeight(con.getWeight());
        con2.setExpressed(con.isExpressed());

        connections.remove(con);

        connections.add(con1);
        connections.add(con2);

        nodes.add(middle);

    }

    public void mutateWeightShift() {
        ConnectionGene con = connections.getRandomElement();
        if (con != null) {
            con.setWeight(con.getWeight() + (Math.random() * 2 - 1) * network.getWEIGHT_RANDOM_STRENGTH());
        }
    }

    public void mutateWeightRandom() {
        ConnectionGene con = connections.getRandomElement();
        if (con != null) {
            con.setWeight((Math.random() * 2 - 1) * network.getWEIGHT_RANDOM_STRENGTH());
        }
    }

    public void mutateLinkToggle() {
        ConnectionGene con = connections.getRandomElement();
        if (con != null) {
            con.setExpressed(!con.isExpressed());
        }
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
