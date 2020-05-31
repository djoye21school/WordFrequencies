import edu.duke.*;
import java.util.*;

/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs; 
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()){
            word = word.toLowerCase();
            if(myWords.contains(word)){
                int index = myWords.indexOf(word);
                myFreqs.set(index, (myFreqs.get(index) + 1));
            }
            else{
                myWords.add(word);
                myFreqs.add(1);
            }
        }
    }
    
    private int findIndexOfMax(){
        int max = 0;
        int indexMax = -1;
        for (int i = 0; i < myFreqs.size(); i++){
            if (myFreqs.get(i) > max){
                max = myFreqs.get(i);
                indexMax = i;
            } 
        }
        return indexMax;
    }
    
    public void tester(){
        findUnique();
        //System.out.println("Number of unique words: " + myWords.size());
        //for(int i = 0; i < myWords.size(); i++)
           // System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " 
        + "word - " + myWords.get(index) + " count = " +  myFreqs.get(index));
        System.out.println("Number of unique words: " + myWords.size());
    }
}

