package practice;

public class longestCommonSubstring {
    public static String getSubstring(String str1, String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] substring = new char[Math.min(chars1.length,chars2.length)];
        int i = -1;
        int j = -1;
        int lo = 0, hi = 0;
        boolean tag = false;

        while (++i < chars1.length){
            int sum = 0;

            for (j = 0; j < chars2.length; j++) {
                if (chars1[i] == chars2[j])
                    tag = true;

                while (j < chars2.length && i < chars1.length && chars1[i] == chars2[j] ) {
                    sum++;
                    i++;
                    j++;
                }

                if (tag == true) {
                    if (sum > hi-lo){
                        lo = i - sum;
                        hi = i-1;
                    }
                    tag = false;
                    i = i-sum;
                    sum = 0;
                }
            }
        }

        for (int k = 0; k <= (hi - lo); k++) {
            substring[k] = chars1[lo+k];
        }

        return String.copyValueOf(substring);
    }

    public static void main(String[] args) {
        System.out.println(getSubstring("1232123456789049996653453245662555555555555555555555345326666","098555555555555555555555555557112345672346348906266666666"));
    }
}
