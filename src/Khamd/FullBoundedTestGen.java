package Khamd;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.ptg.Deleted3DPxg;

import cfg.CFG;
import cfg.CFGGenerationforSubConditionCoverage;
import cfg.ICFG;
import cfg.object.AbstractConditionLoopCfgNode;
import cfg.object.ConditionCfgNode;
import cfg.object.EndFlagCfgNode;
import cfg.object.ICfgNode;
import cfg.testpath.FullTestpath;
import cfg.testpath.FullTestpaths;
import cfg.testpath.IPartialTestpath;
import cfg.testpath.IStaticSolutionGeneration;
import cfg.testpath.ITestpathGeneration;
import cfg.testpath.ITestpathInCFG;
import cfg.testpath.PartialTestpath;
import cfg.testpath.PossibleTestpathGeneration;
import config.FunctionConfig;
import config.ISettingv2;
import config.Paths;
import config.Settingv2;
import constraints.checker.RelatedConstraintsChecker;
import normalizer.FunctionNormalizer;
import parser.projectparser.ProjectParser;
import testdatagen.se.ISymbolicExecution;
import testdatagen.se.Parameter;
import testdatagen.se.PathConstraint;
import testdatagen.se.SymbolicExecution;
import testdatagen.se.solver.ISmtLibGeneration;
import testdatagen.se.solver.RunZ3OnCMD;
import testdatagen.se.solver.SmtLibGeneration;
import testdatagen.se.solver.Z3SolutionParser;
import tree.object.IFunctionNode;
import tree.object.IVariableNode;
import utils.ASTUtils;
import utils.SpecialCharacter;
import utils.Utils;
import utils.search.FunctionNodeCondition;
import utils.search.Search;

public class FullBoundedTestGen {
	public static final String CONSTRAINTS_FILE = Settingv2.getValue(ISettingv2.SMT_LIB_FILE_PATH);
	public static final String Z3 = Settingv2.getValue(ISettingv2.SOLVER_Z3_PATH);
	final static Logger logger = Logger.getLogger(FullBoundedTestGen.class);
	/**
	 * Represent control flow graph
	 */
	private ICFG cfg;
	private int maxIterationsforEachLoop = ITestpathGeneration.DEFAULT_MAX_ITERATIONS_FOR_EACH_LOOP;
	private FullTestpaths possibleTestpaths = new FullTestpaths();
	public FullBoundedTestGen() {
		// TODO Auto-generated constructor stub
		this.cfg = cfg;
	}

	public FullBoundedTestGen(ICFG cfg, int maxloop) {
		maxIterationsforEachLoop = maxloop;
		this.cfg = cfg;

		this.cfg.resetVisitedStateOfNodes();
		this.cfg.setIdforAllNodes();
	}

	public static void main(String[] args) throws Exception {
		CFG cfg;
		ProjectParser parser = new ProjectParser(new File(Paths.TSDV_R1_2));
		IFunctionNode function;
		String functionName = "PDF(int,int,int)";
		function = (IFunctionNode) Search
				.searchNodes(parser.getRootTree(), new FunctionNodeCondition(), functionName)
				.get(0);
//		function.getAST().toString().replaceAll("<", "==");
		FunctionConfig functionConfig = new FunctionConfig();
		functionConfig.setSolvingStrategy(ISettingv2.SUPPORT_SOLVING_STRATEGIES[0]);
		((IFunctionNode ) function).setFunctionConfig(functionConfig);
		FunctionNormalizer fnNorm = ((IFunctionNode) function).normalizedAST();
		String normalizedCoverage = fnNorm.getNormalizedSourcecode();
		((IFunctionNode ) function).setAST(fnNorm.getNormalizedAST());
		IFunctionNode clone = (IFunctionNode) function.clone();
		clone.setAST(Utils.getFunctionsinAST(normalizedCoverage.toCharArray()).get(0));
		CFGGenerationforSubConditionCoverage cfgGen = new CFGGenerationforSubConditionCoverage(clone);
		
		cfg = (CFG) cfgGen.generateCFG();
		cfg.setFunctionNode(clone);
		FullBoundedTestGen tpGen = new FullBoundedTestGen(cfg,1);
		
//		Hashtable<IVariableNode, HashSet<Number>> dict = new Hashtable<IVariableNode, HashSet<Number>>();
		List<IVariableNode> arguments = function.getArguments();
//		for(IVariableNode variable : arguments) {
//			dict.put(variable, new HashSet<Number>());
//		}
		Set<String> testCases = new HashSet<String>();
		Random rand = new Random();
		LocalDateTime before = LocalDateTime.now();
		tpGen.generateTestpaths(testCases);
		LocalDateTime after = LocalDateTime.now();
		List<String> newTestCases= new ArrayList<String>();
		for(String testCase: testCases) {
			for(IVariableNode variable: arguments) {
				if(!testCase.contains(variable.toString())) {
					testCase+=variable.toString()+"="+rand.nextInt(100)+";";
				}
			}
			newTestCases.add(testCase);
		}
		System.out.println("Result: ");
		for(String testcase: newTestCases) {
			System.out.println(testcase);
		}
		System.out.println("Number of test cases: "+ testCases.size());
		Duration duration = Duration.between(before,after);
		System.out.println("Time: "+duration.toSeconds()+"s");
		
	}

