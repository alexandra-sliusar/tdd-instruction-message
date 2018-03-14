package ua.training.parser;

import ua.training.exception.MessageParseException;
import ua.training.model.InstructionMessage;

public class InstructionMessageParser {
    public InstructionMessage parseMessage(String message) {
        if (message == null || message.isEmpty())
            throw new MessageParseException();
        String[] splits = message.split(" ");
        InstructionMessage instructionMessage = new InstructionMessage();
        instructionMessage.setProductCode(splits[2]);
        return instructionMessage;
    }
}
