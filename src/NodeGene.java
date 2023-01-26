package src;
public class NodeGene {

    private int id;
    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public NodeGene(int innovationNumber, TYPE type) {
        this(innovationNumber);
        this.type = type;
    }

    public NodeGene(int ID){
        this.id = ID;
    }

    public int getID(){
        return id;
    }

    public enum TYPE {
        INPUT_NODE,
        HIDDEN_NODE,
        OUTPUT_NODE
    }

}
