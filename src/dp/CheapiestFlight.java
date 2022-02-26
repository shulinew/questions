/*
https://www.geeksforgeeks.org/maximize-array-sum-k-negations-set-2/
https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
*/
public class cheapistFlight {

    class Stop {
        int destination;
        int price;
        public Stop (int d, int p) {
            desination = d;
            price = p;
        }
        public int getPrice(){
            return price;
        }
        public int getDestination() {
            return desination;
        }
    }

    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K){
                int[][] srcToDst = new int[n][n];
        for(int i = 0; i < flights.length; i++)
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2]; 
						
        PriorityQueue<City> minHeap = new PriorityQueue();
        minHeap.offer(new City(src,0,0));
				
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;
				
        while(!minHeap.isEmpty()){
            City curCity = minHeap.poll();
            if(curCity.id == dst) return curCity.costFromSrc;
            if(curCity.stopFromSrc == K + 1) continue;
            int[] nexts = srcToDst[curCity.id];
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }
        
        return cost[dst] == Integer.MAX_VALUE? -1:cost[dst];
    }

      public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    Map<Integer, List<Edge>> map = new HashMap();
    for(int[] f: flights) {
      List<Edge> list = map.getOrDefault(f[0], new ArrayList());
      list.add(new Edge(f[1], f[2]));
      map.put(f[0], list);
    }
    
    return find(map, src, dst, K);
  }
  
  private int find(Map<Integer, List<Edge>> map, int src, int dst, int K) {
    PriorityQueue<Stop> pq = new PriorityQueue<Stop>((a, b) -> a.cost - b.cost);
    pq.offer(new Stop(src, 0, K + 1));
    while(!pq.isEmpty()) {
      Stop cur = pq.poll();
      if(cur.id == dst) {
        return cur.cost;
      }
      if(cur.count > 0) {
        List<Edge> list = map.getOrDefault(cur.id, new ArrayList());
        for(Edge e: list) {
          pq.offer(new Stop(e.to, cur.cost + e.price, cur.count - 1));
        }        
      }
    }
    return -1;
  }
  
  class Stop {
    int id, cost, count;
    Stop(int _id, int _cost, int _count) {
      id = _id;
      cost = _cost;
      count = _count;
    }
  }
  
  class Edge {
    int to, price;
    Edge(int _to, int _price) {
      to = _to;
      price = _price;
    }
  }

    //Priority Queue could be not ordered internally. comparable checking is running time
    // if sorting of objects needs to be based on natural order then use Comparable
    // if you sorting needs to be done on attributes of different objects, then use Comparator
    public int findCheapestPricePQ(int n, int[][] flights, int src, int dst, int K){
       Map<Integer, Map<Integer, Integer>> prices = new HashMap<Integer, Map<Integer, Integer>>();
       for (int i = 0; i < flights.length; i++){
        //   if(!prices.containsKey(flights[i][0])) {
        //       prices.put(flights[i][0], new HashMap<Integer, Integer>());
        //   }
        //   prices.get(flights[i][0]).put(flights[i][1], flights[i][2]);
        Map<Integer, Integer> price = prices.getOrDefault(flight[i][0], new HashMap<Integer, Integer>());
        price.put(flights[i][1], flights[i][2]);
       }
       Queue<int[]> priorityQueue = new PriorityQueue<int[]>((a,b) -> (Integer.compare(a[0], b[0])));
       priorityQueue.add(new int[]{0, src, K+1});
       while (!priorityQueue.isEmpty()) {
           int[] head = priorityQueue.remove();
           int price = head[0];
           int city = head[1];
           int stops = head[2];
           if (city == dst) {
               return price;
           }
           if (stops > 0) {
               Map<Integer, Integer> adjacency = prices.getOrDefault(city, new HashMap<Integer, Integer>());
               for (Integer option: adjacency.keySet()) {
                   priorityQueue.add(new int[]{adjacency.get(option) + price, option,stops-1});
               }
           }
       }
       return -1;
    }
    public int findCheapestPriceDP1(int n, int[][] flights, int src, int dst, int K) {
        int matrix[][] = new int[n][n]; 
        for (int i = 0; i < flights.length; i++){
            matrix[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        // dp[k][i] is price from src to i with k stops
        int dp[][] = new int[K+1][n];
        for (int i = 0; i < n; i++){
            dp[0][i] = matrix[src][i] > 0?matrix[src][i]:Integer.MAX_VALUE;
        }
        int k = 1;
        for (; k <= K; k++){
            boolean changed = false;
            for (int i = 0; i < n; i++){
                dp[k][i] = dp[k-1][i];
                for (int j = 0; j < n; j++){
                    if (i != j){
                        if (dp[k-1][j] != Integer.MAX_VALUE && matrix[j][i] != 0){
                            dp[k][i] = Math.min(dp[k][i], dp[k-1][j] + matrix[j][i]);
                            changed = true;
                        }
                    }
                }
            }
            // if there is no change in k-1th stop, impossible to find kth stop
            if (!changed) break;
        }
        k = Math.min(K, k);
        return dp[k][dst] == Integer.MAX_VALUE ? -1: dp[k][dst];
    }
}