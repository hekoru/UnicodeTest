import java.io.UnsupportedEncodingException;

/**
 * Transform a string containing one character to its unicode, decimal or hexadecimal representation.
 * It supports multi byte characters
 */
public class CharacterRepresentation {


    public static String asUnicode(String character) {
        checkStringOnlyContainsOneCharacter(character);

        char[] chars = character.toCharArray();
        if(chars.length > 1) {
            int codePoint = Character.toCodePoint(chars[0], chars[1]);
            return String.format("U+%X", codePoint);
        } else {
            return String.format("U+%04X", (int) chars[0]);
        }
    }

    public static String asDecimal(String character) {
        checkStringOnlyContainsOneCharacter(character);
        byte[] charBytes = asUtf8Bytes(character);
        return byteArrayToString("%d", charBytes);
    }

    public static String asHexadecimal(String character) {
        checkStringOnlyContainsOneCharacter(character);
        byte[] charBytes = asUtf8Bytes(character);
        return byteArrayToString("%02x", charBytes);
    }

    private static void checkStringOnlyContainsOneCharacter(String character) {
        if(character.length() > 1) {
            if(character.codePointCount(0, character.length() - 1) >= 1) {

                if(!isMultiByteCharacter(character)) {
                    throw new IllegalArgumentException("Bad multi byte character");
                }
            } else {
                throw new IllegalArgumentException("Only one character strings allowed");
            }
        }
    }

    private static boolean isMultiByteCharacter(String character) {
        char[] chars = character.toCharArray();
        return chars.length == 2 && Character.isHighSurrogate(chars[0]) && Character.isLowSurrogate(chars[1]);
    }

    private static String byteArrayToString(String format, byte[] byteArray) {
        StringBuilder builder = new StringBuilder();
        for(byte value: byteArray) {
            builder.append(String.format(format, value & 0xFF));
            builder.append(" ");
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static byte[] asUtf8Bytes(String character) {
        try {
            return character.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("The impossible has happened");
        }
    }
}
