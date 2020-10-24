package seedu.bookmark.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.model.book.Note;

/**
 * Panel containing the list of books.
 */
public class NoteListPanel extends UiPart<Region> {

    private static final String FXML = "BookListPanel.fxml";
    @FXML
    protected ListView<Note> noteListView;
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    /**
     * Creates a {@code BookListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(ObservableList<Note> noteList) {
        super(FXML);
        noteListView.setItems(noteList);
        noteListView.setCellFactory(lv -> new NoteListViewCell());
    }

    public NoteListPanel() {
        super(FXML);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code BookCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note).getRoot());
            }
        }
    }

}
