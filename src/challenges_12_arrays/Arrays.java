/*
Write a method that would sort an array in a descending order. 
Write a method that would remove all duplicates from an array.
Write a method that would find common elements between two arrays.
Write a method that would find the two elements in an array of positive and negative numbers such that their sum is closest to zero.
Write a method that would find the longest consecutive elements sequence from an unsorted array of integers. For example, if the sample array is [49, 1, 3, 200, 2, 4, 70, 5], then the longest consecutive elements sequence is [1, 2, 3, 4, 5]. 
All methods should take in an array and return an array.
*/

package challenges_12_arrays;

public class Arrays {
	// Sort an array in descending order and return a new array.
	// If input is null or empty, return an empty array.
	public static int[] sortDescending(int[] arr) {
		if (arr == null || arr.length == 0) return new int[0];
		// Copy input to avoid mutating caller array
		int[] copy = java.util.Arrays.copyOf(arr, arr.length);
		// Use built-in sort then reverse
		java.util.Arrays.sort(copy);
		// Reverse to get descending order
		for (int i = 0, j = copy.length - 1; i < j; i++, j--) {
			int tmp = copy[i];
			copy[i] = copy[j];
			copy[j] = tmp;
		}
		return copy;
	}

	// Remove duplicates from an array and return a new array with unique elements.
	// We preserve the first occurrence order from the input.
	public static int[] removeDuplicates(int[] arr) {
		if (arr == null || arr.length == 0) return new int[0];
		java.util.LinkedHashSet<Integer> set = new java.util.LinkedHashSet<>();
		// Add elements to LinkedHashSet to preserve insertion order and remove duplicates
		for (int v : arr) {
			set.add(v);
		}
		// Convert to int[]
		int[] res = new int[set.size()];
		int i = 0;
		for (Integer v : set) {
			res[i++] = v;
		}
		return res;
	}

	// Find common elements between two arrays and return them as an array.
	// We return unique common elements in the order they first appear in the first array.
	public static int[] commonElements(int[] a, int[] b) {
		if (a == null || b == null || a.length == 0 || b.length == 0) return new int[0];
		java.util.HashSet<Integer> setB = new java.util.HashSet<>();
		for (int v : b) setB.add(v);
		java.util.LinkedHashSet<Integer> commons = new java.util.LinkedHashSet<>();
		// Keep order from array a
		for (int v : a) {
			if (setB.contains(v)) commons.add(v);
		}
		int[] res = new int[commons.size()];
		int i = 0;
		for (Integer v : commons) res[i++] = v;
		return res;
	}

	// Find two elements whose sum is closest to zero and return them as an array of size 2.
	// If array has fewer than 2 elements, return an empty array.
	public static int[] twoElementsSumClosestToZero(int[] arr) {
		if (arr == null || arr.length < 2) return new int[0];
		// Copy and sort to use two-pointer technique
		int[] copy = java.util.Arrays.copyOf(arr, arr.length);
		java.util.Arrays.sort(copy);
		int left = 0, right = copy.length - 1;
		int bestL = left, bestR = right;
		int bestAbs = Math.abs(copy[left] + copy[right]);
		while (left < right) {
			int sum = copy[left] + copy[right];
			int absSum = Math.abs(sum);
			if (absSum < bestAbs) {
				bestAbs = absSum;
				bestL = left;
				bestR = right;
			}
			// Move pointers to try to get sum closer to zero
			if (sum > 0) {
				right--;
			} else if (sum < 0) {
				left++;
			} else {
				// exact zero is best possible
				break;
			}
		}
		return new int[] { copy[bestL], copy[bestR] };
	}

	// Find the longest consecutive elements sequence from an unsorted array.
	// Example: [49,1,3,200,2,4,70,5] -> [1,2,3,4,5]
	// Returns the consecutive sequence as an array in ascending order.
	public static int[] longestConsecutiveSequence(int[] arr) {
		if (arr == null || arr.length == 0) return new int[0];
		java.util.HashSet<Integer> set = new java.util.HashSet<>();
		for (int v : arr) set.add(v);
		int bestStart = 0;
		int bestLen = 0;
		// For each number, attempt to build sequence only if it's a sequence start
		for (int num : set) {
			// Sequence start if previous number not present
			if (!set.contains(num - 1)) {
				int current = num;
				int length = 1;
				// Count consecutive numbers
				while (set.contains(current + 1)) {
					current++;
					length++;
				}
				if (length > bestLen) {
					bestLen = length;
					bestStart = num;
				}
			}
		}
		// Build result array from bestStart to bestStart + bestLen - 1
		int[] res = new int[bestLen];
		for (int i = 0; i < bestLen; i++) res[i] = bestStart + i;
		return res;
	}

	// Demo main to quickly test the implemented methods.
	public static void main(String[] args) {
		int[] a = {49, 1, 3, 200, 2, 4, 70, 5};
		System.out.println("sortDescending: " + java.util.Arrays.toString(sortDescending(a)));
		System.out.println("removeDuplicates: " + java.util.Arrays.toString(removeDuplicates(new int[] {1,2,2,3,1,4}))); 
		System.out.println("commonElements: " + java.util.Arrays.toString(commonElements(new int[] {1,2,3,4}, new int[] {3,4,5,6}))); 
		System.out.println("twoElementsSumClosestToZero: " + java.util.Arrays.toString(twoElementsSumClosestToZero(new int[] {-8, 4, 5, -2, 1}))); 
		System.out.println("longestConsecutiveSequence: " + java.util.Arrays.toString(longestConsecutiveSequence(a)));
	}
}

// ===== output =====
// sortDescending: [200, 70, 49, 5, 4, 3, 2, 1]
// removeDuplicates: [1, 2, 3, 4]
// commonElements: [3, 4]
// twoElementsSumClosestToZero: [-2, 1]
// longestConsecutiveSequence: [1, 2, 3, 4, 5]
