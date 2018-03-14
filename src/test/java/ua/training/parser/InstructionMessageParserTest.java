package ua.training.parser;

import org.junit.Test;
import ua.training.exception.MessageParseException;

public class InstructionMessageParserTest {

    private InstructionMessageParser testedObject = new InstructionMessageParser();

    @Test(expected = MessageParseException.class)
    public void shouldThrowExceptionWhenStringIsNull() {
        String nullString = null;

        testedObject.parseMessage(nullString);
    }

    @Test(expected = MessageParseException.class)
    public void shouldThrowExceptionWhenStringIsEmpty() {
        String emptyString = "";

        testedObject.parseMessage(emptyString);
    }
}
