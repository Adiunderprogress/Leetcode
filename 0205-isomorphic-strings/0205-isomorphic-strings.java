class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // If the last seen positions don't match, mapping is invalid
            if (mapS[charS] != mapT[charT]) {
                return false;
            }

            // Store position + 1 (using 1-based indexing so default 0 represents unseen)
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }

        return true;
    }
}