	public void generateTestpaths(Set<String> testCases) {
		// Date startTime = Calendar.getInstance().getTime();
		FullTestpaths testpaths_ = new FullTestpaths();

		ICfgNode beginNode = cfg.getBeginNode();
		FullTestpath initialTestpath = new FullTestpath();
		initialTestpath.setFunctionNode(cfg.getFunctionNode());
		try {
			traverseCFG(beginNode, initialTestpath, testpaths_, testCases);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (ITestpathInCFG tp : testpaths_)
			tp.setFunctionNode(cfg.getFunctionNode());

		possibleTestpaths = testpaths_;

		// Calculate the running time
		// Date end = Calendar.getInstance().getTime();
		// totalRunningTime = end.getTime() - startTime.getTime();
		// logger.debug("Total running time: " + totalRunningTime + " ms");
		// logger.debug("Solving time: " + solvingTime + " ms");
		// logger.debug("Number of solving calls: " + numberOfSolvingCalls + "
		// ms");
		// logger.debug(
		// "Number of solving calls that does not have solution: " +
		// numberOfSolvingCallsThatNoSolution + " ms");
	}
private void traverseCFG(ICfgNode stm, FullTestpath tp, FullTestpaths testpaths,Set<String> testCases) throws Exception {
		
		tp.add(stm);
//		System.out.println(this.haveSolution(tp, finalConditionType)+tp.getFullPath());
//		System.out.println(stm.toString());
		if (stm instanceof EndFlagCfgNode) {
			testpaths.add((FullTestpath) tp.clone());
			tp.remove(tp.size() - 1);
		} else {
			ICfgNode trueNode = stm.getTrueNode();
			ICfgNode falseNode = stm.getFalseNode();

			if (stm instanceof ConditionCfgNode) {
				
				if (stm instanceof AbstractConditionLoopCfgNode) {

					int currentIterations = tp.count(trueNode);
					if (currentIterations < maxIterationsforEachLoop) {
						
						traverseCFG(falseNode, tp, testpaths, testCases);
						traverseCFG(trueNode, tp, testpaths,testCases);
					} else
						traverseCFG(falseNode, tp, testpaths,testCases);
				} else {
					
					
						for(int i = -1; i<2;i++) {
							FullTestpath tp1 = (FullTestpath) tp.clone();
							ConditionCfgNode stm1 = (ConditionCfgNode) stm.clone();
//							if(!stm1.isGenForBound()) {
								tp1.remove(tp.lastIndexOf(stm));
							stm1.setContent(stm1.getContent().replaceAll("<=|>=|<|>|!=", "=="));
							stm1.setAst(ASTUtils.convertToIAST(stm1.getContent()+"+"+i));
							tp1.add(stm1);
							tp1.add(trueNode);
//							System.out.println("tp:"+tp1.toString());
							
							String result = this.haveSolution(tp1, true);
//							System.out.println("result: "+result);
							if(!result.equals(IStaticSolutionGeneration.NO_SOLUTION)) {
								testCases.add(result.replaceAll(";;", ";"));
							}
							
//							String[] listResult = result.split(";");
//							
//							if(listResult.length!=0) {
//							for(String resulti: listResult) {
//								for(IVariableNode variableNode : dict.keySet()) {
//									if(resulti.toString().contains(variableNode.toString())){
//										String temp = resulti.split("=")[1];
//										if(variableNode.getFullType().equals("int")) {
//											dict.get(variableNode).add(Integer.parseInt(temp));
//										}
//										else {
//											dict.get(variableNode).add(Float.parseFloat(temp));
//										}
//										
//									}
//								}
//							}
							
//						}
						}
						
						
//						((ConditionCfgNode) stm).setGenForBound(true);
//					}
					
					traverseCFG(falseNode, tp, testpaths,testCases);
//					FullTestpath tp2 = (FullTestpath) tp.clone();
//					tp2.add(trueNode);
//					if(this.haveSolution(tp2, true)) {
					traverseCFG(trueNode, tp, testpaths,testCases);
//					}
					
					
//					System.out.println("true Node "+ this.haveSolution(tp2, true));
//					System.out.println("full "+tp2.getFullPath());
//					System.out.println(tp.getFullPath());
				}
			}
			else
				traverseCFG(trueNode, tp, testpaths,testCases);

			tp.remove(tp.size() - 1);
		}
	}
protected String haveSolution(FullTestpath tp, boolean finalConditionType) throws Exception {
	IPartialTestpath tp1 = createPartialTestpath(tp, finalConditionType);

	String solution = solveTestpath(cfg.getFunctionNode(), tp1);
	return solution;
//	if (!solution.equals(IStaticSolutionGeneration.NO_SOLUTION))
//		return true;
//	else {
//		return false;
//	}
}

protected IPartialTestpath createPartialTestpath(FullTestpath fullTp, boolean finalConditionType) {
	IPartialTestpath partialTp = new PartialTestpath();
	for (ICfgNode node : fullTp.getAllCfgNodes())
		partialTp.getAllCfgNodes().add(node);

	partialTp.setFinalConditionType(finalConditionType);
	return partialTp;
}

protected String solveTestpath(IFunctionNode function, ITestpathInCFG testpath) throws Exception {
	/*
	 * Get the passing variables of the given function
	 */
	Parameter paramaters = new Parameter();
	for (IVariableNode n : function.getArguments())
		paramaters.add(n);
	for (IVariableNode n : function.getReducedExternalVariables())
		paramaters.add(n);

	/*
	 * Get the corresponding path constraints of the test path
	 */
	ISymbolicExecution se = new SymbolicExecution(testpath, paramaters, function);

	// fast checking
	RelatedConstraintsChecker relatedConstraintsChecker = new RelatedConstraintsChecker(
			se.getConstraints().getNormalConstraints(), function);
//	boolean isRelated = relatedConstraintsChecker.check();
	//
	if (true) {
		if (se.getConstraints().getNormalConstraints().size()
				+ se.getConstraints().getNullorNotNullConstraints().size() > 0) {
			/*
			 * Solve the path constraints
			 */
			ISmtLibGeneration smtLibGen = new SmtLibGeneration(function.getPassingVariables(),
					se.getConstraints().getNormalConstraints());
			smtLibGen.generate();

			Utils.writeContentToFile(smtLibGen.getSmtLibContent(), CONSTRAINTS_FILE);

			RunZ3OnCMD z3 = new RunZ3OnCMD(Z3, CONSTRAINTS_FILE);
			z3.execute();
			logger.debug("solving done");
			String staticSolution = new Z3SolutionParser().getSolution(z3.getSolution());

			if (staticSolution.equals(IStaticSolutionGeneration.NO_SOLUTION)) {
				return IStaticSolutionGeneration.NO_SOLUTION;
			} else {
				if (se.getConstraints().getNullorNotNullConstraints().size() > 0)
					for (PathConstraint nullConstraint : se.getConstraints().getNullorNotNullConstraints())
						staticSolution += nullConstraint + SpecialCharacter.END_OF_STATEMENT;

				if (se.getConstraints().getNullorNotNullConstraints().size() > 0)
					return staticSolution + "; " + se.getConstraints().getNullorNotNullConstraints();
				else
					return staticSolution + ";";
			}
		} else
			return IStaticSolutionGeneration.NO_SOLUTION;
	} else
		return IStaticSolutionGeneration.EVERY_SOLUTION;
}

public ICFG getCfg() {
	return cfg;
}

public void setCfg(ICFG cfg) {
	this.cfg = cfg;
}

public int getMaxIterationsforEachLoop() {
	return maxIterationsforEachLoop;
}

public void setMaxIterationsforEachLoop(int maxIterationsforEachLoop) {
	this.maxIterationsforEachLoop = maxIterationsforEachLoop;
}

public FullTestpaths getPossibleTestpaths() {
	return possibleTestpaths;
}

}
