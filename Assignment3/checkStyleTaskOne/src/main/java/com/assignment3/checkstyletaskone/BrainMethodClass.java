/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment3.checkstyletaskone;

/**
 *
 * @author Matej
 */
public class BrainMethodClass {
    
    private int savings;
    
    public void NestingTooDeep() {
        savings = 2;
        if (2 == savings) {
            if (3 == 3) {
                if (4 == 4) {
                    if (5 == 5) {
                        return;
                    }
                }
            }
        }
    }
}
