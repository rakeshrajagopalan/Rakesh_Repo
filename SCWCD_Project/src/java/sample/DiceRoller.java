/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

/**
 *
 * @author Rakesh
 */
public class DiceRoller {
    public static int rollDice() {
        return (int) ((Math.random() * 6) + 1);
    }
}
