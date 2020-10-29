package seedu.bookmark.logic.commands;

import seedu.bookmark.logic.ViewManager;
import seedu.bookmark.logic.ViewType;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.history.State;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public static final ViewManager viewManager = new ViewManager();

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    protected void storeViewType(State state, ViewType viewType) {
        if (viewType != ViewType.MOST_RECENTLY_USED) {
            viewManager.setCurrentView(viewType);
        }
        viewManager.addViewTypePairing(state, viewType);
    }

}
