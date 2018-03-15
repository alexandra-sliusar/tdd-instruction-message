package ua.training.parser;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;
import ua.training.model.InstructionType;

import java.time.Instant;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class InstructionMessageParserTest {

    private InstructionMessageParser testedObject = new InstructionMessageParser();

    private static InstructionMessage instructionMessage;
    private static String instructionMessageString;

    @BeforeClass
    public static void setUpMessageAndMessageString() {
        String delimiter = " ";
        String type = "InstructionMessage";
        InstructionType instructionType = InstructionType.A;
        String productCode = "AA11";
        Integer quantity = 1000;
        Integer uom = 10;
        Instant timestamp = Instant.parse("2015-03-05T10:04:56.012Z");

        instructionMessageString = new StringBuilder(type).append(delimiter)
                .append(instructionType).append(delimiter)
                .append(productCode).append(delimiter)
                .append(quantity).append(delimiter)
                .append(uom).append(delimiter)
                .append(timestamp)
                .toString();
        instructionMessage = new InstructionMessage.Builder()
                .setInstructionType(instructionType)
                .setProductCode(productCode)
                .setQuantity(quantity)
                .setUom(uom)
                .setTimestamp(timestamp)
                .build();
    }

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
        InstructionMessage actualMessage = testedObject.parseMessage(instructionMessageString);

        assertThat(actualMessage.getInstructionType(), equalTo(instructionMessage.getInstructionType()));
    }

    @Test
    public void shouldSetProductCodeWhenMessageIsValid() {
        InstructionMessage actualMessage = testedObject.parseMessage(instructionMessageString);

        assertThat(actualMessage.getProductCode(), equalTo(instructionMessage.getProductCode()));
    }

    @Test
    public void shouldSetQuantityWhenMessageIsValid() {
        InstructionMessage actualMessage = testedObject.parseMessage(instructionMessageString);

        assertThat(actualMessage.getQuantity(), equalTo(instructionMessage.getQuantity()));
    }

    @Test
    public void shouldSetUomWhenMessageIsValid() {
        InstructionMessage actualMessage = testedObject.parseMessage(instructionMessageString);

        assertThat(actualMessage.getUom(), equalTo(instructionMessage.getUom()));
    }

    @Test
    public void shouldSetTimestampWhenMessageIsValid() {
        InstructionMessage actualMessage = testedObject.parseMessage(instructionMessageString);

        assertThat(actualMessage.getTimestamp(), equalTo(instructionMessage.getTimestamp()));
    }

    @Test(expected = MessageParseException.class)
    public void shouldThrowExceptionWhenMessageTypeIsNotValid() {
        String otherTypeString = "TestMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z";

        testedObject.parseMessage(otherTypeString);
    }
    @Test(expected = MessageParseException.class)
    public void shouldThrowExceptionWhenMessageSizeIsNotValid() {
        String otherSizeString = "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z additional";

        testedObject.parseMessage(otherSizeString);
    }

}
