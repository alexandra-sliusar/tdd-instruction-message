package ua.training.parser;

import org.junit.Test;
import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;
import ua.training.model.InstructionType;

import java.time.Instant;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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

    @Test
    public void shouldSetInstructionTypeWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage(InstructionType.A, null, null, null, null);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getInstructionType(), equalTo(expectedMessage.getInstructionType()));
    }

    @Test
    public void shouldSetProductCodeWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage(null, "AA11", null, null, null);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getProductCode(), equalTo(expectedMessage.getProductCode()));
    }

    @Test
    public void shouldSetQuantityWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage(null, null, 1000, null, null);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getQuantity(), equalTo(expectedMessage.getQuantity()));
    }

    @Test
    public void shouldSetUomWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage(null, null, null, 10, null);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getUom(), equalTo(expectedMessage.getUom()));
    }

    @Test
    public void shouldSetTimestampWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage(null, null, null, null, Instant.parse("2015-03-05T10:04:56.012Z"));

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getTimestamp(), equalTo(expectedMessage.getTimestamp()));
    }
}
