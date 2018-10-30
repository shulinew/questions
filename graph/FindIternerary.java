package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who
 * departs from JFK. Thus, the itinerary must begin with JFK.

Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary 
    ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.

	Example 1:
	tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
	
	Example 2:
	tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
	Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order. 
	https://discuss.leetcode.com/topic/36370/short-ruby-python-java-c/15
 */
public class FindIternerary {
	/*
	 * Noticed some folks are using Hierholzer's algorithm to find a Eulerian path.
	 * 
	 * First keep going forward until you get stuck. That's a good main path already. Remaining tickets form cycles which are found on the way back and get merged into that main path. By writing down the path backwards when retreating from recursion, merging the cycles into the main path is easy - the end part of the path has already been written, the start part of the path hasn't been written yet, so just write down the cycle now and then keep backwards-writing the path.
	 * From JFK we first visit JFK -> A -> C -> D -> A. There we're stuck, so we write down A as the end of the route and retreat back to D. There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. Then we're stuck again, retreat and write down the airports while doing so: Write down D before the already written A, then JFK before the D, etc. When we're back from our cycle at D, the written route is D -> B -> C -> JFK -> D -> A. Then we retreat further along the original path, prepending C, A and finally JFK to the route, ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.
	 */
	private void buildQueue(Map<String, PriorityQueue<String>> ticketsMap, String[] ticket) {
        if(!ticketsMap.containsKey(ticket[0])) {
        	ticketsMap.put(ticket[0], new PriorityQueue<String>());
        }
        ticketsMap.get(ticket[0]).add(ticket[1]);
	}
	public List<String> findItinerary(String[][] tickets) {
	    for (String[] ticket : tickets) {
	        //targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
	    	buildQueue(targets, ticket);
	    }
	    visit("JFK");
	    return route;
	}

	Map<String, PriorityQueue<String>> targets = new HashMap<String, PriorityQueue<String>>();
	List<String> route = new LinkedList<String>();

	void visit(String airport) {
	    while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
	        visit(targets.get(airport).poll());
	    route.add(0, airport);
	}
	
	public List<String> findItineraryIterative(String[][] tickets) {
	    Map<String, PriorityQueue<String>> targets = new HashMap<String, PriorityQueue<String>>();
	    for (String[] ticket : tickets) {
	        //targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
	    	buildQueue(targets, ticket);
	    }
	    List<String> route = new LinkedList<String>();
	    Stack<String> stack = new Stack<String>();
	    stack.push("JFK");
	    while (!stack.empty()) {
	        while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
	            stack.push(targets.get(stack.peek()).poll());
	        route.add(0, stack.pop());
	    }
	    return route;
	}
	/*
	 * All the airports are vertices and tickets are directed edges. Then all these tickets form a directed graph.
	The graph must be Eulerian since we know that a Eulerian path exists.
	Thus, start from "JFK", we can apply the Hierholzer's algorithm to find a Eulerian path in the graph which is a valid 
	reconstruction. Since the problem asks for lexical order smallest solution, we can put the neighbors in a 
	min-heap. In this way, we always visit the smallest possible neighbor first in our trip.
	 */
	
	Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary2(String[][] tickets) {
        flights = new HashMap<String, PriorityQueue<String>>();
        path = new LinkedList<String>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<String>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
    
    /*
     * Noticed some folks are using Hierholzer's algorithm to find a Eulerian path.
		My solution is similar, considering this passenger has to be physically in one place before move to 
		another airport, we are considering using up all tickets and choose lexicographically smaller solution 
		if in tie as two constraints.	
		Thinking as that passenger, the passenger choose his/her flight greedy as the lexicographical order, 
		once he/she figures out go to an airport without departure with more tickets at hand. the passenger will
		 push current ticket in a stack and look at whether it is possible for him/her to travel to other places 
		 from the airport on his/her way.
     */
    public List<String> findItineraryGreedy(String[][] tickets) {
        List<String> itinerary = new ArrayList<String>();
        if(tickets == null || tickets.length == 0) return itinerary;
        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<String, PriorityQueue<String>>();
        for(int i = 0; i < tickets.length; i++) {
            if(!ticketsMap.containsKey(tickets[i][0])) {
            	ticketsMap.put(tickets[i][0], new PriorityQueue<String>());
            }
            ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
        }

        String current = "JFK";
        Stack<String> drawBack = new Stack<String>();
        for(int i = 0; i < tickets.length; i++) {
            while(!ticketsMap.containsKey(current) || ticketsMap.get(current).isEmpty()) {
                drawBack.push(current);
                current = itinerary.remove(itinerary.size()-1);
            }
            itinerary.add(current);
            current = ticketsMap.get(current).poll();
        }
        itinerary.add(current);
        while(!drawBack.isEmpty()) itinerary.add(drawBack.pop());
        return itinerary;
    }
    
    public List<String> findItineraryDFS(String[][] tickets) {
        ArrayList<String> result = new ArrayList<String>();
        
        if(tickets == null || tickets.length == 0){
            return result;
        }
        
        int total = tickets.length + 1;
        
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for(int i = 0; i < tickets.length; i++){
            if(map.containsKey(tickets[i][0])){
                ArrayList<String> tmp = map.get(tickets[i][0]);
                listAdd(tickets[i][1], tmp);
            }
            else{
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(tickets[i][1]);
                map.put(tickets[i][0], tmp);
            }
        }
        
        result.add("JFK");
        
        itineraryHelper("JFK", map, result, total, 1);
        
        return result;
    }
    
    public boolean itineraryHelper(String current, HashMap<String, ArrayList<String>> map, ArrayList<String> result, int total, int num){
        
        if(num >= total){
            return true;
        }
        
        if(!map.containsKey(current) || map.get(current).size() == 0){
            return false;
        }
        
        ArrayList<String> curList = map.get(current);
        int i = 0;
        
        while(i < curList.size()){
            String next = curList.remove(i);
            result.add(next);
            
            if(itineraryHelper(next, map, result, total, num + 1)){
                return true;
            }
            
            result.remove(result.size() - 1);
            listAdd(next, curList);
            i++;
        }
        
        return false;
    }
    
    
    public void listAdd(String value, ArrayList<String> list){
        if(list.size() == 0){
            list.add(value);
            return;
        }
        else{
            int i = 0;
            while(i < list.size()){
                if(value.compareTo(list.get(i)) <= 0){
                    list.add(i, value);
                    return;
                }
                i++;
            }
            list.add(value);
            return;
        }
    }
    

}
