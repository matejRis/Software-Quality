/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment3.checkstyletaskone;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
/**
 *
 * @author Matej
 */
public class VariablesInMethodCheck extends AbstractCheck {

    /*private static final int DEFAULT_MAX_LINES_OF_CODE = 30;
    private static final int DEFAULT_MAX_CYCLOMATIC_COMPLEXITY = 10;
    private static final int DEFAULT_MAX_NESTING_DEPTH = 1;*/
    private static final int DEFAULT_MAX_ACCESSED_VARIABLES = 6;
    
    /*private int maxLinesOfCode = DEFAULT_MAX_LINES_OF_CODE;
    private int maxCyclomaticComplexity = DEFAULT_MAX_CYCLOMATIC_COMPLEXITY;
    private int maxNestingDepth = DEFAULT_MAX_NESTING_DEPTH;*/
    private int maxAccessedVariables = DEFAULT_MAX_ACCESSED_VARIABLES;
    
    /*private MethodLengthCheck methodLengthCheck;
    private CyclomaticComplexityCheck methodComplexityCheck;
    private NestedTryDepthCheck nestedTryDepthCheck;
    NestedForDepthCheck nestedForDepthCheck;
    NestedIfDepthCheck nestedIfDepthCheck;*/
    
    private boolean insideMethod;
    private int variableCount;
    

    /*@Override
    public void init() {
         methodLengthCheck = new MethodLengthCheck();
         methodComplexityCheck = new CyclomaticComplexityCheck();
         nestedTryDepthCheck =  new NestedTryDepthCheck();
         nestedForDepthCheck = new NestedForDepthCheck();
         nestedIfDepthCheck = new NestedIfDepthCheck();
    }*/
    
    @Override
    public int[] getDefaultTokens() {

        return new int[] { TokenTypes.METHOD_DEF, TokenTypes.VARIABLE_DEF };
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }
    
    @Override
    public void visitToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.METHOD_DEF) {
            insideMethod = true;
        }
        
        if (insideMethod && ast.getType() == TokenTypes.VARIABLE_DEF) {
            variableCount += 1;
        }
        
        //cyclomaticComplexityCheck(ast);
        //methodLengthCheck(ast);
        //nestedDepthCheck(ast);
        //accessedVariablesCheck(ast);
    }
    
    @Override
    public void leaveToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.METHOD_DEF) {
            if (variableCount > maxAccessedVariables) {
                log(ast.getLineNo(), "Too many accessed variables in single method (max is " + maxAccessedVariables + ")");
            }
            variableCount = 0;
            insideMethod = false;
        }
    }
    
    /*private void cyclomaticComplexityCheck(DetailAST ast)  {
        methodComplexityCheck.setMax(maxCyclomaticComplexity);
        methodComplexityCheck.visitToken(ast);
    }
     
    private void methodLengthCheck(DetailAST ast)  {
        methodLengthCheck.setMax(maxLinesOfCode);
        methodLengthCheck.setCountEmpty(false);
        methodLengthCheck.visitToken(ast);
    }
    
    private void nestedDepthCheck(DetailAST ast)  {
        nestedIfDepthCheck(ast);
        nestedForDepthCheck(ast);
        nestedTryDepthCheck(ast);
    }
    
    private void nestedTryDepthCheck(DetailAST ast) {
        nestedTryDepthCheck.setMax(maxNestingDepth);
        nestedTryDepthCheck.visitToken(ast);
    }

    private void nestedForDepthCheck(DetailAST ast) {
        nestedForDepthCheck.setMax(maxNestingDepth);
        nestedForDepthCheck.visitToken(ast);
    }

    private void nestedIfDepthCheck(DetailAST ast) {
        nestedIfDepthCheck.setMax(maxNestingDepth);
        nestedIfDepthCheck.visitToken(ast);
    }
        
     public void setMaxLinesOfCodes(int maxLinesOfCodes) {
        this.maxLinesOfCode = maxLinesOfCodes;
    }

    public void setMaxCyclomaticComlexity(int maxCyclomaticComlexity) {
        this.maxCyclomaticComplexity = maxCyclomaticComlexity;
    }

    public void setMaxNestingDepth(int maxNestingDepth) {
        this.maxNestingDepth = maxNestingDepth;
    }*/

    public void setMaxAccesedVariables(int maxAccesedVariables) {
        this.maxAccessedVariables = maxAccesedVariables;
    }

    
}
