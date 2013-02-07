/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import java.lang.String;
import java.lang.StringBuilder;
import javax.swing.JOptionPane;

/**
 *
 * @author tangrufus@gmail.com
 */
public class BullsAndCows {

    /**
     * @param args the command line arguments
     */
    
    public static void showError() {
        JOptionPane.showMessageDialog(null, "Error, invalid input, please try again!");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String masterLenght;
        String masterWord;
        StringBuilder guessWord;
        int cows;
        int bulls;
        StringBuilder tmpWord;
        StringBuilder tmpGuessWord;
        String result = "";
        int numG = 1;
        
        //Ask masterLenght
        while(true) {
        String s = JOptionPane.showInputDialog("Master, please enter the lenght of the secret numbers [3 to 7]?");
            if (s != null) {
                try {
                    int num = Integer.parseInt(s); 
                    
                    if (num >= 3 && num <= 7) {
                        //OK
                        masterLenght = s;
                        break;
                    } else {
                        showError();
                        continue;
                    }
                } catch (NumberFormatException nFE) {
                    showError();
                }
            }
        }// end of ask masterLenght
        
        //Ask masterWord
        while(true) {
        String s = JOptionPane.showInputDialog("Master, please enter the "+ masterLenght +" digits secret number?");
            if (s != null) {
                try {
                    int num = Integer.parseInt(s); 
                    
                    if (s.length() == Integer.parseInt(masterLenght)) {
                        //OK
                        masterWord = s;
                        break;
                    } else {
                        showError();
                        continue;
                    }
                } catch (NumberFormatException nFE) {
                    showError();
                }
            }
        }// end of ask masterWord
        
        //while guess
        while(true) { 
            bulls = 0;
            cows = 0;
            
            //Ask guessWord
            while(true) {
                String showResult = "";
                if (numG != 1) {
                    showResult = result + "---\n";
                }
                
            String s = JOptionPane.showInputDialog(showResult +"Guesser, please enter the "+ masterLenght +" digits guess?");
                if (s != null) {
                    try {
                        int num = Integer.parseInt(s); 

                        if (s.length() == Integer.parseInt(masterLenght)) {
                            //OK
                            guessWord = new StringBuilder(s);
                            break;
                        } else {
                            showError();
                            continue;
                        }
                    } catch (NumberFormatException nFE) {
                        showError();
                    }
                }
            }// end of ask guessWord
        
            //check bulls
            tmpWord = new StringBuilder(masterWord);
            tmpGuessWord = new StringBuilder(guessWord);
            for (int i=Integer.parseInt(masterLenght)-1; i >= 0; i--) {
                if (tmpWord.charAt(i) == tmpGuessWord.charAt(i)) {
                    bulls++;
                    tmpWord.deleteCharAt(i);
                    tmpGuessWord.deleteCharAt(i);
                }//end of check bulls
            }
            
            //check cows
            boolean checkCow = true;
            while (checkCow && tmpWord.length()>0 && tmpGuessWord.length()>0) {
                boolean noCow = true;
                for (int i=tmpGuessWord.length()-1; i >= 0 && noCow; i--) {
                    for (int j=tmpWord.length()-1; j >= 0 && noCow; j--) {
                        if (tmpWord.charAt(j) == tmpGuessWord.charAt(i)) {
                            //cow!
                            tmpWord.deleteCharAt(j);
                            tmpGuessWord.deleteCharAt(i);
                            cows++;
                            noCow = false;
                            break;
                        } else if (j==0 && i==0) {
                            checkCow = false;
                        }
                    }
                }
            }//end of while cows
            
            //output
             if (bulls == Integer.parseInt(masterLenght)) {
                // End Game!!
                String showResult = result += "Guess #" + numG +": "+ guessWord +" - Reply: "+bulls+" Bulls!";
                JOptionPane.showMessageDialog(null, showResult);
                System.exit(1);
            }
            
            
            if (bulls != Integer.parseInt(masterLenght)) {
                result += "Guess #" + numG +": "+ guessWord +" - Reply: "+bulls+" Bulls, "+cows+" Cows.\n";
                numG++;
            }
           
                
           
            
        }//while guess
        
    }
}
