//import java.util.ArrayList;
//import java.util.List;
//
//
//public class MiniStack<E> {
//	
//	private Object[] members = null;
//	private int size = 0;
//	private int MAX_SIZE = 10;
//	private int minimum = 0;
//	private int secondMinimum = 0;
//	
//	public MiniStack(){
//		members = new Object[MAX_SIZE];
//	}
//	public void push(E e){
//		size = members.length;
//		members[size++] = e;
//		if (e < minimum )
//		
//	}
//	public E pop(){
//	    E e = (E) members[--size];
//	    members[size] = null;
//	    return e;
//	}
//	public E peek(){
//		return (E)members[--size];
//	}
//	
//}
//
//public class MinStack {
//    long min;
//    Stack<Long> stack;
//
//    public MinStack(){
//        stack=new Stack<>();
//    }
//
//    public void push(int x) {
//        if (stack.isEmpty()){
//            stack.push(0L);
//            min=x;
//        }else{
//            stack.push(x-min);//Could be negative if min value needs to change
//            if (x<min) min=x;
//        }
//    }
//
//    public void pop() {
//        if (stack.isEmpty()) return;
//
//        long pop=stack.pop();
//
//        if (pop<0)  min=min-pop;//If negative, increase the min value
//
//    }
//
//    public int top() {
//        long top=stack.peek();
//        if (top>0){
//            return (int)(top+min);
//        }else{
//           return (int)(min);
//        }
//    }
//
//    public int getMin() {
//        return (int)min;
//    }
//}
