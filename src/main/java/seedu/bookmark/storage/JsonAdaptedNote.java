package seedu.bookmark.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.book.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    private final String title;
    private final String text;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given {@code title} and {@code text}.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title, @JsonProperty("text") String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.title;
        text = source.text;
    }
    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the note.
     */
    public Note toModelType() throws IllegalValueException {
        if (!Note.isValidNote(title, text)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(title, text);
    }

}
