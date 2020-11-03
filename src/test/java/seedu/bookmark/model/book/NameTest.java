package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";

        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void constructor_tooLongName_throwsIllegalArgumentException() {
        StringBuilder tooLong = new StringBuilder();
        // tooLong is one character too long
        tooLong.append("a".repeat(Name.MAX_NAME_LENGTH + 2));

        assertThrows(IllegalArgumentException.class, () -> new Name(tooLong.toString()));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only

        StringBuilder tooLong = new StringBuilder();
        // tooLong is one character too long
        tooLong.append("a".repeat(Name.MAX_NAME_LENGTH + 3));

        assertFalse(Name.isValidName(tooLong.toString())); // too long

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
