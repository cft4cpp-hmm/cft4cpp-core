package Khamd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.System.Logger;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.eclipse.cdt.core.parser.util.IUnaryPredicate;
import org.eclipse.cdt.core.settings.model.COutputEntry;

import com.alee.log.Log;
import com.ibm.icu.text.UFieldPosition;

import HMM.HMMGraph;
import HMM.Node;
import cfg.ICFG;
import cfg.object.BranchInCFG;
import cfg.object.ICfgNode;
import cfg.testpath.FullTestpath;
import cfg.testpath.FullTestpaths;
import cfg.testpath.IFullTestpath;
import testdatagen.se.ISymbolicExecution;
import testdatagen.se.Parameter;
import testdatagen.se.PathConstraint;
import testdatagen.se.PathConstraints;
import testdatagen.se.SymbolicExecution;
import tree.object.FunctionNode;
import tree.object.IFunctionNode;
import tree.object.INode;

public class Graph {
	
	private List<IFullTestpath> fullPossibleTestpaths;
	private List<ProbTestPath> fullProbTestPaths;
	private IFunctionNode functionNode;
	private String pathToFile;
	private ICFG cfg;
	private LocalDateTime createdDate;
	private int epoches ;
	public Graph(ICFG cfg,List<IFullTestpath> fullPossibleIFullTestpaths, IFunctionNode functionNode, String pathtoFile) {
		
		List<IFullTestpath> fullTestpaths = fullPossibleIFullTestpaths;
		this.fullPossibleTestpaths=fullPossibleIFullTestpaths;
		this.functionNode=functionNode;
		this.pathToFile=pathtoFile;
		this.createdDate = LocalDateTime.now();
		this.fullProbTestPaths = new ArrayList<ProbTestPath>();
		this.cfg=cfg;
		this.epoches = 1;
		
		for(int pathNumber = 0; pathNumber< this.fullPossibleTestpaths.size(); pathNumber++) {
			List<ICfgNode> fullCfgNodes = (ArrayList<ICfgNode>)this.fullPossibleTestpaths.get(pathNumber).getAllCfgNodes();
			fullCfgNodes= new ArrayList<ICfgNode>(fullCfgNodes);
			ProbTestPath myTestPath = new ProbTestPath(pathNumber);
//			System.out.println(fullCfgNodes);
			fullCfgNodes.remove(0);
			fullCfgNodes.remove(fullCfgNodes.size()-1);
			for(int i=0;i<fullCfgNodes.size();i++) {
				if(fullCfgNodes.get(i).toString().contains("{")||fullCfgNodes.get(i).toString().contains("}")) {
					fullCfgNodes.remove(i);
					i=i-1;
				}
			}
			
			for(int i=0;i<fullCfgNodes.size()-1;i++) {
				
				Edge edge = new Edge(fullCfgNodes.get(i), fullCfgNodes.get(i+1), pathNumber);
				myTestPath.addEdge(edge);
			}
//			System.out.println(myTestPath.getFullCfgNode());
			this.fullProbTestPaths.add(myTestPath); 
		}
	}
	
