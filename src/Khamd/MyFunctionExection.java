package Khamd;

import java.io.File;
import java.io.IOException;

import config.FunctionConfig;
import config.ParameterBound;
import config.Paths;
import parser.projectparser.ProjectParser;
import testdatagen.FunctionExecution;
import tree.object.FunctionNode;
import utils.Utils;
import utils.search.FunctionNodeCondition;
import utils.search.Search;

public class MyFunctionExection {
	
	private Graph graph;
	private FunctionExecution functionExecution;
	
	public MyFunctionExection(Graph graph) throws Exception {
		this.graph = graph;
		this.functionExecution = new FunctionExecution();
		
		
	}
	
	public String getTestPath(String preparedInput) throws Exception {
		String testedProject = graph.getPathToFile();
	
		System.out.println(testedProject);
		/**
		 * Create a clone
		 */
		
		File clone = Utils.copy(testedProject);
		Paths.CURRENT_PROJECT.CLONE_PROJECT_PATH = clone.getAbsolutePath();
		
		ProjectParser parser = new ProjectParser(clone);
		FunctionNode testedFunction = (FunctionNode) Search
				.searchNodes(parser.getRootTree(), new FunctionNodeCondition(), graph.getFunctionNode().getName()).get(0);

		//FunctionNode testedFunction = (FunctionNode)graph.getFunctionNode();
		FunctionConfig config = new FunctionConfig();
		config.setCharacterBound(new ParameterBound(32, 100));
		config.setIntegerBound(new ParameterBound(0, 100));
		testedFunction.setFunctionConfig(config);

		

		/**
		 * Find test path given a test case
		 */
		this.functionExecution = new FunctionExecution();
		this.functionExecution.setTestedFunction(testedFunction);
		this.functionExecution.setPreparedInput(preparedInput);
		this.functionExecution.setClonedProject(clone.getCanonicalPath());
		this.functionExecution.analyze(this.functionExecution.getTestedFunction(), this.functionExecution.getPreparedInput());
		return this.functionExecution.analyze(this.functionExecution.getTestedFunction(), this.functionExecution.getPreparedInput());
	}
	
}
