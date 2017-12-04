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

	public int[] selectionSort(int[] data) {
		int length = data.length;
		for (int i = 0; i < length; i++) {
			int min = i;
			for (int j = i; j < length; j++) {
				if (data[i] > data[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
	}
	
	public int[] insertionSort(int[] data) {
		int length = data.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0 && (data[j] < data[j-1]); j--) {
				int temp = data[j];
				data[j] = data[j-1];
				data[j-1] = temp;
			}
		}
	}

}
