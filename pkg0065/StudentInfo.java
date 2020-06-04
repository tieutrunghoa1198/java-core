/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0065;

/**
 *
 * @author Tiêu Trung Hòa
 */
public class StudentInfo {
    private String name;
    private String cLass;
    private double math = 10;
    private double chemistry;
    private double physical;
    private double AVG;
    private String type;
    
    public StudentInfo(){
        
    }

    public StudentInfo(String name, String cLass, double math, double chemistry, double physical) {
        this.name = name;
        this.cLass = cLass;
        this.math = math;
        this.chemistry = chemistry;
        this.physical = physical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcLass() {
        return cLass;
    }

    public void setcLass(String cLass) {
        this.cLass = cLass;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    public double getPhysical() {
        return physical;
    }

    public void setPhysical(double physical) {
        this.physical = physical;
    }

    public double getAVG() {
        return AVG;
    }

    public void setAVG(double AVG) {
        this.AVG = AVG;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return ("Name: "+name+"\n"+"Classes: "+cLass+"\n"+"AVG: "+getAVG()+"\n"+"Type: "+getType());
    }
    
    
    
}
