package Khamd;

import config.Paths;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import cfg.CFGGenerationforSubConditionCoverage;
import cfg.ICFG;
import cfg.object.ICfgNode;
import cfg.testpath.FullTestpath;
import cfg.testpath.IFullTestpath;
import cfg.testpath.PartialTestpath;
import cfg.testpath.PossibleTestpathGeneration;
import parser.projectparser.ProjectParser;
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

public class TestPathGeneration {
	public static void main(String[] args) {

		TestPathGeneration path = new TestPathGeneration();
		try {
			int maxIterations= 1;
//			String solution = path.getSolutionInRandomPath(Paths.SYMBOLIC_EXECUTION_TEST,"basicTest3(int,int)" , maxIterations);
//			path.createGraph(Paths.TSDV_R1, "IntTest(int)", 1);
//			System.out.print(tpGen.getPossibleTestpaths().get(0));
			Graph graph = path.createGraph(Paths.SYMBOLIC_EXECUTION_TEST, "basicTest3(int,int)", 1);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public String getSolutionInRandomPath(String pathToFile, String functionName, int maxIterations) throws Exception{
		ProjectParser parser = new ProjectParser(new File(pathToFile));
		IFunctionNode function = (IFunctionNode) Search.searchNodes(parser.getRootTree(), new FunctionNodeCondition(), functionName).get(0);
		CFGGenerationforSubConditionCoverage cfgGen = new CFGGenerationforSubConditionCoverage(function);
		ICFG cfg;
		cfg = cfgGen.generateCFG();
		cfg.setFunctionNode(function);
		cfg.setIdforAllNodes();
		cfg.resetVisitedStateOfNodes();

		PossibleTestpathGeneration tpGen = new PossibleTestpathGeneration(cfg, maxIterations);
		tpGen.generateTestpaths();
		Parameter paramaters = new Parameter();
		for (INode n : ((FunctionNode) function).getArguments())
			paramaters.add(n);
		for (INode n : ((FunctionNode) function).getReducedExternalVariables())
			paramaters.add(n);
		
		ISymbolicExecution se = new SymbolicExecution(tpGen.getPossibleTestpaths().get(1), paramaters, function);
		System.out.println(tpGen.getPossibleTestpaths().get(1));
		
		List<PathConstraint> constraints = new ArrayList<>();
		for (PathConstraint c : (PathConstraints) se.getConstraints())
			constraints.add(c);
		SmtLibGeneration smt = new SmtLibGeneration(function.getArguments(), constraints);
		smt.generate();

		BufferedWriter writer = new BufferedWriter(new FileWriter("myConstraint.smt2", false));
		writer.write(smt.getSmtLibContent());
		writer.close();
		RunZ3OnCMD run = new RunZ3OnCMD("D:\\cft4cpp-core\\local\\z3\\bin\\z3", "D:\\cft4cpp-core\\myConstraint.smt2");
		run.execute();
		return  new Z3SolutionParser().getSolution(run.getSolution());
	}
	
	public Graph createGraph(String pathtoFile, String functionName,int maxIteration) throws Exception {
		Graph graph = new Graph();
		ProjectParser parser = new ProjectParser(new File(pathtoFile));
		IFunctionNode function = (IFunctionNode) Search.searchNodes(parser.getRootTree(), new FunctionNodeCondition(), functionName).get(0);
		CFGGenerationforSubConditionCoverage cfgGen = new CFGGenerationforSubConditionCoverage(function);
		ICFG cfg;
		cfg = cfgGen.generateCFG();
		cfg.setFunctionNode(function);
		cfg.setIdforAllNodes();
		cfg.resetVisitedStateOfNodes();

		PossibleTestpathGeneration tpGen = new PossibleTestpathGeneration(cfg, maxIteration);
		tpGen.generateTestpaths();
		Parameter paramaters = new Parameter();
		for (INode n : ((FunctionNode) function).getArguments())
			paramaters.add(n);
		for (INode n : ((FunctionNode) function).getReducedExternalVariables())
			paramaters.add(n);
		List<IFullTestpath> fullTestpaths = tpGen.getPossibleTestpaths();
		for(int pathNumber = 0 ; pathNumber<fullTestpaths.size(); pathNumber++) {
			IFullTestpath fullTestpath = fullTestpaths.get(pathNumber);
			List<ICfgNode> allCfgNodes = fullTestpath.getAllCfgNodes();
			allCfgNodes.remove(0);
			allCfgNodes.remove(0);
			allCfgNodes.remove(allCfgNodes.size()-1);
			for(int index =0; index<allCfgNodes.size(); index++) {
				Node node;
				if(graph.checkExist(allCfgNodes.get(index))==null) {
					
					node = new Node(allCfgNodes.get(index),pathNumber);
					graph.addNode(node);
					
				}
				else {
					node = graph.checkExist(allCfgNodes.get(index));
					node.addTestPathNumber(pathNumber);
				}
				
				if(index +1 < allCfgNodes.size()) {
					if(graph.checkExist(allCfgNodes.get(index+1))==null) {
						Node nextNode =  new Node(allCfgNodes.get(index+1),pathNumber);
						node.initNodeAndDistance(nextNode, 0);
						graph.addNode(nextNode);
					}
					else {
						node.initNodeAndDistance(graph.checkExist(allCfgNodes.get(index+1)), 0);
					}
					
				}
			}
			
		}
		
		return graph;
	}
}
