import java.util.*;
import edu.duke.*;

/**
 * Write a description of class HashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HashMapClass {
    Map<String, Integer> map;
    public HashMapClass(){
        map = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        for(int i = start; i < dna.length() - 3; i += 3){
            String codon = dna.substring(i, i + 3);
            
            if(map.containsKey(codon))
                map.put(codon, map.get(codon) + 1);
            else
                map.put(codon,1);
        }
        
    }
    
    private String getMostCommonCodon(){
        String check = "";
        int max = -1;
        for (String codon : map.keySet()){
            int curr = map.get(codon);
            if (curr > max){
                max = curr;
                check = codon;
            }
        }
        return check;
    }
    
    private void printCodonCounters(int start, int end){
        for (String codon : map.keySet()){
            int curr = map.get(codon);
            if (curr >= start && curr <= end)
                System.out.println(codon + " - " + curr);
            }
    }
    
    public void tester(){
    FileResource fr = new FileResource();
    String dna = fr.asString().toUpperCase();
    /*
    for (int start = 0; start < 3; start++){ 
        buildCodonMap(start, dna);
        System.out.println(map);
    }*/
    buildCodonMap(0, dna);
    printCodonCounters(7, 7);
    System.out.println(map);
    System.out.println("uniq code = " + map.size());
    String codon = getMostCommonCodon();
    System.out.println("most codon " + codon + " repeat " + map.get(codon));
    }
}
