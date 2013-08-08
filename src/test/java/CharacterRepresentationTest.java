import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test data obtained from http://www.utf8-chartable.de
 */
public class CharacterRepresentationTest {

    @Test
    public void testUnicodeRepresentation() {
        assertEquals(CharacterRepresentation.asUnicode("a"),"U+0061");
        assertEquals(CharacterRepresentation.asUnicode("A"),"U+0041");
        assertEquals(CharacterRepresentation.asUnicode("á"),"U+00E1");
        assertEquals(CharacterRepresentation.asUnicode("0"),"U+0030");
        assertEquals(CharacterRepresentation.asUnicode("$"),"U+0024");
        assertEquals(CharacterRepresentation.asUnicode("€"),"U+20AC");
        assertEquals(CharacterRepresentation.asUnicode("£"),"U+00A3");
    }

    @Test
    public void testUnicodeRepresentationSurrogates() {
        //Now try a big unicode character
        assertEquals(CharacterRepresentation.asUnicode("\uD840\uDC00"),"U+20000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringsWithMoreThanOneCharacterFail() {
        CharacterRepresentation.asUnicode("Some string");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDodgyString() {
        CharacterRepresentation.asUnicode("\uD840\u0020");
    }

    @Test
    public void testDecimalRepresentation() {
        assertEquals(CharacterRepresentation.asDecimal("a"),"97");
        assertEquals(CharacterRepresentation.asDecimal("A"),"65");
        assertEquals(CharacterRepresentation.asDecimal("á"),"195 161");
        assertEquals(CharacterRepresentation.asDecimal("0"),"48");
        assertEquals(CharacterRepresentation.asDecimal("$"),"36");
        assertEquals(CharacterRepresentation.asDecimal("€"),"226 130 172");
        assertEquals(CharacterRepresentation.asDecimal("£"),"194 163");
    }

    @Test
    public void testHexadecimalRepresentation() {
        assertEquals(CharacterRepresentation.asHexadecimal("a"),"61");
        assertEquals(CharacterRepresentation.asHexadecimal("A"),"41");
        assertEquals(CharacterRepresentation.asHexadecimal("á"),"c3 a1");
        assertEquals(CharacterRepresentation.asHexadecimal("0"),"30");
        assertEquals(CharacterRepresentation.asHexadecimal("$"),"24");
        assertEquals(CharacterRepresentation.asHexadecimal("€"),"e2 82 ac");
        assertEquals(CharacterRepresentation.asHexadecimal("£"),"c2 a3");
    }
}
