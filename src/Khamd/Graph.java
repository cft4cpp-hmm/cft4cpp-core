package Khamd;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import cfg.object.ICfgNode;
import cfg.testpath.IFullTestpath;

public class Graph {
	private Set<Node> nodes = new HashSet<Node>();
	
	private List<IFullTestpath> fullPossibleTestpaths;
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public Node checkExist(ICfgNode cfgNode) {
		for (Node node : nodes) {
			if(node.getICfgNode() == cfgNode) {
				return node;
			}
		}
		return null;
	}
	
	public Set<Node> getAllNode(){
		return this.nodes;
	}
	
}
