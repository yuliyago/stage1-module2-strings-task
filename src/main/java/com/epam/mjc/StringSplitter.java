package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
       List<String> substrings = new ArrayList<>();
       int startPos=0, endPos;
       while(startPos<source.length()){
           endPos=source.length();
           for (String s:delimiters) {
               int index = source.indexOf(s,startPos);
               if(index!=-1 && index<endPos){
                   endPos=index;
               }
           }
           if(endPos!=source.length()){
               if(!source.substring(startPos,endPos).equals("")){
                   substrings.add(source.substring(startPos,endPos));
               }
               startPos=endPos+1;
           }
           else{
               if(!source.substring(startPos).equals("")){
                   substrings.add(source.substring(startPos));
               }
               break;
           }
       }
       return substrings;
    }
}
