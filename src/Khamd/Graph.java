package Khamd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.xmlbeans.impl.regex.REUtil;

import com.alee.log.Log;
import com.ibm.icu.text.UFieldPosition;

import cfg.object.ICfgNode;
import cfg.testpath.IFullTestpath;
import tree.object.IFunctionNode;

public class Graph {
	
	private List<IFullTestpath> fullPossibleTestpaths;
	private List<MyTestPath> fullMyTestPaths;
	private IFunctionNode functionNode;
	private String pathToFile;
	
	private LocalDateTime createdDate;
	public Graph(List<IFullTestpath> fullPossibleTestPaths, IFunctionNode functionNode, String pathtoFile) {
		this.fullPossibleTestpaths=fullPossibleTestPaths;
		this.fullMyTestPaths = new ArrayList<MyTestPath>();
		this.functionNode=functionNode;
		this.pathToFile=pathtoFile;
		this.createdDate = LocalDateTime.now();
		for(int pathNumber = 0; pathNumber< this.fullPossibleTestpaths.size(); pathNumber++) {
			List<ICfgNode> fullCfgNodes = this.fullPossibleTestpaths.get(pathNumber).getAllCfgNodes();
			MyTestPath myTestPath = new MyTestPath(pathNumber);
			fullCfgNodes.remove(0);
			fullCfgNodes.remove(0);
			fullCfgNodes.remove(fullCfgNodes.size()-1);
			for(int i=0;i<fullCfgNodes.size()-1;i++) {
				Edge edge = new Edge(fullCfgNodes.get(i), fullCfgNodes.get(i+1), pathNumber);
				myTestPath.addEdge(edge);
			}
			this.fullMyTestPaths.add(myTestPath); 
		}
	}
	public String getPathToFile() {
		return pathToFile;
	}
	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}
	public IFunctionNode getFunctionNode() {
		return functionNode;
	}
	public void setFunctionNode(IFunctionNode functionNode) {
		this.functionNode = functionNode;
	}
	public List<IFullTestpath> getFullPossibleFullTestpaths(){
		return this.fullPossibleTestpaths;
	}
	public List<MyTestPath> getFullMyTestPaths(){
		return this.fullMyTestPaths;
	}
	public int getIntersection2Path(MyTestPath path1, MyTestPath path2) {
		int numOfNode=0;
		for(ICfgNode node1_i : path1.getFullCfgNode()) {
			for(ICfgNode node2_i : path2.getFullCfgNode()) {
				if(node1_i==node2_i) {
					numOfNode++;
				}
			}
		}
		return numOfNode-1;
	}
	
	public void updateGraph(int pathNumber, int weight) {
		MyTestPath testPath = this.fullMyTestPaths.get(pathNumber);
		this.setVisitedPath(pathNumber);
		for(int i=0;i<this.fullMyTestPaths.size();i++) {
			MyTestPath testPath1 = this.fullMyTestPaths.get(i);
			for(Edge edge_i: testPath.getEdge()) {
				Edge edge = testPath1.searchEdge(edge_i.getNode(), edge_i.getNextNode());
				if(edge!=null) {
					edge.addWeight(weight);
				}
			}
		}
	}
	
	public void setVisitedPath(int pathNumber) {
		this.fullMyTestPaths.get(pathNumber).setGenerated(true);
	}
	
	public int countVisitedNode() {
		int count =0;
		for(MyTestPath testPath: this.fullMyTestPaths) {
			if(testPath.isGenerated()) {
				count++;
			}
		}
		return count;
	}
	
	public float getCoverage() {
		return (float)this.countVisitedNode()/(this.getFullMyTestPaths().size());
	}
	public int getNewPath() {
		int weight=-1;
		int index =-1;
		for(MyTestPath testPath: this.fullMyTestPaths) {
			if(testPath.isGenerated()==false && testPath.getVisitedNumber() < 2) {
				if(testPath.getWeight()>=weight) {
					weight=testPath.getWeight();
					index = this.fullMyTestPaths.indexOf(testPath);
				}
			}
		}
		if(index!=-1) {
			this.fullMyTestPaths.get(index).setVisitedNumber(1);
		}
		return index;
	}
	public void toTxtFile() throws IOException {
		Duration duration = Duration.between(this.createdDate,LocalDateTime.now());
		long diff = Math.abs(duration.toSeconds());
		FileWriter csvWriter = new FileWriter(this.functionNode.getFullName()+".txt",false);
		csvWriter.append(this.functionNode.getAST().getRawSignature().toString());
		csvWriter.append("\n \n");
		csvWriter.append("Path file: "+this.functionNode.getAbsolutePath());
		for(MyTestPath testPath: this.fullMyTestPaths) {
			csvWriter.append("\n Path "+this.fullMyTestPaths.indexOf(testPath)+ ": ");
			csvWriter.append(testPath.toCSV());
			csvWriter.append("\n");
			csvWriter.append("\tTest case: "+testPath.getTestCase());
			csvWriter.append("\n");
		}
		csvWriter.append("\n\nCoverage: "+this.getCoverage());
		csvWriter.append("\nGenerated time: "+ diff+"s");
		csvWriter.close();
	}
}
