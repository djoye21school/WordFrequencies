import edu.duke.*;
import java.io.*;
import java.util.*;

/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    Map<String, ArrayList> map = new HashMap<String, ArrayList>();
    private void addWordsFromFile(File f){
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            ///System.out.println(map.get(word) + " " + fileName);
            if (map.containsKey(word)){
                ArrayList<String> list = map.get(word);
                if(!list.contains(fileName)){
                    list.add(fileName);
                }
            }
            else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(fileName);
                map.put(word, list);
            }
        }
        
    }
    
    private void buildWordFileMap(){
            map.clear();
            DirectoryResource dr = new DirectoryResource();
            for (File f : dr.selectedFiles()){
                addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int max = 0;
        for(ArrayList iter : map.values()){
            int curr = iter.size();
            if (max < curr)
                max = curr;
        }
        return max;
    }
    
    private ArrayList wordsInNumFiles(int number){
        ArrayList<String> word = new ArrayList();
        for(String key : map.keySet()){
            //System.out.println(key + " - key" + " ---- number = " + number);
            if(map.get(key).size() == number)
                word.add(key);
        }
        //System.out.println(word);
        return word;
    }
    
    private void printFileln(String word){
        for(String key : map.keySet()){
            if(key.equals(word)){
                ArrayList<String> list = map.get(key);
                for(String file : list)
                    System.out.println(file);
                break;
            }
        }
    }
   
    public void tester(){
        buildWordFileMap();
        //System.out.println(map);
        //System.out.println(maxNumber());
        System.out.println(wordsInNumFiles(7));
        System.out.println(wordsInNumFiles(7).size());
        System.out.println(wordsInNumFiles(4));
        System.out.println(wordsInNumFiles(4).size());
        printFileln("laid");
    }
}
