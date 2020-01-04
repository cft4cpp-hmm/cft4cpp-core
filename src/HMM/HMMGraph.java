package HMM;

import java.util.ArrayList;
import java.util.List;

import cfg.ICFG;
import cfg.object.ICfgNode;

public class HMMGraph {
	private List<Node> nodes;
	public HMMGraph() {
		nodes = new ArrayList<Node>();
	}
	public void addNode(Node node, Node nextNode, float weight) {
		for(Node node1: nodes) {
			if(node1.getCfgNode() == node.getCfgNode()) {
				node1.addProbability(nextNode,weight);
//				for(Node node2: nodes) {
//					if(node2.getCfgNode()==nextNode.getCfgNode()) {
//						return;
//					}
//				}
//				nodes.add(nextNode);
				return ;
			}
			
		}
		nodes.add(node);
		node.addProbability(nextNode,weight);
//		for(Node node2: nodes) {
//			if(node2.getCfgNode()==nextNode.getCfgNode()) {
//				return;
//			}
//		}
//		nodes.add(nextNode);
		
	}
	public void recomputeProbability() {
		for(Node node: nodes) {
			
			node.recomputeProbabilities();
		}
	}
	
	public Node getNode(ICfgNode iCfgNode) {
		for(Node node: nodes) {
			if(node.getCfgNode()==iCfgNode) {
				return node;
			}
		}
		return null;
	}
}
