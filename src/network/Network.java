package src.network;

import java.util.ArrayList;
import java.util.HashMap;

import src.genome.ConnectionGene;
import src.genome.Genome;
import src.genome.NodeGene;
import src.genome.NodeGene.TYPE;


public class Network {
    public static final int MAX_NODES = (int) Math.pow(2, 20);

    private double C1 = 1;
    private double C2 = 1;
    private double C3 = 1;

    private int inputNodes, outputNodes, maxInstances;

    private HashMap<ConnectionGene, ConnectionGene> connectionDictionary = new HashMap<>();
    private ArrayList<NodeGene> nodeDictionary = new ArrayList<>();

    public Network(int inputNodes, int outputNodes, int maxInstances) {
        reset(inputNodes, outputNodes, maxInstances);
    }

    public Genome emptyGenome(){
        Genome g = new Genome(this);
        for (int i = 0; i < inputNodes + outputNodes; i++) {
            g.getNodes().add(getNode(i + 1));
        }
        return g;
    }

    public void reset(int inputNodes, int outputNodes, int maxInstances) {
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;
        this.maxInstances = maxInstances;

        connectionDictionary.clear();
        nodeDictionary.clear();

        for (int i = 0; i < this.inputNodes; i++) {
            NodeGene n = generateNode();
            n.setX(0.1);
            n.setY((i+1)/(double)(this.inputNodes + 1));
            n.setType(TYPE.INPUT);
        }

        for (int i = 0; i < this.outputNodes; i++) {
            NodeGene n = generateNode();
            n.setX(0.9);
            n.setY((i+1)/(double)(this.outputNodes + 1));
            n.setType(TYPE.OUTPUT);
        }
    }

    public NodeGene generateNode() {
        NodeGene n = new NodeGene(nodeDictionary.size() + 1);
        nodeDictionary.add(n);
        return n;
    }

    public ConnectionGene cloneConnectionGene(ConnectionGene con){
        ConnectionGene c = new ConnectionGene(con.getFrom(), con.getTo());
        c.setWeight(con.getWeight());
        c.setExpressed(con.isExpressed());
        return c;
    } 

    public ConnectionGene generateConnectionGene(NodeGene n1, NodeGene n2){
        ConnectionGene c = new ConnectionGene(n1, n2);
        if(connectionDictionary.containsKey(c)){
            c.setInnovationNumber(connectionDictionary.get(c).getInnovationNumber());
        } else {
            c.setInnovationNumber(connectionDictionary.size() + 1);
            connectionDictionary.put(c,c);
        }
        return c;
    }

    public NodeGene getNode(int id) {
        if (id <= nodeDictionary.size() && id > 0)
            return nodeDictionary.get(id - 1);
        return generateNode();
    }

    public static void main(String[] args) {
        Network network = new Network(3, 3, 100);

        Genome g = network.emptyGenome();
        System.out.println(g.getNodes().getSize());
    }

    public double getC1() {
        return C1;
    }

    public double getC2() {
        return C2;
    }

    public double getC3() {
        return C3;
    }
}
