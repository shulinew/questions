package math;

public class ComputeArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    	int bottomX = Math.max(A,E);
    	int bottomY = Math.max(B,F);
    	int topX = Math.min(C, G);
    	int topY = Math.min(D, H);
    	int diff = (C-A)*(D-B) + (H-F)*(G-E);
    	int overlap = 0;
    	if (topY > bottomY && topX > bottomX){
    		overlap = (topY - bottomY) * (topX - bottomX);
    	}
    	return diff - overlap;
    }
}
