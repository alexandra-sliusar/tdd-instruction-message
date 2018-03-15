package ua.training.parser;

import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;
import ua.training.model.InstructionType;

import java.time.Instant;

public class InstructionMessageParser {
    public InstructionMessage parseMessage(String message) {
        if (message == null || message.isEmpty())
            throw new MessageParseException();
        String[] splits = message.split(" ");
        if (!splits[0].equals("InstructionMessage") || splits.length != 6)
            throw new MessageParseException();
        return buildInstructionMessage(splits);
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
