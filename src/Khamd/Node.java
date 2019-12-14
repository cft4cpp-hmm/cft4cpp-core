package Khamd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cfg.object.ICfgNode;

public class Node {
	private ICfgNode node;
	
	private Map<Node, Integer> adjacementNodes ;
	private List<Integer> pathNumbers ;
	private boolean isVisited;
	public Node(ICfgNode node, int pathNumber) {
		adjacementNodes = new HashMap<Node, Integer>();
		pathNumbers= new ArrayList<Integer>();
		this.node = node;
		this.pathNumbers.add(pathNumber);
		this.isVisited= false;
	}
	public void initNodeAndDistance(Node node, int distance) {
		adjacementNodes.put(node,distance);
	}
	public void addTestPathNumber(int pathNumber) {
		this.pathNumbers.add(pathNumber);
	}
	public void addWeight(Node node, int weight) throws Exception {
		Integer distance = this.adjacementNodes.get(node);
		if(distance==null) {
			throw new  Exception("Graph Initialization error");
		}
		this.adjacementNodes.put(node, distance+ weight);
	}
	public ICfgNode getICfgNode() {
		return this.node;
	}
	
	public boolean isReturnedNode() {
		return this.adjacementNodes.size()==0;
	}
	
	public Map<Node, Integer> getAdjacementNodes() {
		return adjacementNodes;
	}
	
	public void setAdjacementNodes(Map<Node, Integer> adjacementNodes) {
		this.adjacementNodes = adjacementNodes;
	}
	
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	
}