	public int getIntersection2Path(ProbTestPath path1, ProbTestPath path2) {
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
	
	public void updateGraph(int pathNumber, int weight, HMMGraph hmmGraph) {
		ProbTestPath testPath = this.fullProbTestPaths.get(pathNumber);
//		this.setVisitedPath(pathNumber);
		testPath.setGenerated(true);
		for(Edge edge : testPath.getEdge()) {
			
			Node node = hmmGraph.getNode(edge.getNode());
			node.updateProbability(edge.getNextNode());
			
			
		}
		hmmGraph.recomputeProbability();
		for(ProbTestPath testPath2: this.getFullProbTestPaths()) {
			for(Edge edge: testPath2.getEdge()) {
				Node node = hmmGraph.getNode(edge.getNode());
				edge.setWeight(node.getProbability(edge.getNextNode()));
			}
		}
//		for(int i=0;i<this.fullProbTestPaths.size();i++) {
//			ProbTestPath testPath1 = this.fullProbTestPaths.get(i);
//			for(Edge edge_i: testPath.getEdge()) {
//				Edge edge = testPath1.searchEdge(edge_i.getNode(), edge_i.getNextNode());
//				if(edge!=null) {
//					edge.addWeight(weight);
//				}
//			}
//		}
		
		
	}
	
	public void computeProbabilityForAllPath() {
		Node node;
		Node nextNode;
		HMMGraph hmmGraph = new HMMGraph();
		for(ProbTestPath probTestPath : this.getFullProbTestPaths()) {
			for(Edge edge : probTestPath.getEdge()) {
				node = new Node(edge.getNode());
				nextNode = new Node(edge.getNextNode());
			}
		}
	}
	
	public void setVisitedPath(int pathNumber) {
		this.fullProbTestPaths.get(pathNumber).setGenerated(true);
	}
	
	public int countVisitedNode() {
		int count =0;
		for(ProbTestPath testPath: this.fullProbTestPaths) {
			if(testPath.isGenerated()) {
				count++;
			}
		}
		return count;
	}
	
	public float getCoverage() {
		return (float)this.countVisitedNode()/(this.getFullProbTestPaths().size());
	}
	public int getNewPath() {
		int weight=-1;
		int index =-1;
		for(ProbTestPath testPath: this.fullProbTestPaths) {
			if(testPath.isGenerated()==false && testPath.getVisitedNumber() < this.epoches) {
				if(testPath.getWeight()>=weight) {
					weight=testPath.getWeight();
					index = this.fullProbTestPaths.indexOf(testPath);
				}
			}
		}
		if(index!=-1) {
			this.fullProbTestPaths.get(index).setVisitedNumber(1);
		}
		
		return index;
	}
	
	public void addConstraint() throws Exception {
		Parameter paramaters = new Parameter();
		for (INode n : ((FunctionNode) this.functionNode).getArguments())
			paramaters.add(n);
		for (INode n : ((FunctionNode) this.functionNode).getReducedExternalVariables())
			paramaters.add(n);
		for(IFullTestpath fullTestpath:this.getFullPossibleFullTestpaths()) {
			ISymbolicExecution se = new SymbolicExecution(fullTestpath, paramaters, this.functionNode);
			int path = this.getFullPossibleFullTestpaths().indexOf(fullTestpath);
			this.getFullProbTestPaths().get(path).setConstraints(se.getConstraints());
			
		}

	}
	
	public void toTxtFile() throws IOException {
		Duration duration = Duration.between(this.createdDate,LocalDateTime.now());
		long diff = Math.abs(duration.toSeconds());
		FileWriter csvWriter = new FileWriter(this.functionNode.getFullName()+".txt",false);
		csvWriter.append(this.functionNode.getAST().getRawSignature().toString());
		csvWriter.append("\n \n");
		csvWriter.append("Path file: "+this.functionNode.getAbsolutePath()+" \n");
		for(ProbTestPath testPath: this.fullProbTestPaths) {
			csvWriter.append("\n Path "+this.fullProbTestPaths.indexOf(testPath)+ ": ");
			csvWriter.append(testPath.toString());
			csvWriter.append("\n");
			csvWriter.append("\tTest case: "+testPath.getTestCase());
			csvWriter.append("\n");
		}
		csvWriter.append("\n\nPath Coverage: "+this.getCoverage());
		csvWriter.append("\nStatement Coverage: "+this.getCfg().computeStatementCoverage());
		csvWriter.append("\nGenerated time: "+ diff+"s");
		csvWriter.close();
		
	}
	public void toHtml() throws IOException {
		Duration duration = Duration.between(this.createdDate,LocalDateTime.now());
		long diff = Math.abs(duration.toSeconds());
		FileWriter csvWriter = new FileWriter("hmm_report.html",false);
		String valueString = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"\r\n" + 
				"<head> <link rel=\"stylesheet\" type=\"text/css\" href=\"hmm_report.css\">\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"    <h2>Markov Chain Report</h2>\r\n" + 
				
				"    <div class=\"table-wrapper\">\r\n" + 
				"        <table class=\"fl-table\">\r\n" + 
				"            <thead>\r\n" + 
				"                <tr>\r\n" + 
				"                    <th>PathNumber</th>\r\n" + 
				"                    <th style=\"width: 800px\">Path</th>\r\n" + 
				"                    <th>Test Case</th>\r\n" + 
				"                </tr>\r\n" + 
				"            </thead>\r\n" + 
				"            <tbody>";
		for(ProbTestPath testPath: this.getFullProbTestPaths()) {
			valueString+=testPath.toString();
		}
//		valueString+=;
		valueString+="   <tbody>\r\n" + 
				"        </table></div>\r\n" + 
				"<div class=\"conlusion\">\n"+
		        "<pre>"+this.functionNode.getAST().getRawSignature().toString()+
		        "</pre>"+
		        "<div>Path Coverage: "+this.getCoverage()+"</div>\r\n" + 
		        "        <div>Generated Time: "+diff+"s</div>\r\n" + 
		        "        <div>Statement Cover: "+this.getCfg().computeStatementCoverage()+"</div></div>\r\n"+
				"    </div>\r\n" + 
				"</body></html>";
		
		csvWriter.append(valueString);
		csvWriter.close();
	}
	public void createProbabilityForTestPath(HMMGraph hmmGraph) {
		List<Float> proList = new ArrayList<Float>();
		for(ProbTestPath testPath: this.getFullProbTestPaths()) {
			proList = new ArrayList<Float>();
			for(int i=0;i<testPath.getFullCfgNode().size()-1;i++) {
				Node node = hmmGraph.getNode(testPath.getFullCfgNode().get(i));
				ICfgNode nextCfgNode = testPath.getFullCfgNode().get(i+1);
				proList.add(node.getProbability(nextCfgNode));
			}
			testPath.setProList(proList);
		}
	}
	
	public ICFG getCfg() {
		return cfg;
	}
	public void setCfg(ICFG cfg) {
		this.cfg = cfg;
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
	
	public List<ProbTestPath> getFullProbTestPaths(){
		return this.fullProbTestPaths;
	}

	public int getEpoches() {
		return epoches;
	}

	public void setEpoches(int epoches) {
		this.epoches = epoches;
	}
	
	
}
