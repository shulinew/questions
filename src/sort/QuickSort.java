package sort;

public class QuickSort {
	public int[] quickSort(int[] data){
		if (data == null || data.length <2){
			return data;
		}
		int pivot = data[data.length/2];
		
		int count = 0;
		for (int i = 0 ;i < data.length;i++){
			if (data[i] < pivot){
				++count;
			}
		}
		int [] left = new int[count];
		int [] right = new int[data.length - count];
		
		int lIndex = 0, rIndex = 0;
		for (int i = 0 ;i < data.length; i++){
			if (i == data.length/2) continue;
			if (data[i] < pivot){
				left[lIndex++] = data[i];
			} else {
				right[rIndex++] = data[i];
			}
		}
		left = quickSort(left);
		right = quickSort(right);
		System.arraycopy(left, 0, data, 0, left.length);
		data[left.length] = pivot;
		System.arraycopy(right, 0, data, left.length+1, right.length);
		return data;
	}

}
