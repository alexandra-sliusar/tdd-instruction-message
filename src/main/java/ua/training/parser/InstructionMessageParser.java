package ua.training.parser;

import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;

import java.time.Instant;

public class InstructionMessageParser {
    public InstructionMessage parseMessage(String message) {
        if (message == null || message.isEmpty())
            throw new MessageParseException();
        String[] splits = message.split(" ");
        InstructionMessage instructionMessage = new InstructionMessage();
        instructionMessage.setProductCode(splits[2]);
        instructionMessage.setQuantity(Integer.valueOf(splits[3]));
        instructionMessage.setUom(Integer.valueOf(splits[4]));
        instructionMessage.setTimestamp(Instant.parse(splits[5]));
        return instructionMessage;
    }
}
