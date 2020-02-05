package Khamd;

import config.Paths;
import normalizer.FunctionNormalizer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.fife.ui.rsyntaxtextarea.modes.GroovyTokenMaker;

import HMM.HMMGraph;
import HMM.Node;
import cfg.CFGGenerationforSubConditionCoverage;
import cfg.ICFG;
import cfg.object.ICfgNode;
import cfg.testpath.FullTestpath;
import cfg.testpath.IFullTestpath;
import cfg.testpath.PartialTestpath;
import cfg.testpath.PossibleTestpathGeneration;
import parser.projectparser.ProjectParser;
import testdata.object.TestpathString_Marker;
import testdatagen.FunctionExecution;
import testdatagen.coverage.CFGUpdater_Mark;
import testdatagen.se.IPathConstraints;
import testdatagen.se.ISymbolicExecution;
import testdatagen.se.Parameter;
import testdatagen.se.PathConstraint;
import testdatagen.se.PathConstraints;
import testdatagen.se.SymbolicExecution;
import testdatagen.se.solver.RunZ3OnCMD;
import testdatagen.se.solver.SmtLibGeneration;
import testdatagen.se.solver.Z3SolutionParser;
import tree.object.FunctionNode;
import tree.object.IFunctionNode;
import tree.object.INode;
import utils.search.FunctionNodeCondition;
import utils.search.Search;

public class Main {
	public static String pathToZ3 ="D:\\cft4cpp-core\\local\\z3\\bin\\z3.exe";
	public static String pathToMingw32 = "D:\\program files\\Dev-Cpp\\MinGW64\\bin\\mingw32-make.exe";
	public static String pathToGCC = "D:\\program files\\Dev-Cpp\\MinGW64\\bin\\gcc.exe";
	public static String pathToGPlus = "D:\\program files\\Dev-Cpp\\MinGW64\\bin\\g++.exe";
	public static String pathToConstraint = "D:\\cft4cpp-core\\myConstraint.smt2";
	public static SmtLibGeneration smt = new SmtLibGeneration();
	
	public static void main(String[] args) {
		
		Main Prob = new Main();
		int epoch = 2;
		try {
			int maxIterations= 5;
			int randomTestPath = 0;
			Graph graph = Prob.createGraph(Paths.TSDV_R1_2, "doubleTest(double)", maxIterations);
			graph.setEpoches(epoch);
			graph.addConstraint();
			HMMGraph hmmGraph = new HMMGraph();
			Node node;
			Node nextNode;
			String solution ;
			ProbFunctionExection functionExection = new ProbFunctionExection(graph,pathToZ3,pathToMingw32,pathToGCC,pathToGPlus);
			int pathNumber = graph.getNewPath();

			for(ProbTestPath testPath: graph.getFullProbTestPaths()) {
				for(Edge edge: testPath.getEdge()) {
					node = new Node(edge.getNode());
					nextNode = new Node(edge.getNextNode());
					hmmGraph.addNode(node, nextNode, (float)edge.getWeight());
				}
			}
			
			do {
				solution = Prob.getSolutionInRandomPath(graph, pathNumber);
				solution=solution.replace("(","");
				solution=solution.replace(")", "");
				if(solution=="") {
					pathNumber=graph.getNewPath();
					continue;
				}
				
				TestpathString_Marker testpath = functionExection.getEncodedPath(solution);
				CFGUpdater_Mark updater = new CFGUpdater_Mark(testpath,graph.getCfg());
				updater.updateVisitedNodes();
				ProbTestPath trackedPath = graph.getFullProbTestPaths().get(pathNumber);
				List<ICfgNode> visitedPath = updater.getUpdatedCFGNodes().getAllCfgNodes();
				
				boolean isRight = false;
				for(int i=0;i< graph.getFullProbTestPaths().size();i++) {
					ProbTestPath myTestPath = graph.getFullProbTestPaths().get(i);
					if(myTestPath.compare(visitedPath)) {
						graph.updateGraph(i, 1, hmmGraph);
						myTestPath.setToString(updater.getUpdatedCFGNodes().getFullPath());
						myTestPath.setTestCase(solution);
						isRight=true;
						break;
					}
				}
				
				if(isRight==false) {
					graph.updateGraph(pathNumber, 1, hmmGraph);
					trackedPath.setTestCase(solution);
				}
				
				pathNumber=graph.getNewPath();
			}while(pathNumber!=-1);
			
			functionExection.deleteClone();
			hmmGraph.recomputeProbability();
			graph.createProbabilityForTestPath(hmmGraph);
			graph.toHtml();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Finish Generting!");
		
	}
	
	public String getSolutionInRandomPath(Graph graph, int pathNumber) throws Exception{
		IFunctionNode function = (IFunctionNode) graph.getFunctionNode();

		List<PathConstraint> constraints =new ArrayList<PathConstraint>();
		for (PathConstraint c : (PathConstraints) graph.getFullProbTestPaths().get(pathNumber).getConstraints()) {
			constraints.add(c);
		}
		
		smt.setTestcases(function.getArguments());
		smt.setConstraints(constraints);
		smt.generate();
		BufferedWriter writer = new BufferedWriter(new FileWriter("myConstraint.smt2", false));
		writer.write(smt.getSmtLibContent());
		writer.close();
		RunZ3OnCMD run = new RunZ3OnCMD(pathToZ3, pathToConstraint);
		run.execute();
		return new Z3SolutionParser().getSolution(run.getSolution());
	}
	
	
	public Graph createGraph(String pathtoFile, String functionName,int maxIteration) throws Exception {
		ProjectParser parser = new ProjectParser(new File(pathtoFile));
		IFunctionNode function = (IFunctionNode) Search.searchNodes(parser.getRootTree(), new FunctionNodeCondition(), functionName).get(0);
		FunctionNormalizer fnNorm = function.normalizedAST();
		function.setAST(fnNorm.getNormalizedAST());
		ICFG cfg;
		cfg = function.generateCFG();
		cfg.generateAllPossibleTestpaths(maxIteration);
		PossibleTestpathGeneration tpGen = new PossibleTestpathGeneration(cfg,maxIteration);
		tpGen.generateTestpaths();
		return new Graph(cfg,tpGen.getPossibleTestpaths(),function,pathtoFile);
	}
}