package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.
	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
	which is expressed as a pair: [0,1]
	Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish 
	all courses? 
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
	    ArrayList<Integer>[] graph = new ArrayList<Integer>[numCourses];
	    for(int i=0;i<numCourses;i++)
	        graph[i] = new ArrayList<Integer>();
	        
	    boolean[] visited = new boolean[numCourses];
	    for(int i=0; i<prerequisites.length;i++){
	        graph[prerequisites[i][1]].add(prerequisites[i][0]);
	    }
	
	    for(int i=0; i<numCourses; i++){
	        if(!dfs(graph,visited,i))
	            return false;
	    }
	    return true;
	}

private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
    if(visited[course])
        return false;
    else
        visited[course] = true;;

    for(int i=0; i<graph[course].size();i++){
        if(!dfs(graph,visited,(int)graph[course].get(i)))
            return false;
    }
    visited[course] = false;
    return true;
}
}

public boolean canFinish(int numCourses, int[][] prerequisites) {

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

public boolean canFinish2(int numCourses, int[][] prerequisites) {

    List<Integer>[] adj = new List[numCourses];    
    for(int i = 0; i < numCourses; i++)
        adj[i] = new ArrayList<Integer>();
    int[] indegree = new int[numCourses];          
    Queue<Integer> readyCourses = new LinkedList(); 
    int finishCount = 0;                        
    for (int i = 0; i < prerequisites.length; i++)  
    {
        int curCourse = prerequisites[i][0];        
        int preCourse = prerequisites[i][1];        
        adj[preCourse].add(curCourse);
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
        for (int nextCourse : adj[course]) 
        {
            indegree[nextCourse]--;
            if (indegree[nextCourse] == 0)    
                readyCourses.offer(nextCourse);  // ready
        }
    }
    return finishCount == numCourses;
}
}


}
