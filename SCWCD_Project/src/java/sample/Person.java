/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author Rakesh
 */
public class Person {

    private String name;
    
    private int empID;
    
    private Dog dog;
    
    public Dog getDog() {
        return dog;
    }
    
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
