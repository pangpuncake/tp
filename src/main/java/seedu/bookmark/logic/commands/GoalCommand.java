package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_PAGE;

import java.util.List;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Goal;

public class GoalCommand extends Command {
    public static final String COMMAND_WORD = "goal";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a goal for a specified book. \n"
            + "Parameters: INDEX (positive integer) "
            + PREFIX_PAGE + "PAGE NUMBER "
            + PREFIX_DEADLINE + "DEADLINE (DD-MM-YYYY) \n"
            + "Example: "
            + COMMAND_WORD + " 2 "
            + PREFIX_PAGE + "102 "
            + PREFIX_DEADLINE + "21-12-2024\n";

    public static final String MESSAGE_DEADLINE_OVERDUE = "%s has already passed. "
            + "Please choose a deadline later than today.";
    public static final String MESSAGE_ADD_GOAL_SUCCESS = "New goal for %s: %s";

    private final Index targetIndex;
    private final Goal goal;

    /**
     * Constructor for {@code GoalCommand} object with index and goal.
     * @param targetIndex index of book in List view.
     * @param goal to be set for the book.
     */
    public GoalCommand(Index targetIndex, Goal goal) {
        this.targetIndex = targetIndex;
        this.goal = goal;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> allBooks = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= allBooks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        if (goal.isOverdue()) {
            throw new CommandException(String.format(MESSAGE_DEADLINE_OVERDUE, goal.deadline));
        }

        Book bookWithoutGoal = allBooks.get(targetIndex.getZeroBased());
        Book bookWithGoal = Book.setGoal(bookWithoutGoal, goal);

        model.setBook(bookWithoutGoal, bookWithGoal);

        return new CommandResult(String.format(MESSAGE_ADD_GOAL_SUCCESS, bookWithGoal.getName(), goal.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoalCommand // instanceof handles nulls
                && targetIndex.equals(((GoalCommand) other).targetIndex) // same index
                && goal.equals(((GoalCommand) other).goal)); // same goal
    }
}