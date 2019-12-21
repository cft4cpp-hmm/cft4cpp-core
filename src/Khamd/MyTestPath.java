package Khamd;

import java.util.ArrayList;
import java.util.List;

import cfg.object.ICfgNode;
import utils.tostring.ToString;

public class MyTestPath {
	private List<Edge> edges;
	private int pathNumber;
	private boolean isGenerated;
	private int visitedNumber ;
	private String realString;
	
	private String testCase;
	public MyTestPath(int pathNumber) {
		this.pathNumber=pathNumber;
		this.edges = new ArrayList<Edge>();
		this.visitedNumber=0;
		this.isGenerated=false;
		this.testCase="";
		this.realString="";
	}
	public String getTestCase() {
		return testCase;
	}
	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}
	public int getVisitedNumber() {
		return visitedNumber;
	}
	public void setVisitedNumber(int visitedNumber) {
		this.visitedNumber += visitedNumber;
	}
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	public List<Edge> getEdge(){
		return this.edges;
	}
	public String getRealString() {
		return realString;
	}
	public void setRealString(String realString) {
		this.realString = realString;
	}
	public List<ICfgNode> getFullCfgNode(){
		List<ICfgNode> fullCfgNode = new ArrayList<ICfgNode>();
		fullCfgNode.add(this.edges.get(0).getNode());
		for(int i=0;i<this.edges.size();i++){
			fullCfgNode.add(this.edges.get(i).getNextNode());
		}
		return fullCfgNode;
	}
	public String toString() {
		List<ICfgNode> fullCfgNodes =this.getFullCfgNode();
		String toString = "";
		for(ICfgNode node : fullCfgNodes) {
			if(node.getContent().equals("{")) continue;
			toString+=""+node.toString().replace(" ", "")+"";
		}
		return toString;
	}
	
	public String toCSV() {
		List<ICfgNode> fullCfgNodes =this.getFullCfgNode();
		String toCSV = "";
		for(ICfgNode node : fullCfgNodes) {
			if(node.getContent().equals("{")) continue;
			toCSV+=" ("+node.toString()+") ";
		}
		return toCSV;
	}
	
	public Edge searchEdge(ICfgNode node, ICfgNode nextNode) {
		for(Edge edge : this.edges) {
			if(edge.getNode()==node && edge.getNextNode()==nextNode) {
				return edge;
			}
		}
		return null;
	}
	
	public int getWeight() {
		int pro=0;
		for(Edge edge: this.edges) {
			pro+=edge.getWeight();
		}
		return pro;
	}
	public boolean isGenerated() {
		return isGenerated;
	}
	public void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}
	
	
	
}
