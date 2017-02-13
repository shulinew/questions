package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CloneGraph {
	private Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return null;
		if (map.containsKey(node.label)) {
			return map.get(node.label);
		}
		UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
		map.put(cloned.label, cloned);
		for (UndirectedGraphNode neighbor: node.neighbors){
			cloned.neighbors.add(cloneGraph(neighbor));
		}
		return cloned;
	}
	public UndirectedGraphNode cloneGraphIterative(UndirectedGraphNode node) {
		if (node == null) return null;
		Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
		map.put(node.label, cloned);
		
		LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			UndirectedGraphNode nodefromQueue = queue.pop();
			for (UndirectedGraphNode neighbor: nodefromQueue.neighbors) {
				if (!map.containsKey(neighbor.label)) {
					map.put(neighbor.label, neighbor);
					queue.add(neighbor);
				}
				map.get(nodefromQueue.label).neighbors.add(map.get(nodefromQueue.label));
			}
		}
		return cloned;
	}

}
