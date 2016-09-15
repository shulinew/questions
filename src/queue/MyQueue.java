package queue;

import java.util.Stack;

class MyQueue {
//	Stack<Integer> stack = new Stack<Integer>();
//    // Push element x to the back of queue.
//    public void push(int x) {
//    	Stack<Integer> temp = new Stack<Integer>();
//    	temp.push(x);
//    	temp.addAll(stack);
//    	stack = temp;
//    }
//
//    // Removes the element from in front of queue.
//    public void pop() {
//        stack.pop();
//    }
//
//    // Get the front element.
//    public int peek() {
//        return stack.peek();
//    }
//
//    // Return whether the queue is empty.
//    public boolean empty() {
//        return stack.isEmpty();
//    }
	
	Stack<Integer> in = new Stack<Integer>();
	Stack<Integer> out = new Stack<Integer>();
	public void push(int x){
		in.push(x);
	}
	public void pop(){
		if (!out.isEmpty()){
			out.pop();
		} else{
			while(!in.isEmpty()){
				out.push(in.pop());
			}
			out.pop();
		}
	}
	public int peek(){
		if (!out.isEmpty()){
			return out.peek();
		} else{
			while(!in.isEmpty()){
				out.push(in.pop());
			}
		}
		return out.peek();
	}
	public boolean empty(){
		return out.isEmpty() && in.isEmpty();
	}
}
