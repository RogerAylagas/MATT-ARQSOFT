/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.libraries;

import client.functions.Max;
import client.functions.Sum;
import client.functions.Average;
import client.functions.Min;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import client.functions.Function;


/**
 *
 * @author roger
 */
public class BasicLib extends Library{
    HashMap<String,Function> functions;
    
    public BasicLib() {
        this.functions = new HashMap<>();
        functions.put("SUM", new Sum());
        functions.put("MIN", new Min());
        functions.put("MAX", new Max());
        functions.put("AVERAGE", new Average());
    } 

    public Function getFunction(String operation) {
        return this.functions.get(operation);
    }

}
