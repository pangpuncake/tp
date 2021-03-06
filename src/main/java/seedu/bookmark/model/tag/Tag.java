package seedu.bookmark.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the library.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final int MAX_TAG_LENGTH = 25;
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric. \n"
            + String.format("Maximum of %d characters allowed.", MAX_TAG_LENGTH);
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]"
            + "{0," + (MAX_TAG_LENGTH - 1) + "}$";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Returns the Tag Name in String
     * @return Tag Name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
