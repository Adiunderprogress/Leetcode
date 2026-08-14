class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[k];

        // i represents the number of elements picked from nums1
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);

        for (int i = start; i <= end; i++) {
            int[] seq1 = maxSubsequence(nums1, i);
            int[] seq2 = maxSubsequence(nums2, k - i);
            int[] candidate = merge(seq1, seq2, k);

            if (isGreater(candidate, 0, result, 0)) {
                result = candidate;
            }
        }

        return result;
    }

    // Helper: Finds the lexicographically largest subsequence of length len
    private int[] maxSubsequence(int[] nums, int len) {
        int[] stack = new int[len];
        int top = 0;
        int drop = nums.length - len;

        for (int num : nums) {
            while (top > 0 && stack[top - 1] < num && drop > 0) {
                top--;
                drop--;
            }
            if (top < len) {
                stack[top++] = num;
            } else {
                drop--;
            }
        }
        return stack;
    }

    // Helper: Merges two arrays into the lexicographically largest sequence of length k
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] merged = new int[k];
        int i = 0, j = 0, r = 0;

        while (r < k) {
            if (isGreater(nums1, i, nums2, j)) {
                merged[r++] = nums1[i++];
            } else {
                merged[r++] = nums2[j++];
            }
        }
        return merged;
    }

    // Helper: Lexicographical comparison between suffixes of two arrays
    private boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}