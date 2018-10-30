package queue;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  Implement the following operations of a stack using queues.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    empty() -- Return whether the stack is empty.

 */
//public class StackByQueue {
//	Queue<Integer> queue = new LinkedList<Integer>();
//	public void push(int x){
//		Queue<Integer> temp = new LinkedList<Integer>();
//		temp.add(x);
//		while (!queue.isEmpty()){
//			temp.add(queue.remove());
//		}
//		queue = temp;
//	}
//	public void pop(){
//		if (!queue.isEmpty())
//			queue.remove();
//	}
//	public int peek(){
//		return queue.isEmpty()? 0: (int) queue.peek();
//	}
//	public boolean empty(){
//		return queue.isEmpty();
//	}
//    private Queue<Integer> queue = new LinkedList<Integer>();
//
//    public void push(int x) {
//        queue.add(x);
//        for (int i=1; i<queue.size(); i++)
//            queue.add(queue.remove());
//    }
//
//    public void pop() {
//        queue.remove();
//    }
//
//    public int top() {
//        return queue.peek();
//    }
//
//    public boolean empty() {
//        return queue.isEmpty();
//    }

//}
public class StackByQueue {
	Queue<Integer> inQueue = new LinkedList<Integer>();
	Queue<Integer> outQueue = new LinkedList<Integer>();
    public void push(int x) {
    	inQueue.add(x);   
    	while (!outQueue.isEmpty()){
    		inQueue.add(outQueue.remove());
    	}
    	while(!inQueue.isEmpty()){
    		outQueue.add(inQueue.remove());
    	}
    }

    // Removes the element from in front of queue.
    public void pop() {
    	if (!outQueue.isEmpty()){
    		outQueue.remove();
    	}
    }

    // Get the front element.
    public int top() {
    	if (!outQueue.isEmpty())
    		return outQueue.peek();
    	else
    		return 0;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return inQueue.isEmpty() && outQueue.isEmpty();
    }

	public static void main(String [] arg){
		StackByQueue stack = new StackByQueue();
		stack.push(1);
//		stack.push(2);
//		stack.push(3);
		System.out.println(stack.top());
//		stack.pop();
//		System.out.println(stack.top());
//		stack.pop();
//		System.out.println(stack.top());
//		System.out.println(stack.empty());
//		stack.pop();
//		System.out.println(stack.empty());
	}

}

