import edu.duke.*;
import java.util.*;

/**
 * Write a description of class GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibMap {
    private Map<String, ArrayList> map;
    private Random myRandom;
    private ArrayList<String> makeStory;
    
    private String sourceURL = "https://www.dukelearntoprogram.com//java/madtemplate.txt";
    private String source = "data";
    
    public GladLibMap(){
        map = new HashMap<String, ArrayList>();
        map.clear();
        initializeFromSource(source);
        myRandom = new Random();
        makeStory = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source){
        String[] categories = {"adjective", "noun", "color", "animal", "timeframe", "name", "country", "verb", "fruit"};
        for(int i = 0; i < categories.length; i++){
            String category = categories[i];
            map.put(category, readIt(source + "/" + category + ".txt"));
        }
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")){
            URLResource url = new URLResource(source);
            for(String word : url.words()){
                list.add(word);
            }
        }
        else{
            FileResource file = new FileResource(source);
            for(String word : file.words()){
                list.add(word);
            }
        }
        return list;
    }
    
        private String fromTemplate(String source){
        String result = "";
        if (source.startsWith("http")){
            URLResource url = new URLResource(source);
            for(String word : url.words()){
                result += processWord(word) + " ";
            }
        }
        else{
            FileResource file = new FileResource(source);
            for(String word : file.words()){
                result += processWord(word) + " ";
            }
        }
        return result;
    }
    
    private String processWord(String word){
        
        int ind1 = word.indexOf("<");
        int ind2 = word.indexOf(">");
        String str = "";
        if (ind1 != -1 && ind2 != -1){
            String label = word.substring(ind1 + 1, ind2);
            String substitute = "";
            
            int maxIter = 0;
            for (String s : map.keySet()){
                maxIter += map.get(s).size();
            }
            for (int i = 0; i < maxIter; i++){
                substitute = getSubstitute(label);
                if(!makeStory.contains(substitute))
                     break;
            }
            makeStory.add(substitute);    
            str = word.substring(0, ind1) + substitute;
            if (ind2 < word.length() - 1)
                str += word.substring(ind2 + 1, word.length());
            return str;
        }
        return word;
    }
    
    private String randomFrom(ArrayList<String> source){
        return source.get(myRandom.nextInt(source.size()));
    }
    
    private String getSubstitute(String label){
        if(map.containsKey(label))
            return randomFrom(map.get(label));
        else if(label.equals("number"))
                return "" + ((myRandom.nextInt(50)) + 1);
        else
            return "unknown";
    }
    
    private void printOut(String template, int size){
        for (int i = 0; i < template.length(); i++){
            int ind1 = template.indexOf(" ", i);
            int ind2 = -1;
            if (i + 1 < template.length())
                ind2 = template.indexOf(" ", i + 1);
            if(ind1 + 1 <= size &&  ind2 + 1 >= size){
                System.out.println(template.substring(0, ind1));
                template = template.substring(ind1 + 1);
                i = 0;
            }
            else if(ind2 == template.length() - 1){
                template = template.substring(0, ind2);
            }
        }
        System.out.println(template);
    }
    
    private int totalWordsInMap(){
        int count = 0;
        for(ArrayList list : map.values())
            count += list.size();
        return count;
    }
    
    private int totalWordsConsidered(){
        int count = 0;
        ArrayList<String> category = new ArrayList<String>();
        for(Map.Entry<String, ArrayList> pair : map.entrySet()){
            String key = pair.getKey();
            System.out.println(key);
            
            for(String used : makeStory){
                System.out.println(pair.getValue() + "----used = " + used + " ----- " + pair.getValue().contains(used));
                if(pair.getValue().contains(used) && !category.contains(key)){
                    category.add(key);
                    count += map.get(key).size();
                    break;
                }
            }
            
        }
        return count;
    }
    
    public void makeStory(){
        String template = fromTemplate(source + "/madtemplate2.txt");
        printOut(template, 60);
        System.out.println("words used in text - " + makeStory.size());
        System.out.println("words total count - " + totalWordsInMap());
        System.out.println("words considered count - " + totalWordsConsidered());
    }
    
    public void main(String[] args){
        GladLib lib = new GladLib();
        makeStory();
    }
}
