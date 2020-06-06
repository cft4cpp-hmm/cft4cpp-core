package Khamd;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cfg.CFGGenerationforBranchvsStatementCoverage;
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
import config.ISettingv2;
import config.Paths;
import config.Settingv2;
import constraints.checker.RelatedConstraintsChecker;
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

/**
 * Generate all possible test paths
 *
 * @author DucAnh
 */
public class CFT4CPP{
	public static final String CONSTRAINTS_FILE = Settingv2.getValue(ISettingv2.SMT_LIB_FILE_PATH);
	public static final String Z3 = Settingv2.getValue(ISettingv2.SOLVER_Z3_PATH);
	final static Logger logger = Logger.getLogger(CFT4CPP.class);
	/**
	 * Represent control flow graph
	 */
	private ICFG cfg;
	private int maxIterationsforEachLoop = ITestpathGeneration.DEFAULT_MAX_ITERATIONS_FOR_EACH_LOOP;
	private FullTestpaths possibleTestpaths = new FullTestpaths();
	public List<String> testCases;
	public CFT4CPP(ICFG cfg) {
		this.cfg = cfg;
	}

	public CFT4CPP(ICFG cfg, int maxloop) {
		maxIterationsforEachLoop = maxloop;
		this.cfg = cfg;
		this.cfg.resetVisitedStateOfNodes();
		this.cfg.setIdforAllNodes();
		this.testCases = new ArrayList<String>();
	}

	/**
	 * @param cfg
	 * @param maxloop
	 * @param isResetVisitedState
	 *            true if the visit stated is marked unvisited
	 */
	public CFT4CPP(ICFG cfg, int maxloop, boolean isResetVisitedState) {
		maxIterationsforEachLoop = maxloop;
		this.cfg = cfg;

		if (isResetVisitedState) {
			this.cfg.resetVisitedStateOfNodes();
			this.cfg.setIdforAllNodes();
		}
	}

	public static void main(String[] args) throws Exception {
		ProjectParser parser = new ProjectParser(new File(Paths.TSDV_R1_2));

		IFunctionNode function = (IFunctionNode) Search
				.searchNodes(parser.getRootTree(), new FunctionNodeCondition(), "divisionTest(int,int)").get(0);

//		CFGGenerationforSubConditionCoverage cfgGen = new CFGGenerationforSubConditionCoverage(function);
		
		CFGGenerationforBranchvsStatementCoverage cfgGen = new CFGGenerationforBranchvsStatementCoverage(function);
		
		ICFG cfg = cfgGen.generateCFG();
		cfg.setFunctionNode(function);
		cfg.setIdforAllNodes();
		cfg.resetVisitedStateOfNodes();
		
		int maxIterations = 0;
		
		CFT4CPP tpGen = new CFT4CPP(cfg, maxIterations);
		LocalDateTime before = LocalDateTime.now();
		tpGen.generateTestpaths(function);
		LocalDateTime after = LocalDateTime.now();
		Duration duration = Duration.between(before,after);
		System.out.println("Num of test paths: " + tpGen.getPossibleTestpaths().size());
		System.out.println("Test Case: "+tpGen.getTestCases());
		System.out.println("Time: "+duration.toMillis()+"mili");
	}

	
	public void generateTestpaths(IFunctionNode function) {
		// Date startTime = Calendar.getInstance().getTime();
		FullTestpaths testpaths_ = new FullTestpaths();

		ICfgNode beginNode = cfg.getBeginNode();
		FullTestpath initialTestpath = new FullTestpath();
		initialTestpath.setFunctionNode(cfg.getFunctionNode());
		try {
			traverseCFG(beginNode, initialTestpath, testpaths_,function);
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

	private void traverseCFG(ICfgNode stm, FullTestpath tp, FullTestpaths testpaths, IFunctionNode function) throws Exception {
		
		tp.add(stm);
		FullTestpath tp1 = (FullTestpath) tp.clone();
		FullTestpath tp2 = (FullTestpath) tp.clone();
//		System.out.println(this.haveSolution(tp, finalConditionType)+tp.getFullPath());
//		System.out.println(stm.toString());
		if (stm instanceof EndFlagCfgNode) {
			testpaths.add((FullTestpath) tp.clone());
			testCases.add(this.solveTestpath(function, tp));
			tp.remove(tp.size() - 1);
		} else {
			ICfgNode trueNode = stm.getTrueNode();
			ICfgNode falseNode = stm.getFalseNode();

			if (stm instanceof ConditionCfgNode) {
				
				if (stm instanceof AbstractConditionLoopCfgNode) {

					int currentIterations = tp.count(trueNode);
					if (currentIterations < maxIterationsforEachLoop) {
						tp1.add(falseNode);
						if(this.haveSolution(tp1, false)) {
							traverseCFG(falseNode, tp, testpaths,function);
						}
						tp2.add(trueNode);
						if(this.haveSolution(tp2, true)) {
							traverseCFG(trueNode, tp, testpaths,function);
						}
						
//						traverseCFG(trueNode, tp, testpaths,function);
					} else {
						tp1.add(falseNode);
						if(this.haveSolution(tp1, false)) {
							traverseCFG(falseNode, tp, testpaths,function);
						}
						
					}
//						traverseCFG(falseNode, tp, testpaths,function);
				} else {
					
					
//					tp1.remove(tp1.lastIndexOf(stm));
//					ConditionCfgNode stm1 = (ConditionCfgNode) stm.clone();
//					stm1.setContent(stm1.getContent().replace("<", "=="));
////					stm1.getAst().set
//					stm1.setAst(ASTUtils.convertToIAST(stm1.getContent()));
//					tp1.add(stm1);
					tp1.add(falseNode);
//					System.out.println("false Node "+this.haveSolution(tp1, false));
//					System.out.println("full: "+tp1.getFullPath());
					
					if(this.haveSolution(tp1, false)) {
						traverseCFG(falseNode, tp, testpaths,function);
					}
					
					tp2.add(trueNode);
					if(this.haveSolution(tp2, true)) {
						traverseCFG(trueNode, tp, testpaths,function);
					}
					
					
//					System.out.println("true Node "+ this.haveSolution(tp2, true));
//					System.out.println("full "+tp2.getFullPath());
//					System.out.println(tp.getFullPath());
				}
			}
			else
				traverseCFG(trueNode, tp, testpaths,function);

			tp.remove(tp.size() - 1);
		}
	}

	protected boolean haveSolution(FullTestpath tp, boolean finalConditionType) throws Exception {
		IPartialTestpath tp1 = createPartialTestpath(tp, finalConditionType);

		String solution = solveTestpath(cfg.getFunctionNode(), tp1);
		
		if (!solution.equals(IStaticSolutionGeneration.NO_SOLUTION))
			return true;
		else {
			return false;
		}
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
		boolean isRelated = relatedConstraintsChecker.check();
		//
		if (isRelated) {
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
	public List<String> getTestCases(){
		return this.testCases;
	}
}
