package ua.training.parser;

import org.apache.commons.lang.StringUtils;
import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;
import ua.training.model.InstructionType;

import java.time.Instant;

public class InstructionMessageParser {

    private static String DELIMITER = " ";
    private static String ACCEPTED_MESSAGE_TYPE = "InstructionMessage";
    private static Integer ACCEPTED_MESSAGE_SPLIT_LENGTH = 6;
    private static Integer MESSAGE_TYPE_INDEX = 0;
    private static Integer INSTRUCTION_TYPE_INDEX = 1;
    private static Integer PRODUCT_CODE_INDEX = 2;
    private static Integer QUANTITY_INDEX = 3;
    private static Integer UOM_INDEX = 4;
    private static Integer TIMESTAMP_INDEX = 5;

    public InstructionMessage parseMessage(String message) {
        checkMessageIsNotEmpty(message);
        String[] messageSplits = message.split(DELIMITER);
        checkMessageValidity(messageSplits);
        return buildInstructionMessage(messageSplits);
    }

    private void checkMessageIsNotEmpty(String message) {
        boolean isMessageEmpty = StringUtils.isEmpty(message);
        if (isMessageEmpty) {
            throw new MessageParseException();
        }
    }

    private void checkMessageValidity(String[] messageSplits) {
        if (!messageSplits[MESSAGE_TYPE_INDEX].equals(ACCEPTED_MESSAGE_TYPE) || messageSplits.length != ACCEPTED_MESSAGE_SPLIT_LENGTH)
            throw new MessageParseException();
    }

    private InstructionMessage buildInstructionMessage(String[] messageSplits) {
        return new InstructionMessage.Builder()
                .setInstructionType(
                        parseInstructionType(messageSplits[INSTRUCTION_TYPE_INDEX]))
                .setProductCode(messageSplits[PRODUCT_CODE_INDEX])
                .setQuantity(
                        parseInteger(messageSplits[QUANTITY_INDEX]))
                .setUom(
                        parseInteger(messageSplits[UOM_INDEX]))
                .setTimestamp(
                        parseTimestamp(messageSplits[TIMESTAMP_INDEX]))
                .build();
    }

    private InstructionType parseInstructionType(String instructionType) {
        try {
            return InstructionType.valueOf(instructionType);
        } catch (IllegalArgumentException e) {
            throw new MessageParseException();
        }
    }

    private Integer parseInteger(String integerToParse) {
        try {
            return Integer.parseInt(integerToParse);
        } catch (NumberFormatException e) {
            throw new MessageParseException();
        }
    }

    private Instant parseTimestamp(String timestamp) {
        return Instant.parse(timestamp);
    }

}
