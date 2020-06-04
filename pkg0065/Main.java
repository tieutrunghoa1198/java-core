/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg0065;

import java.util.*;

/**
 *
 * @author Tiêu Trung Hòa
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<StudentInfo> stdIn4 = new ArrayList<>();
    private Map<String, Integer> getPercent = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("======== Manage student program ========");
        Main main = new Main();
        main.addStudent();
        main.calc();
        main.addIntoHashMap();
        main.display();
        main.finalOutput();
    }

    //them hoc sinh
    public void addStudent() {
        boolean flag = false;
        Main main = new Main();
        do {
            System.out.print("Name: ");
            String name = main.correctString();
            System.out.print("Class: ");
            String cLass = main.correctString();
            System.out.print("Math mark: ");
            double math = main.checkInput(0,10);
            System.out.print("Chemistry mark: ");
            double chemistry = main.checkInput(0,10);
            System.out.print("Physical mark: ");
            double physical = main.checkInput(0,10);
            stdIn4.add(new StudentInfo(name, cLass, math, chemistry, physical));
            flag = main.askUser();
        } while (flag != false);
    }

    // tinh diem 
    public void calc() {
        double total = 0;
        for (int i = 0; i < stdIn4.size(); i++) {
            total = (stdIn4.get(i).getMath() + stdIn4.get(i).getChemistry() + stdIn4.get(i).getPhysical()) / 3;

            if (total > 7.5) {
                stdIn4.get(i).setAVG(total);
                stdIn4.get(i).setType("a");
            } else if (6 <= total && total <= 7.5) {
                stdIn4.get(i).setAVG(total);
                stdIn4.get(i).setType("b");
            } else if (4 <= total && total < 6) {
                stdIn4.get(i).setAVG(total);
                stdIn4.get(i).setType("c");
            } else if (total < 4) {
                stdIn4.get(i).setAVG(total);
                stdIn4.get(i).setType("d");
            }
        }
    }

    //them cac loai diem vao hashmap
    public void addIntoHashMap() {
        getPercent.put("a", 0);
        getPercent.put("b", 0);
        getPercent.put("c", 0);
        getPercent.put("d", 0);
        for (int i = 0; i < stdIn4.size(); i++) {
            if (getPercent.containsKey(stdIn4.get(i).getType())) {
                getPercent.put(stdIn4.get(i).getType(), (getPercent.get(stdIn4.get(i).getType()) + 1) / stdIn4.size());
            }

        }
    }

    // hien thi output
    public void display() {
        for (int i = 0; i < stdIn4.size(); i++) {
            System.out.println("-------- Student Info --------");
            System.out.println(stdIn4.get(i).toString());
        }
    }

    //tinh phan tram diem tren tong so 
    public void finalOutput() {
        System.out.println("========== Clasification info ==========");
        //entry set la gi?
        Set set = getPercent.entrySet();
        //iterator la gi 
        Iterator i = set.iterator();
        while (i.hasNext()) {
            HashMap.Entry e = (HashMap.Entry) i.next();
            System.out.print(e.getKey() + " :");
            System.out.println(100 * (int) e.getValue() + "%");
        }
    }

    //hoi nguoi dung 
    public boolean askUser() {
        System.out.println("Do you want to enter more? Y/N");
        while (true) {
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("Y")) {
                return true;
            } else if (option.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.print("Retype: ");
                continue;
            }
        }
    }

    // kiem tra so 
    public double checkInput(double min,double max) {
        double n;
        
        while(true){
            try {
                n = Integer.parseInt(sc.nextLine());
                if(n<min){
                    System.out.println("Range["+min+"-"+max+"]");
                    System.out.println("Mark must be greater than "+min);
                    System.out.print("Retype: ");
                    continue;
                }else if(n>max ){
                    System.out.println("Range["+min+"-"+max+"]");
                    System.out.println("Mark must be less than "+max);
                    System.out.print("Retype: ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("must be a number");
                System.out.print("Retype: ");
            }
        }
       return n;
    }

    //kiem tra string 
    public String correctString(){
        String str=null;
        String regex = "(^[a-zA-Z\\s]+)(\\d+){0,}";
        while(true){
            try {
                str= sc.nextLine();
                if(str.matches(regex)){
                    break;
                }else System.out.print("Enter new string: ");
            } catch (Exception e) {
                System.out.print("Enter new string: ");
            }
        }
        return str;
    }

    
    
}
