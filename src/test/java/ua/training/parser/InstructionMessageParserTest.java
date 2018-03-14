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
        InstructionMessage expectedMessage = new InstructionMessage();
        expectedMessage.setInstructionType(InstructionType.A);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getInstructionType(), equalTo(expectedMessage.getInstructionType()));
    }

    @Test
    public void shouldSetProductCodeWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage();
        expectedMessage.setProductCode("AA11");

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getProductCode(), equalTo(expectedMessage.getProductCode()));
    }

    @Test
    public void shouldSetQuantityWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage();
        expectedMessage.setQuantity(1000);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getQuantity(), equalTo(expectedMessage.getQuantity()));
    }

    @Test
    public void shouldSetUomWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage();
        expectedMessage.setUom(10);

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getUom(), equalTo(expectedMessage.getUom()));
    }

    @Test
    public void shouldSetTimestampWhenMessageIsValid() {
        String messageString = "InstructionMessage A AA11 1000 10 2015-03-05T10:04:56.012Z";
        InstructionMessage expectedMessage = new InstructionMessage();
        expectedMessage.setTimestamp(Instant.parse("2015-03-05T10:04:56.012Z"));

        InstructionMessage actualMessage = testedObject.parseMessage(messageString);

        assertThat(actualMessage.getTimestamp(), equalTo(expectedMessage.getTimestamp()));
    }
}
