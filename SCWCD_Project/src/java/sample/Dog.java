/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

/**
 *
 * @author Rakesh
 */
public class Dog {
    private String name;
    private Toy[] toys;
    public String getName() {
        return name;
    }
    public Toy[] getToys() {
        return toys;
    }
    public void setToys(Toy[] toys) {
        this.toys = toys;
    }
    public void setName(String name) {
        this.name = name;
    }
}
