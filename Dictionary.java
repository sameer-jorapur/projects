package dictionary;

import java.util.HashMap;

class Dictionary {
  
    Dictionary() {
      if (root == null) {
        root = new TrieNode('^'); //Root is denoted as ^ node
      }
    }
  
    TrieNode root = null;

    class TrieNode {
      
      char c; //The char  
      HashMap<String, TrieNode> childNodeMap = null; //Child map of all contained letters     
      boolean isCompleteWord = false; //If its a intermediate word..for e.g if you insert apple and app, app is a internmediate word so set isComplete to true     
      String meaning = null; //Stores the meaning
      
      //Constructor
      TrieNode(char ch) {
        c = ch;
        childNodeMap = new HashMap<String, Dictionary.TrieNode>();
        isCompleteWord = false;
        meaning = null;
      }
    }
  
    
    /** insert() - Stores the word in the tree
     * @param word
     * @param meaning
     * 
     * We start with root and navigate each char in the word. If char is not found, we insert it in parents node. At the end we set isComplete = true and 
     * set its meaning.
     */
    public void insert(String word, String meaning) {
      
      //Start from top     
      char[] wordCharArray = word.toCharArray();
      TrieNode currentNode = root;
      TrieNode parentNode = null;
      
      for (int i = 0; i < wordCharArray.length; i++) {
        char currentChar = wordCharArray[i];
        if (currentNode.childNodeMap.get(""+currentChar) == null) {
          //Create new node
          TrieNode node = new TrieNode(currentChar);
          //Attach to current node
          currentNode.childNodeMap.put(""+currentChar, node);
        }
        //Set currentNode as parent
        parentNode = currentNode;
        currentNode = parentNode.childNodeMap.get(""+currentChar);   
        int g=1;
      }//for
      
      if (currentNode != null) {
    	//The currentNode at this point will be pointing to the inserted word. We store its meaning.
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

