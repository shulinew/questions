package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 *  Given two words (beginWord and endWord), and a dictionary's word list, find all shortest 
 *  transformation sequence(s) from beginWord to endWord, such that:
    Only one letter can be changed at a time
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	For example,
	Given:
	beginWord = "hit"
	endWord = "cog"
	wordList = ["hot","dot","dog","lot","log","cog"]
	  [
	    ["hit","hot","dot","dog","cog"],
	    ["hit","hot","lot","log","cog"]
	  ]
 */
public class WordLadder {
	/*
	The solution contains two steps 1 Use BFS to construct a graph. 2. Use DFS to construct the paths
	 from end to start.Both solutions got AC within 1s.
	The first step BFS is quite important. I summarized three tricks
	    Using a MAP to store the min ladder of each word, or use a SET to store the words visited in 
	    current ladder, when the current ladder was completed, delete the visited words from unvisited. 
	    That's why I have two similar solutions.
	    Use Character iteration to find all possible paths. Do not compare one word to all the other 
	    words and check if they only differ by one character.
	    One word is allowed to be inserted into the queue only ONCE. See my comments.
	*/
 
    private Map<String,List<String>> map; // Min ladder of each word
	private List<List<String>> results;
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {   	
        results= new ArrayList<List<String>>();
        if (dict.size() == 0)
			return results;
        
        int min=Integer.MAX_VALUE;
        
        Queue<String> queue= new ArrayDeque<String>();
        queue.add(start);
        
		map = new HashMap<String,List<String>>();
		
		Map<String,Integer> ladder = new HashMap<String,Integer>();
		for (String string:dict)
		    ladder.put(string, Integer.MAX_VALUE);
		ladder.put(start, 0);
				
		dict.add(end); //??
		//BFS: Dijisktra search
		while (!queue.isEmpty()) {
			String word = queue.poll();
			int step = ladder.get(word)+1;//'step' indicates how many steps are needed to travel to one word. 
			if (step>min) break;
			for (int i = 0; i < word.length(); i++){
			   StringBuilder builder = new StringBuilder(word); 
				for (char ch='a';  ch <= 'z'; ch++){
					builder.setCharAt(i,ch);
					String new_word=builder.toString();				
					if (ladder.containsKey(new_word)) {
					    if (step>ladder.get(new_word))//Check if it is the shortest path to one word.
					    	continue;
					    else if (step<ladder.get(new_word)){
					    	queue.add(new_word);
					    	ladder.put(new_word, step);
					    }else;// It is a KEY line. If one word already appeared in one ladder,
					          // Do not insert the same word inside the queue twice. Otherwise it gets TLE.
					    
					    if (map.containsKey(new_word)) //Build adjacent Graph
					    	map.get(new_word).add(word);
					    else{
					    	List<String> list= new LinkedList<String>();
					    	list.add(word);
					    	map.put(new_word,list);
					    	//It is possible to write three lines in one:
					    	//map.put(new_word,new LinkedList<String>(Arrays.asList(new String[]{word})));
					    	//Which one is better?
					    }
					    
					    if (new_word.equals(end))
					    	min=step;

					}//End if dict contains new_word
				}//End:Iteration from 'a' to 'z'
			}//End:Iteration from the first to the last
		}//End While

    	//BackTracking
		LinkedList<String> result = new LinkedList<String>();
		backTrace(end,start,result);

		return results;        
    }
    private void backTrace(String word,String start,List<String> list){
    	if (word.equals(start)){
    		list.add(0,start);
    		results.add(new ArrayList<String>(list));
    		list.remove(0);
    		return;
    	}
    	list.add(0,word);
    	if (map.get(word)!=null)
    		for (String s:map.get(word))
    			backTrace(s,start,list);
    	list.remove(0);
    }

	List<String> list;

	    public List<List<String>> findLadders1(String start, String end, Set<String> dict) {
	        results= new ArrayList<List<String>>();
	        if (dict.size() == 0)
				return results;
	        
	        int curr=1,next=0;	        
	        boolean found=false;	        
	        list = new LinkedList<String>();	       
			map = new HashMap<String,List<String>>();
			
			Queue<String> queue= new ArrayDeque<String>();
			Set<String> unvisited = new HashSet<String>(dict);
			Set<String> visited = new HashSet<String>();
			
			queue.add(start);			
			unvisited.add(end);
			unvisited.remove(start);
			//BFS
			while (!queue.isEmpty()) {
			   
				String word = queue.poll();
				curr--;				
				for (int i = 0; i < word.length(); i++){
				   StringBuilder builder = new StringBuilder(word); 
					for (char ch='a';  ch <= 'z'; ch++){
						builder.setCharAt(i,ch);
						String new_word=builder.toString();	
						if (unvisited.contains(new_word)){
							//Handle queue
							if (visited.add(new_word)){//Key statement,Avoid Duplicate queue insertion
								next++;
								queue.add(new_word);
							}
							
							if (map.containsKey(new_word))//Build Adjacent Graph
								map.get(new_word).add(word);
							else{
								List<String> l= new LinkedList<String>();
								l.add(word);
								map.put(new_word, l);
							}
							
							if (new_word.equals(end)&&!found) found=true;		
														
						}

					}//End:Iteration from 'a' to 'z'
				}//End:Iteration from the first to the last
				if (curr==0){
					if (found) break;
					curr=next;
					next=0;
					unvisited.removeAll(visited);
					visited.clear();
				}
			}//End While

			backTrace(end,start);
			
			return results;        
	    }
	    private void backTrace(String word,String start){
	    	if (word.equals(start)){
	    		list.add(0,start);
	    		results.add(new ArrayList<String>(list));
	    		list.remove(0);
	    		return;
	    	}
	    	list.add(0,word);
	    	if (map.get(word)!=null)
	    		for (String s:map.get(word))
	    			backTrace(s,start);
	    	list.remove(0);
	    }
	    
