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
        return buildInstructionMessage(splits);
    }

    private InstructionMessage buildInstructionMessage(String[] messageSplits) {
        InstructionMessage instructionMessage = new InstructionMessage();
        instructionMessage.setInstructionType(parseInstructionType(messageSplits[1]));
        instructionMessage.setProductCode(messageSplits[2]);
        instructionMessage.setQuantity(parseInteger(messageSplits[3]));
        instructionMessage.setUom(parseInteger(messageSplits[4]));
        instructionMessage.setTimestamp(parseTimestamp(messageSplits[5]));
        return instructionMessage;
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
