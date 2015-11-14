import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * TODO:
 * print out the names of the actors forming the connection between the target actor 
 * and Kevin Bacon. 
 * Alternatively, write a method that adds edges to an existing graph when a new movie is released, 
 * and efficiently updates only the Bacon numbers that have changed.
 */

public class ActorGraphNode {
	private String name;
	private Set<ActorGraphNode> linkedActors;
	private int baconNumber = -1;
	
	public ActorGraphNode(String name){
		this.name = name;
		linkedActors = new HashSet<ActorGraphNode>();
	}
	public void linkCostar(ActorGraphNode costar){
		linkedActors.add(costar);
		costar.linkCostar(this);
	}
	public int getBaconNumber(){
		return this.baconNumber;
	}
	public void setBaconNumber(){
		Queue<ActorGraphNode> queue = new LinkedList<ActorGraphNode>();
		queue.add(this);
		ActorGraphNode current = null;
		baconNumber = 0;
		while((current = queue.poll()) != null){
			for (ActorGraphNode actor:current.linkedActors){
				if (actor.baconNumber == -1){
					actor.baconNumber = current.baconNumber+1;
					queue.add(actor);
				}
			}
		}
	}
}