	    /*
	     * The basic idea is:

1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node to end node, and store node's next level neighbors to HashMap;

2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: compare if the distance of the next level node equals the distance of the current node + 1.
	     */
	    
	}
public List<List<String>> findLadders(String start, String end, List<String> wordList) {
	   HashSet<String> dict = new HashSet<String>(wordList);
	   List<List<String>> res = new ArrayList<List<String>>();         
	   HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
	   HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
	   ArrayList<String> solution = new ArrayList<String>();

	   dict.add(start);          
	   bfs(start, end, dict, nodeNeighbors, distance);                 
	   dfs(start, end, dict, nodeNeighbors, distance, solution, res);   
	   return res;
	}

	// BFS: Trace every node's distance from the start node (level by level).
	private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
	  for (String str : dict)
	      nodeNeighbors.put(str, new ArrayList<String>());

	  Queue<String> queue = new LinkedList<String>();
	  queue.offer(start);
	  distance.put(start, 0);

	  while (!queue.isEmpty()) {
	      int count = queue.size();
	      boolean foundEnd = false;
	      for (int i = 0; i < count; i++) {
	          String cur = queue.poll();
	          int curDistance = distance.get(cur);                
	          ArrayList<String> neighbors = getNeighbors(cur, dict);

	          for (String neighbor : neighbors) {
	              nodeNeighbors.get(cur).add(neighbor);
	              if (!distance.containsKey(neighbor)) {// Check if visited
	                  distance.put(neighbor, curDistance + 1);
	                  if (end.equals(neighbor))// Found the shortest path
	                      foundEnd = true;
	                  else
	                      queue.offer(neighbor);
	                  }
	              }
	          }

	          if (foundEnd)
	              break;
	      }
	  }

	// Find all next level nodes.    
	private ArrayList<String> getNeighbors(String node, Set<String> dict) {
	  ArrayList<String> res = new ArrayList<String>();
	  char chs[] = node.toCharArray();

	  for (char ch ='a'; ch <= 'z'; ch++) {
	      for (int i = 0; i < chs.length; i++) {
	          if (chs[i] == ch) continue;
	          char old_ch = chs[i];
	          chs[i] = ch;
	          if (dict.contains(String.valueOf(chs))) {
	              res.add(String.valueOf(chs));
	          }
	          chs[i] = old_ch;
	      }

	  }
	  return res;
	}

	// DFS: output all paths with the shortest distance.
	private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
	    solution.add(cur);
	    if (end.equals(cur)) {
	       res.add(new ArrayList<String>(solution));
	    } else {
	       for (String next : nodeNeighbors.get(cur)) {            
	            if (distance.get(next) == distance.get(cur) + 1) {
	                 dfs(next, end, dict, nodeNeighbors, distance, solution, res);
	            }
	        }
	    }           
	   solution.remove(solution.size() - 1);
	}
	
	
	// Second solution
	
	List<List<String>> results;
	List<String> list;
	Map<String,List<String>> map;
	    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
	        results= new ArrayList<List<String>>();
	        if (dict.size() == 0)
				return results;
	        
	        int curr=1,next=0;	        
	        boolean found=false;	        
	        list = new LinkedList<String>();	       
			map = new HashMap<String,List<String>>();
			
			Queue<String> queue= new ArrayDeque<String>();
			Set<String> unvisited = new HashSet<String>(dict);
			Set<String> visited = new HashSet<String>();
			
			queue.add(start);			
			unvisited.add(end);
			unvisited.remove(start);
			//BFS
			while (!queue.isEmpty()) {
			   
				String word = queue.poll();
				curr--;				
				for (int i = 0; i < word.length(); i++){
				   StringBuilder builder = new StringBuilder(word); 
					for (char ch='a';  ch <= 'z'; ch++){
						builder.setCharAt(i,ch);
						String new_word=builder.toString();	
						if (unvisited.contains(new_word)){
							//Handle queue
							if (visited.add(new_word)){//Key statement,Avoid Duplicate queue insertion
								next++;
								queue.add(new_word);
							}
							
							if (map.containsKey(new_word))//Build Adjacent Graph
								map.get(new_word).add(word);
							else{
								List<String> l= new LinkedList<String>();
								l.add(word);
								map.put(new_word, l);
							}
							
							if (new_word.equals(end)&&!found) found=true;		
														
						}

					}//End:Iteration from 'a' to 'z'
				}//End:Iteration from the first to the last
				if (curr==0){
					if (found) break;
					curr=next;
					next=0;
					unvisited.removeAll(visited);
					visited.clear();
				}
			}//End While

			backTrace(end,start);
			
			return results;        
	    }
	    private void backTrace(String word,String start){
	    	if (word.equals(start)){
	    		list.add(0,start);
	    		results.add(new ArrayList<String>(list));
	    		list.remove(0);
	    		return;
	    	}
	    	list.add(0,word);
	    	if (map.get(word)!=null)
	    		for (String s:map.get(word))
	    			backTrace(s,start);
	    	list.remove(0);
	    }

}
