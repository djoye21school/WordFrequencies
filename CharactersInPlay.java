import edu.duke.*;
import java.util.*;
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay{
    private ArrayList<String> character;
    private ArrayList<Integer> count;
    CharactersInPlay(){
        character = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    private void update(String person){
        int index = character.indexOf(person);
        if (index == -1){
            character.add(person);
            count.add(1);
        }
        else
            count.set(index, count.get(index) + 1);
    }
    
    private void  findAllCharacters(){
        FileResource fr = new FileResource();
        for (String line : fr.lines()){
            int indexDot = line.indexOf(".");
            if(indexDot != -1){
                update(line.substring(0, indexDot));
            }
        }    
    }
    
    private void charactersWithNumParts(int num1, int num2){
        if (num1 <= num2){
            for(int i = 0; i < count.size(); i++){
                if (count.get(i) >= num1 && count.get(i) <= num2)
                    System.out.println(character.get(i) + " " + count.get(i));
            }
        }
    }
    
    private int findIndexOfMax(){
        int max = 0;
        int indexMax = -1;
        for (int i = 0; i < count.size(); i++){
            if (count.get(i) > max){
                max = count.get(i);
                indexMax = i;
            } 
        }
        return indexMax;
    }
    
    public void tester(){
        findAllCharacters();
        charactersWithNumParts(10, 15);
        int i = findIndexOfMax();
        System.out.println("popular " + character.get(i) + " " + count.get(i));
        //for(int i = 0; i < count.size(); i++)
        //    System.out.println(character.get(i) + " " + count.get(i));
        /*for(int i = 0; i < count.size(); i++)
            if (count.get(i) > 3)
                System.out.println(character.get(i) + " " + count.get(i));*/            
    }
}
