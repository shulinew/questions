public class QuickSelect {
	
	public static int findKthLargest(int [] array, int k){
		int n = array.length;
		int p = select(array, 0, n-1, n-k+1);
		return array[p];
	}
	
	private static int select(int[] array, int low, int high, int k){
		int pivot = array[high];
		int i = low;
		int j = high;
		
		while(i < j){
			if (array[i++] > pivot){
				swap(array, i--, j--);
			}
		}
		swap(array, i, high);
		int m = array.length - i + 1;
		if (m == k)
			return i;
		else if (m > k){
			return select (array, 0, i-1, k);
		}
		else {
			return select(array,i+1, high, k - m);
		}
		
	}
	private static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
