/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment3.checkstyletaskone;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.checks.coding.NestedForDepthCheck;
import com.puppycrawl.tools.checkstyle.checks.coding.NestedIfDepthCheck;
import com.puppycrawl.tools.checkstyle.checks.coding.NestedTryDepthCheck;
import com.puppycrawl.tools.checkstyle.checks.metrics.CyclomaticComplexityCheck;
import com.puppycrawl.tools.checkstyle.checks.sizes.MethodLengthCheck;
import com.sun.tools.javac.util.ArrayUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Matej
 */
public class BrainMethodCheck extends AbstractCheck {

    private static final int DEFAULT_MAX_LINES_OF_CODE = 30;
    private static final int DEFAULT_MAX_CYCLOMATIC_COMPLEXITY = 10;
    private static final int DEFAULT_MAX_NESTING_DEPTH = 1;
    private static final int DEFAULT_MAX_ACCESSED_VARIABLES = 6;
    
    
    private int maxLinesOfCode = DEFAULT_MAX_LINES_OF_CODE;
    private int maxCyclomaticComplexity = DEFAULT_MAX_CYCLOMATIC_COMPLEXITY;
    private int maxNestingDepth = DEFAULT_MAX_NESTING_DEPTH;
    private int maxAccessedVariables = DEFAULT_MAX_ACCESSED_VARIABLES;
    
    private MethodLengthCheck methodLengthCheck;
    private CyclomaticComplexityCheck methodComplexityCheck;
    private NestedTryDepthCheck nestedTryDepthCheck;
    NestedForDepthCheck nestedForDepthCheck;
    NestedIfDepthCheck nestedIfDepthCheck;
    

    @Override
    public void init() {
         methodLengthCheck = new MethodLengthCheck();
         methodComplexityCheck = new CyclomaticComplexityCheck();
         nestedTryDepthCheck =  new NestedTryDepthCheck();
         nestedForDepthCheck = new NestedForDepthCheck();
         nestedIfDepthCheck = new NestedIfDepthCheck();
    }
    
    @Override
    public int[] getDefaultTokens() {
        /*Set tokens = new HashSet();
        
        tokens.addAll(Arrays.asList(methodLengthCheck.getDefaultTokens()));
        tokens.addAll(Arrays.asList(methodComplexityCheck.getDefaultTokens()));
        tokens.addAll(Arrays.asList(nestedTryDepthCheck.getDefaultTokens()));
        tokens.addAll(Arrays.asList(nestedForDepthCheck.getDefaultTokens()));
        tokens.addAll(Arrays.asList(nestedIfDepthCheck.getDefaultTokens()));
        
        return ArrayUtils.to;*/
        
        return nestedIfDepthCheck.getDefaultTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return nestedIfDepthCheck.getAcceptableTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return nestedIfDepthCheck.getRequiredTokens();
    }
    
    @Override
    public void visitToken(DetailAST ast) {
        //cyclomaticComplexityCheck(ast);
        //methodLengthCheck(ast);
        nestedDepthCheck(ast);
        //accessedVariablesCheck(ast);
    } 
    
    private void cyclomaticComplexityCheck(DetailAST ast)  {
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
    
    private void accessedVariablesCheck(DetailAST ast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void setMaxLinesOfCodes(int maxLinesOfCodes) {
        this.maxLinesOfCode = maxLinesOfCodes;
    }

    public void setMaxCyclomaticComlexity(int maxCyclomaticComlexity) {
        this.maxCyclomaticComplexity = maxCyclomaticComlexity;
    }

    public void setMaxNestingDepth(int maxNestingDepth) {
        this.maxNestingDepth = maxNestingDepth;
    }

    public void setMaxAccesedVariables(int maxAccesedVariables) {
        this.maxAccessedVariables = maxAccesedVariables;
    }

    
}
