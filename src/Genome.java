package src;

import src.NodeGene;
import src.utils.CustomHashSet;

public class Genome {
    CustomHashSet<NodeGene> nodes = new CustomHashSet<NodeGene>();
    CustomHashSet<ConnectionGene> connections = new CustomHashSet<ConnectionGene>();
    Network network;

    public Genome(Network parentNetwork) {
        this.network = parentNetwork;
    }

    /**
     * Take two nodes that have not been connected and connect them, giving them a
     * random weight.
     */
    public void mutateAddConnection() {

    }

    /**
     * Take an existing connection and add a node in the middle. Connect the
     * original input to the new node with a weight of 1. Connect the new node to
     * the original output with the same weight as the original connection. Disables
     * the original connection.
     */
    public void mutateAddNode() {
        ConnectionGene toSplit = connections.getRandomElement();
        toSplit.setExpressed(false);
        NodeGene newNodeGene = network.generateNode();
        newNodeGene.setType(NodeGene.TYPE.HIDDEN_NODE);
        ConnectionGene fromConnection = new ConnectionGene(toSplit.getInputNodeID(), newNodeGene.getInnovationNumber());
        ConnectionGene toConnection = new ConnectionGene(newNodeGene.getInnovationNumber(), toSplit.getOutputNodeID());
        fromConnection.setExpressed(true);
        toConnection.setExpressed(true);
        fromConnection.setWeight(1);
        toConnection.setWeight(toSplit.getWeight());
        network.getConnectionDictionary()
                .put(new int[] { fromConnection.getInputNodeID(), fromConnection.getOutputNodeID() }, fromConnection);
        network.getConnectionDictionary()
                .put(new int[] { toConnection.getInputNodeID(), toConnection.getOutputNodeID() }, toConnection);
    }

    public void mutateConnectionToggle() {
        ConnectionGene c = connections.getRandomElement();
        c.setExpressed(c.isExpressed());
    }
}