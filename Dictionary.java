package dictionary;

import java.util.HashMap;

class Dictionary {
  
    Dictionary() {
      if (root == null) {
        root = new TrieNode('^');
      }
    }
  
    TrieNode root = null;

    class TrieNode {
      
      char c;
      
      HashMap<String, TrieNode> childNodeMap = null;
      
      boolean isCompleteWord = false;
      
      String meaning = null;
      
      TrieNode(char ch) {
        c = ch;
        childNodeMap = new HashMap<String, Dictionary.TrieNode>();
        isCompleteWord = false;
        meaning = null;
      }
    }
  
    
    public void insert(String word, String meaning) {
      
      //Start from top     
      char[] wordCharArray = word.toCharArray();
      TrieNode currentNode = root;
      TrieNode parentNode = null;
      for (int i = 0; i < wordCharArray.length; i++) {
        
        char currentChar = wordCharArray[i];
        if (currentNode.childNodeMap.get(""+currentChar) == null) {
          //Create new
          TrieNode node = new TrieNode(currentChar);
          //Attach to current
          currentNode.childNodeMap.put(""+currentChar, node);
        }
        parentNode = currentNode;
        currentNode = parentNode.childNodeMap.get(""+currentChar);   
        int g=1;
      }//for
      if (currentNode != null) {
        currentNode.meaning = meaning;
        currentNode.isCompleteWord = true;
      }      
      
      return;
    }
    
    public String lookup (String word) {
      char[] wordCharArray = word.toCharArray();
      TrieNode currentNode = root;
      TrieNode parentNode = null;
      boolean wordMatched = false;
      StringBuffer strBuf = new StringBuffer("");
      for (int i = 0; i < wordCharArray.length; i++) {
        
        char currentChar = wordCharArray[i];
        if (currentNode.childNodeMap.get(""+currentChar) == null) {
           wordMatched = false;
           break;
        }
        parentNode = currentNode;
        currentNode = parentNode.childNodeMap.get(""+currentChar);
        if (currentNode != root) {
        	strBuf.append(currentNode.c);
        }
        if ( (strBuf.toString().equals(word)) && (currentNode.isCompleteWord == true) ) {
        	wordMatched = true;
        }
      }
      if ((wordMatched) && (currentNode != null)) {
          return currentNode.meaning;
      } else {
        return null;
      }
    } 
    
    public String[] lookupStartsWith(String prefix) {
      return null;
    }
    
    public String[] lookupRange (String startWord, String endWord) {
      
      return null;
    }
    
 
  
  
  public static void main(String[] args) {
    
    Dictionary dict = new Dictionary();
    dict.insert("apple", "a fruit");
    dict.insert("app", "short for application on mobile");
    System.out.println("Meaning for apple:" + dict.lookup("apple"));
    System.out.println("Meaning for app:" + dict.lookup("app"));
    System.out.println("Meaning for appl:" + dict.lookup("appl"));
    
  }
}

