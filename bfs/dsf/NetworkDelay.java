/*
 There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time 
it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:

    N will be in the range [1, 100].
    K will be in the range [1, N].
    The length of times will be in the range [1, 6000].
    All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

*/

The idea is to find the time to reach every other node from given node K
Then, to check if all nodes can be reached and if it can be reached then return the time taken to reach the farthest node (node which take longest to get the signal).
As the signal traverses concurrently to all nodes, we have to find the maximum time it takes to reach a node among all nodes from given node K.
If any single node takes Integer.MAX_Value, then return -1, as not all nodes can be reached

add the index of all the nodes which can be reached from a node to a list and store this list in a hashmap

then its similar to dijkstra’s shortest path algorithm except that I am not using Priority Queue to get the minimum distance node. See comments.

class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int r = times.length, max = Integer.MAX_VALUE;
        
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<r;i++){
            int[] nums = times[i];
            int u = nums[0];
            int v = nums[1];
            List<Integer> list = map.getOrDefault(u,new ArrayList<>());
            
            list.add(i);
            
            map.put(u,list);
        }
        if(map.get(K) == null){
            return -1;// no immediate neighbor of node K, so return -1
        }
        int[] dist = new int[N+1];//dist[i] is the time taken to reach node i from node k
        Arrays.fill(dist,max);
        
        dist[K] = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(K);
        
        while(!queue.isEmpty()){
            int u = queue.poll();
            int t = dist[u];
            List<Integer> list = map.get(u);// get the indices of all the neighbors of node u
            if(list == null)
                continue;
            
            for(int n:list){
                int v = times[n][1];
                int time = times[n][2];// time taken to reach from u to v
                 if(dist[v] > t + time){// if time taken to reach v from k is greater than time taken to reach from k to u + time taken to reach from u to v, then update dist[v]
                    dist[v] = t + time;
                    queue.add(v);// as we have found shorter distance to node v, explore all neighbors of v
                }                
            }
        }
        
        int res = -1;
        
        for(int i=1;i<=N;i++){
            int d = dist[i];
            if(d == max){// if d is max, it means node i can not be reached from K, so return -1
                return -1;
            }
            res = d > res ? d : res;
        }
        
        return res;
    }
}


public class NetworkDelay {
    public int networkDelayTime(int[][] times, int N, int K) {
        if(times == null || times.length == 0){
            return -1;
        }
        // store the source node as key. The value is another map of the neighbor nodes and distance.
        Map<Integer, Map<Integer, Integer>> path = new HashMap<>();
        for(int[] time : times){
            Map<Integer, Integer> sourceMap = path.get(time[0]);
            if(sourceMap == null){
                sourceMap = new HashMap<>();
                path.put(time[0], sourceMap);
            }
            Integer dis = sourceMap.get(time[1]);
            if(dis == null || dis > time[2]){
                sourceMap.put(time[1], time[2]);
            }
            
        }

        //Use PriorityQueue to get the node with shortest absolute distance 
        //and calculate the absolute distance of its neighbor nodes.
        Map<Integer, Integer> distanceMap = new HashMap<>();
        distanceMap.put(K, 0);
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {return i1[1] - i2[1];});
        pq.offer(new int[]{K, 0});
        int max = -1;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int distance = cur[1];

            // Ignore processed nodes
            if(distanceMap.containsKey(node) && distanceMap.get(node) < distance){
                continue;
            }
            
            Map<Integer, Integer> sourceMap = path.get(node);
            if(sourceMap == null){
                continue;
            }
            for(Map.Entry<Integer, Integer> entry : sourceMap.entrySet()){
                int absoluteDistence = distance + entry.getValue();
                int targetNode = entry.getKey();
                if(distanceMap.containsKey(targetNode) && distanceMap.get(targetNode) <= absoluteDistence){
                    continue;
                }
                distanceMap.put(targetNode, absoluteDistence);
                pq.offer(new int[]{targetNode, absoluteDistence});
            }
        }
        // get the largest absolute distance.
        for(int val : distanceMap.values()){
            if(val > max){
                max = val;
            }
        }
        return distanceMap.size() == N ? max : -1;
}
}