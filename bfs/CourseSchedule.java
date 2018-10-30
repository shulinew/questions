package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.
	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
	which is expressed as a pair: [0,1]
	Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish 
	all courses? 
 */
public class CourseSchedule {
    public boolean canFinishOrg(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];
        
        for (int i=0; i<prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }
        
        int count = 0;
        // Queue is for the nodes there is no incoming edges
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i=0; i<indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;  
    }
    public boolean canFinishT(int numCourses, int[][] prerequisites){
        int[] incomingEdges = new int[numCourses];
        List<List<Integer>> goCourses = new ArrayList<List<Integer>>();
        for(int i=0;i<numCourses;i++){
            goCourses.add(new ArrayList<Integer>());
        }
        for(int[] pair: prerequisites){
            incomingEdges[pair[0]]++;
            goCourses.get(pair[1]).add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<incomingEdges.length;i++){
            if(incomingEdges[i]==0){
                queue.add(i);
            }
        }
        int edgeCnt = prerequisites.length;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int goCrs: goCourses.get(cur)){
                 edgeCnt--;
                 if(--incomingEdges[goCrs]==0)
                    queue.add(goCrs);
            }
        }
        return edgeCnt==0;
    }
    public boolean canFinishDfs1(int numCourses, int[][] prerequisites) {
	    List<List<Integer>> graph = new ArrayList<List<Integer>>();
	    boolean[] visited = new boolean[numCourses];
	    
	    for (int i = 0; i < numCourses; i++) {
	    	graph.add(new ArrayList<Integer>());
	    }
	    for(int i=0; i<prerequisites.length;i++){
	        graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
	    }
	
	    for(int i=0; i<numCourses; i++){
	        if(!dfs(graph,visited,i))
	            return false;
	    }
	    return true;
	}

	private boolean dfs(List<List<Integer>> graph, boolean[] visited, int course){
	    if(visited[course])
	        return false;
	    else
	        visited[course] = true;;
	
	    for(int i=0; i<graph.get(course).size();i++){
	        if(!dfs(graph,visited,(int)graph.get(course).get(i)))
	            return false;
	    }
	    visited[course] = false;
	    return true;
	}


	public boolean canFinishDfs2(int numCourses, int[][] prerequisites) {
	    boolean[] visited = new boolean[numCourses];  
	    List<Integer>[] adj = new List[numCourses];    
	    for(int i = 0; i < numCourses; i++)
	        adj[i] = new ArrayList<Integer>();
	    for(int i = 0; i < prerequisites.length; i++)  
	    {
	        int curCourse = prerequisites[i][0];        
	        int preCourse = prerequisites[i][1];        
	        adj[preCourse].add(curCourse);
	    }
	    for(int i = 0; i < numCourses; i++)
	    {
	        if(!dfs(adj, visited, i))     
	            return false;
	    }
	    return true;
	}
	
	private boolean dfs(List<Integer>[] adj, boolean[] visited, int course){
	    if(visited[course])         // have circle
	        return false;
	    visited[course] = true;
	    for (int i = 0; i < adj[course].size(); i++)
	    {
	        if(!dfs(adj, visited, adj[course].get(i)))
	            return false;
	        adj[course].remove(i);  // delete edge
	    }
	    visited[course] = false;    
	    return true;
	}
	
	public boolean canFinishDfs3(int num, int[][] mat) {
	    Map<Integer, Set<Integer>> dependOn = new HashMap<Integer, Set<Integer>>();
	    for (int[] edge : mat) {
	      int child = edge[0];
	      int parent = edge[1];
	      dependOn.putIfAbsent(child, new HashSet<Integer>());
	      dependOn.get(child).add(parent);
	    }

	    boolean[] visited = new boolean[num];
	    boolean[] onStack = new boolean[num];
	    for (int i = 0; i < num; i++) {
	      if (!visited[i]) { // find one not visited and start from it
	        if (!dfs(dependOn, i, visited, onStack))
	          return false;
		  }
	    }
	    return true;
	  }
	private boolean dfs(Map<Integer, Set<Integer>> dependOn, int current, boolean[] visited, boolean[] onStack) {
	    onStack[current] = true; // mark current on the stack of a post-order traversal
	    for (int parent: dependOn.getOrDefault(current, new HashSet<Integer>())) {
	    	if (onStack[parent]) {// one of the dependency is still not popped from the stack
	    		return false;
	    	}
	    	if (!visited[parent] && !dfs(dependOn, parent, visited, onStack)) {
	    		return false;
	    	}
	    }
	    visited[current] = true; // can also be placed before visiting children, so place is not important
	    onStack[current] = false; // making DFS a post-order traversal: popping current when all its children are done
	    return true;
	  }
	  
	  
	   public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
	        List<List<Integer>> adj = new ArrayList<List<Integer>>();
	        for (int i = 0; i < numCourses; i++){
	        	adj.add(new ArrayList<Integer>());
	        }
	        int[] indegree = new int[numCourses];          
	        Queue<Integer> readyCourses = new LinkedList<Integer>(); 
	        int finishCount = 0;                        
	        for (int i = 0; i < prerequisites.length; i++)  
	        {
	            int curCourse = prerequisites[i][0];        
	            int preCourse = prerequisites[i][1];        
	            adj.get(preCourse).add(curCourse);
	            indegree[curCourse]++;
	        }
	        for (int i = 0; i < numCourses; i++) 
	        {
	            if (indegree[i] == 0) 
	                readyCourses.offer(i);           
	        }
	        while (!readyCourses.isEmpty()) 
	        {
	            int course = readyCourses.poll();        // finish
	            finishCount++;
	            for (int nextCourse : adj.get(course)) 
	            {
	                indegree[nextCourse]--;
	                if (indegree[nextCourse] == 0)    
	                    readyCourses.offer(nextCourse);  // ready
	            }
	        }
	        return finishCount == numCourses;
	    }
}

/*
 * public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(numCourses == 0 || prerequisites == null || prerequisites.length == 0) return true; //??
    
    // create the array lists to represent the courses
    List<List<Integer>> courses = new ArrayList<List<Integer>>(numCourses);
    for(int i=0; i<numCourses; i++) {
        courses.add(new ArrayList<Integer>());
    }
    
    // create the dependency graph
    for(int i=0; i<prerequisites.length; i++) {
        courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
    }

    int[] visited = new int[numCourses]; 
    
    // dfs visit each course
    for(int i=0; i<numCourses; i++) {
           if (!dfs(i,courses, visited)) return false; 
    }
    
    return true;
}

private boolean dfs(int course, List<List<Integer>> courses, int[] visited) {
    
    visited[course] = 1; // mark it being visited
    
    List<Integer> eligibleCourses = courses.get(course); // get its children
    
    // dfs its children
    for(int i=0; i<eligibleCourses.size(); i++) {
        int eligibleCourse = eligibleCourses.get(i).intValue();
        
        if(visited[eligibleCourse] == 1) return false; // has been visited while visiting its children - cycle !!!!
        if(visited[eligibleCourse]  == 0) { // not visited
           if (!dfs(eligibleCourse,courses, visited)) return false; 
        }

    }
    
    visited[course] = 2; // mark it done visiting
    return true;
    
}

 public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;
        
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
            
        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }
        
        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else    
            return false;
    }
} */
