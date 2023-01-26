package src;

import java.util.ArrayList;
import java.util.HashMap;

import src.NodeGene.TYPE;

public class Network {

    public static final int MAX_NODES = (int) Math.pow(2, 10);

    private HashMap<ConnectionGene, ConnectionGene> connectionDictionary = new HashMap<>();
    private ArrayList<NodeGene> nodeDictionary = new ArrayList<>();

    private int inputNodes, outputNodes, instanceLimit, instances;

    public Network(int inputNodes, int outputNodes, int instanceLimit) {
        this.reset(inputNodes, outputNodes, instanceLimit);
    }

    public void reset(int inputNodes, int outputNodes, int instanceLimit) {
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;
        this.instanceLimit = instanceLimit;

        connectionDictionary.clear();
        nodeDictionary.clear();

        for (int i = 0; i < this.inputNodes; i++) {
            NodeGene nodeGene = generateNode();
            nodeGene.setType(TYPE.INPUT_NODE);
        }

        for (int i = 0; i < this.outputNodes; i++) {
            NodeGene nodeGene = generateNode();
            nodeGene.setType(TYPE.OUTPUT_NODE);
        }
    }

    public HashMap<ConnectionGene, ConnectionGene> getConnectionDictionary() {
        return connectionDictionary;
    }

    public NodeGene generateNode() {
        NodeGene nodeGene = new NodeGene(nodeDictionary.size() + 1);
        nodeDictionary.add(nodeGene);
        return nodeGene;
    }

    public NodeGene getNode(int index) {
        if (index >= 0 && index < nodeDictionary.size())
            return nodeDictionary.get(index);
        return generateNode();
    }

}
