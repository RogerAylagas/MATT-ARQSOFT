/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.spreadsheet;

import static java.lang.Integer.max;
import java.util.regex.Pattern;

/**
 *
 * @author Roger Aylagas Torres
 */
public class Spreadsheet {
    private final String name;
    private final String folder;
    private Grid grid;
    private DataManager dataManager;
    private Calc calc;

    public Spreadsheet(String name, String folder) {
        this.name = name;
        this.folder = folder;
        this.dataManager = new DataManager();
        this.grid = new Grid();
        this.calc = new Calc();
    }
    
    public Spreadsheet(String name, String folder, int numCells) {
        this.name = name;
        this.folder = folder;
        this.dataManager = new DataManager();
        this.grid = new Grid(numCells);
        this.calc = new Calc();
    }

    public Grid getGrid() {
        return grid;
    }
    
    
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
    }
    
    public void edit(String row, String col, String input) throws InvalidCellException, InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        try{
            int r = Integer.parseInt(row)-1;
            int c = Integer.parseInt(col)-1;
            if(!this.isValidCell(r, c)){
                throw new InvalidCellException("Error: Invalid row or column");
            }  
            if(this.isEquation(input)){
                String result = this.calc.solveEq(input, this.grid);
                this.grid.setValue(r, c, result);
                this.grid = this.calc.recomputeCells(r,c,this.grid);
            }else{
                if (Pattern.matches("[0-9]+", input)){
                    this.grid.setValue(r, c, input);
                    this.grid = this.calc.recomputeCells(r,c,this.grid);
                }else{
                    this.grid.setValueToNull(r, c);
                }
                
            }
            this.grid.setContent(r, c, input);
        }catch(NumberFormatException e){
            throw new InvalidCellException("Error: Invalid row or column");
        } 
    }
    
    private boolean isValidCell(int row, int col) throws InvalidCellException{
        if(row>=0 && col>=0){
            if(row>this.grid.getNumCells()-1 || col>this.grid.getNumCells()-1){
                this.extendSpreadsheet(max(row,col)+1);                            
            }
            return true;
        }else{
            throw new InvalidCellException("Error: Invalid row or column");
        } 
    }

    private boolean isEquation(String input) {
        if(input.charAt(0)=='=') return true;
        else return false;
    }

    private void extendSpreadsheet(int newDim) {
        this.grid.extendSpreadsheet(newDim);
    }

    public void save() throws SavingException {
        this.dataManager.save(name, folder, grid);
    }
    
    public String visualizeValues(){
        String visualization = "";
        for (int r = 0; r < this.grid.getNumCells(); ++r) {
            for (int c = 0; c < this.grid.getNumCells(); ++c) {
                if(c == this.grid.getNumCells()-1)
                    try{
                    visualization = visualization.concat(this.grid.getValue(r, c).concat("\n"));
                    }catch(NullPointerException ex){
                        visualization = visualization.concat(this.grid.getContent(r, c).concat("\n"));
                    }
                else
                    try{
                    visualization = visualization.concat(this.grid.getValue(r, c).concat(";"));
                    }catch(NullPointerException ex){
                        visualization = visualization.concat(this.grid.getContent(r, c).concat(";"));
                    }
            }
        }
        return visualization;
    }

    String visualizeContent() {
        String visualization = "";
        for (int r = 0; r < this.grid.getNumCells(); ++r) {
            for (int c = 0; c < this.grid.getNumCells(); ++c) {
                if(c == this.grid.getNumCells()-1)
                    visualization = visualization.concat(this.grid.getContent(r, c).concat("\n"));
                else
                    visualization = visualization.concat(this.grid.getContent(r, c).concat(";"));
            }
        }
        return visualization;
    }

    int getNumCells() {
        return this.grid.getNumCells();
    }

    void setContent(int r, int c, String content) {
        this.grid.setContent(r, c, content);
    }

    void computeContent() throws InvalidSyntaxException, InvalidCellValueException, InvalidFormulaException, InvalidOperationException {
        this.grid = this.calc.computeContent(this.grid);
    }
    
}
