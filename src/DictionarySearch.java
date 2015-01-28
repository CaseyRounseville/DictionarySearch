import java.io.*;
import java.util.*;

public class DictionarySearch {
  
  /**
   * stores an ordered lists of words for searching
   */
  private static ArrayList<String> words;
  
  /**
   * main program
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    
    words = getWords();
    int interval = 100;
    String[] find = new String[100];    
    int index = interval;
    for (int i = 0; i < interval - 1; i++) {
      index += words.size() / interval;
      find[i] = words.get(index);
    }
    find[interval - 1] = "Xdfsdda";
    
    SearchResult seqResult;
    SearchResult binResult;
    
    System.out.println("#\tBinary\tSequential\tindex\tword");
    
    for (int i = 0; i < find.length; i++) {
      seqResult = sequentialSearch(find[i]);
      binResult = binarySearch(find[i]);
      System.out.println(i + "\t" + binResult.getIterations() + "\t" + seqResult.getIterations() + "\t" + binResult.getIndex() + ":" + seqResult.getIndex() + "\t" + find[i] );
    }
  }
  
  /**
   * Implement a sequential search to find wordToFind in the ArrayList words
   * 
   * @param wordToFind - String to find in words
   * @return a SearchResult (index of item found or -1 if not found, number of iterations in search loop)
   */
  public static SearchResult sequentialSearch(String wordToFind) {
    //TODO
    int count=1;
    for(int i=0;i<words.size();i++,count++){
      if(words.get(i).equals(wordToFind)){
        return new SearchResult(i,count);
      }
    }
    return new SearchResult(-1,count);
  }
  
  /**
   * Implement a binary search to find wordToFind in the ArrayList words
   * 
   * @param wordToFind - String to find in words
   * @return a SearchResult (index of item found or -1 if not found, number of iterations in search loop)
   */
  public static SearchResult binarySearch(String wordToFind) {
    //TODO
    int count=0;
    int min=0;
    int max=words.size()-1;
    while(min<=max){
      count++;
      int mid=(min+max)/2;
      int compare=words.get(mid).compareTo(wordToFind);
      if(compare<0){
        max=mid-1;
      }
      else if(compare>0){
        min=mid+1;
      }
      else{
        return new SearchResult(mid,count);
      }
    }
    return new SearchResult(-1,count);////FIX ME////
  }
  
  /**
   * create an ArrayList<String> and populate it from text file
   * 
   * @return an ArrayList<String>
   * @throws FileNotFoundException
   */
  public static ArrayList<String> getWords() throws FileNotFoundException {
    ArrayList<String> result = new ArrayList<String>();
    Scanner input = new Scanner(new File("words.txt"));
    while(input.hasNextLine()) {
      result.add(input.nextLine());
    }
    input.close();
    return result;
  }
}

