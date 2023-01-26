package src;

public class ConnectionGene {

    private int innovationNumber;
    private boolean expressed;
    private float weight;
    private int inputNodeID, outputNodeID;

    public ConnectionGene(int inputNodeID, int outputNodeID) {
        this.inputNodeID = inputNodeID;
        this.outputNodeID = outputNodeID;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getInputNodeID() {
        return inputNodeID;
    }

    public void setInputNodeID(int inputNodeID) {
        this.inputNodeID = inputNodeID;
    }

    public int getOutputNodeID() {
        return outputNodeID;
    }

    public void setOutputNodeID(int outputNodeID) {
        this.outputNodeID = outputNodeID;
    }

    public boolean isExpressed() {
        return expressed;
    }

    public void setExpressed(boolean expressed) {
        this.expressed = expressed;
    }

    public boolean equals(Object object){
        if(!(object instanceof ConnectionGene)) return false;
        ConnectionGene connectionGene = (ConnectionGene) object;
        return (this.inputNodeID == connectionGene.getInputNodeID() && this.outputNodeID == connectionGene.getOutputNodeID());
    }

}
