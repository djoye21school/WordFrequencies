import edu.duke.*;
import java.util.*;

/**
 * Write a description of class ChangeWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeframeList;
    private ArrayList<String> nameList;
    private ArrayList<String> countryList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private Random myRandom;
    private ArrayList<String> makeStory;
    
    private String sourceURL = "https://www.dukelearntoprogram.com//java/madtemplate.txt";
    private String source = "data";
    
    public GladLib(){
        initializeFromSource(source);
        myRandom = new Random();
        makeStory = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source){
        adjectiveList = readIt(source + "/adjective.txt");
        nounList = readIt(source + "/noun.txt");
        colorList = readIt(source + "/color.txt");
        animalList = readIt(source + "/animal.txt");
        timeframeList = readIt(source + "/timeframe.txt");
        nameList = readIt(source + "/name.txt");
        countryList = readIt(source + "/country.txt");
        verbList = readIt(source + "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
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
            int maxIter = adjectiveList.size() + nounList.size() + colorList.size() + animalList.size() + timeframeList.size() + 
            nameList.size() + countryList.size() + verbList.size() + fruitList.size();
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
        if(label.equals("adjective"))
                return randomFrom(adjectiveList);
        else if (label.equals("color"))
                return randomFrom(colorList);
        else if (label.equals("animal"))
                return randomFrom(animalList);
        else if (label.equals("timeframe"))
                return randomFrom(timeframeList);
        else if (label.equals("name"))
                return randomFrom(nameList);
        else if (label.equals("country"))
                return randomFrom(countryList);
        else if (label.equals("verb"))
                return randomFrom(verbList);
        else if (label.equals("fruit"))
                return randomFrom(fruitList);
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
    public void makeStory(){
        String template = fromTemplate(source + "/madtemplate2.txt");
        printOut(template, 60);
        System.out.println("words in text - " + makeStory.size());
    }
    public void main(String[] args){
        GladLib lib = new GladLib();
        makeStory();
    }
}