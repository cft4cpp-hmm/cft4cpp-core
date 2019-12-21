package Khamd;

import config.Paths;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.System.Logger;
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
			int randomTestPath = 0;
			Graph graph = path.createGraph(Paths.TSDV_R1_2, "myTest(int)", maxIterations);
			
			
			do{
				String solution = path.getSolutionInRandomPath(graph, randomTestPath);
				MyFunctionExection functionExection = new MyFunctionExection(graph);
				String path1 = graph.getFullMyTestPaths().get(randomTestPath).toString();
				String path2 = functionExection.getTestPath(solution);
				List<MyTestPath> myTestPaths = graph.getFullMyTestPaths();
				int i =0;
				for(MyTestPath testPath: myTestPaths) {
					if(testPath.toString().equals(path2)) {
						 i = myTestPaths.indexOf(testPath);
						 testPath.setTestCase(solution);
						 testPath.setRealString(path2);
					}
				}
				graph.updateGraph(i, 1);
				
				randomTestPath=graph.getNewPath();
				
				
			}while(graph.getNewPath()!=-1);
			
			graph.toTxtFile();
			System.out.println("Finish Generating!");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public String getSolutionInRandomPath(Graph graph, int pathNumber) throws Exception{
		ProjectParser parser = new ProjectParser(new File(graph.getPathToFile()));
		IFunctionNode function = (IFunctionNode) graph.getFunctionNode();
		CFGGenerationforSubConditionCoverage cfgGen = new CFGGenerationforSubConditionCoverage(function);
		ICFG cfg;
		cfg = cfgGen.generateCFG();
		cfg.setFunctionNode(function);
		cfg.setIdforAllNodes();
		cfg.resetVisitedStateOfNodes();

		PossibleTestpathGeneration tpGen = new PossibleTestpathGeneration(cfg,1);
		tpGen.generateTestpaths();
		
		Parameter paramaters = new Parameter();
		for (INode n : ((FunctionNode) function).getArguments())
			paramaters.add(n);
		for (INode n : ((FunctionNode) function).getReducedExternalVariables())
			paramaters.add(n);
		
		ISymbolicExecution se = new SymbolicExecution(tpGen.getPossibleTestpaths().get(pathNumber), paramaters, function);
		
		
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
		
		return new Graph(tpGen.getPossibleTestpaths(),function,pathtoFile);
	}
}
