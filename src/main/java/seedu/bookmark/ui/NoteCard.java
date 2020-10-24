package seedu.bookmark.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.bookmark.model.book.Note;

/**
 * An UI component that displays more detailed information of a {@code Book}.
 */
public class NoteCard extends UiPart<Region> {

    private static final String FXML = "NoteCard.fxml";
    public final Note note;

    @FXML
    private Label noteTitle;

    @FXML
    private Label noteText;

    /**
     * Creates a {@code DetailedBookCard} with the given {@code Book} and index to display.
     */
    public NoteCard(Note note) {
        super(FXML);
        this.note = note;
        initialize(note);
    }

    /**
     * Work around for subclasses to specify their own fxml file path.
     */
    public NoteCard(Note note, String fxml) {
        super(fxml);
        this.note = note;
    }

    /**
     * Initialises the note card with the note contents.
     */
    protected void initialize(Note note) {
        String title = note.title + ":";
        String text = note.text;
        noteTitle.setText(title);
        noteText.setText(text);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCard)) {
            return false;
        }

        // state check
        NoteCard card = (NoteCard) other;
        return noteTitle.getText().equals(card.noteTitle.getText())
                       && noteText.getText().equals(card.noteText.getText())
                       && note.equals(card.note);
    }
}
