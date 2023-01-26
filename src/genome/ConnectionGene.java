package src.genome;

import src.network.Network;

public class ConnectionGene {
    private NodeGene from, to;

    private double weight;
    private boolean expressed = true;

    public ConnectionGene(NodeGene from, NodeGene to) {
        this.from = from;
        this.to = to;
    }

    public NodeGene getFrom() {
        return from;
    }

    public void setFrom(NodeGene from) {
        this.from = from;
    }

    public NodeGene getTo() {
        return to;
    }

    public void setTo(NodeGene to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isExpressed() {
        return expressed;
    }

    public void setExpressed(boolean expressed) {
        this.expressed = expressed;
    }

    public boolean equals(Object o){
        if(!(o instanceof ConnectionGene)) return false;
        return (from.equals(((ConnectionGene)o).getFrom()) && to.equals(((ConnectionGene)o).getTo()));
    }

    public int hashCode() {
        return from.getInnovationNumber() * Network.MAX_NODES + to.getInnovationNumber();
    }
}
