import java.util.HashMap;

public class Main {

    public static String bitsAsString(long value) {
        StringBuilder binStr = new StringBuilder();
        for(int i = 0; i < 64; i++) {
            if((value & 1) == 1) {
                binStr.append("1");
            }
            else {
                binStr.append("0");
            }
            if((i+1) % 4 == 0) {
                binStr.append(" ");
            }
            value >>= 1;
        }
        //binStr = binStr.reverse();
        return binStr.toString();
    }

    public static boolean isBitSet(long value, int pos) {
        return ((value >> pos - 1) & 1) == 1;
    }

    public static long setBit(long value, int pos) {
        // value = 0010 0000
        //         0000 0001
        // value = 0000 0010
        pos--;
        return value | (1L << pos);

    }

    public static int bitsNeeded(String input, HashMap<Character, String> charMap) {
        int bitsNeeded = 0;
        for(char c : input.toCharArray()) {
            bitsNeeded += charMap.get(c).length();
        }
        return bitsNeeded;
    }

    public static int bytesNeeded(String input, HashMap<Character, String> charMap) {

        return (int)bitsNeeded(input, charMap) / 8 + 1;
    }

    public static void main(String[] args) {
            long code = 0;
            int pos = 1;
        String input = "alla gillar glass";
        HashMap<Character, String> charMap = new HashMap<>();
        charMap.put('a', "00");
        charMap.put('g', "010");
        charMap.put(' ', "011");
        charMap.put('s', "100");
        charMap.put('r', "1010");
        charMap.put('i', "1011");
        charMap.put('l', "11");
        System.out.println(bytesNeeded(input, charMap));
        System.out.println(bitsNeeded(input, charMap));
        //byte[] codedBytes = new byte[bytesNeeded(input, charMap)];
        for(char c : input.toCharArray()) {
            String pattern = charMap.get(c);
            for(char p : pattern.toCharArray()) {
                if(p == '1') {
                    code = setBit(code, pos);
                }
                pos++;
            }
        }
        System.out.println(bitsAsString(code));
    }
}
