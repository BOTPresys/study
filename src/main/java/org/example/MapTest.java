package org.example;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class MapTest {
    private String[] words;
    public MapTest(){};
    public MapTest(String[] words){
        setWords(words);
    }
    public String[] getWords() {
        return words;
    }
    public void setWords(String[] words) {
        this.words = words;
    }

    public void CountFrequency(){
        String[] words=getWords();
        HashMap<String,Integer>map=new HashMap<>();
        for(String word:words){
            if(map.get(word)==null){
                map.put(word,1);
            }else{
                map.put(word,map.get(word)+1);
            }
        }
        for(Map.Entry<String,Integer>entry : map.entrySet()){
            System.out.printf("%s,%d\n",entry.getKey(),entry.getValue());
        }
    }

}
