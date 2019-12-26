/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.libraries;

import client.operations.Max;
import client.operations.Sum;
import client.operations.Average;
import client.operations.Min;
import client.operations.Operation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author roger
 */
public class BasicLib extends Library{
    HashMap<String,Operation> operations;
    
    public BasicLib() {
        this.operations = new HashMap<String, Operation>();
        operations.put("SUM", new Sum());
        operations.put("MIN", new Min());
        operations.put("MAX", new Max());
        operations.put("AVERAGE", new Average());
    } 

    public Operation getOperation(String operation) {
        return this.operations.get(operation);
    }

}
