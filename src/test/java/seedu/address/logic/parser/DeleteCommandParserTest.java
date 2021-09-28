package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalEvents.EVENTNAME_ONE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.model.event.EventName;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "s/1" and "s/1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 *
 * Note: This javadoc is from the original test. To edit when delete s/ no longer is by index.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteStudentCommand() {
        assertParseSuccess(parser, " " + PREFIX_STUDENT + "1",
                new DeleteStudentCommand(INDEX_FIRST_PERSON));

        assertParseSuccess(parser, " "
                        + PREFIX_STUDENT + "1" + " "
                        + PREFIX_EVENT + EVENTNAME_ONE,
                new DeleteStudentCommand(INDEX_FIRST_PERSON));

        assertParseSuccess(parser, " "
                        + PREFIX_EVENT + EVENTNAME_ONE + " "
                        + PREFIX_STUDENT + "1",
                new DeleteStudentCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validArgs_returnsDeleteEventCommand() {
        assertParseSuccess(parser, " " + PREFIX_EVENT + EVENTNAME_ONE,
                new DeleteEventCommand(EVENTNAME_ONE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        assertParseFailure(parser, " missing prefixes ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " " + PREFIX_STUDENT + "a", MESSAGE_INVALID_INDEX);

        assertParseFailure(parser, " "
                        + PREFIX_STUDENT + "a" + " "
                        + PREFIX_EVENT + EVENTNAME_ONE, MESSAGE_INVALID_INDEX);

        assertParseFailure(parser, " " + PREFIX_EVENT, EventName.MESSAGE_CONSTRAINTS);
    }
}
