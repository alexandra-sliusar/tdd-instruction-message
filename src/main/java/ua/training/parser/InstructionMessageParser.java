package ua.training.parser;

import org.apache.commons.lang.StringUtils;
import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;
import ua.training.model.InstructionType;

import java.time.Instant;

public class InstructionMessageParser {
    public InstructionMessage parseMessage(String message) {
        checkMessageIsNotEmpty(message);
        String[] messageSplits = message.split(" ");
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
        if (!messageSplits[0].equals("InstructionMessage") || messageSplits.length != 6)
            throw new MessageParseException();
    }

    private InstructionMessage buildInstructionMessage(String[] messageSplits) {
        return new InstructionMessage.Builder()
                .setInstructionType(
                        parseInstructionType(messageSplits[1]))
                .setProductCode(messageSplits[2])
                .setQuantity(
                        parseInteger(messageSplits[3]))
                .setUom(
                        parseInteger(messageSplits[4]))
                .setTimestamp(
                        parseTimestamp(messageSplits[5]))
                .build();
    }

    private InstructionType parseInstructionType(String instructionType) {
        return InstructionType.valueOf(instructionType);
    }

    private Integer parseInteger(String integerToParse) {
        return Integer.parseInt(integerToParse);
    }

    private Instant parseTimestamp(String timestamp) {
        return Instant.parse(timestamp);
    }

}
