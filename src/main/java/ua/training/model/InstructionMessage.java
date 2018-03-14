package ua.training.model;

import java.time.Instant;

public final class InstructionMessage {
    private final InstructionType instructionType;
    private final String productCode;
    private final Integer quantity;
    private final Integer uom;
    private final Instant timestamp;

    public InstructionMessage(InstructionType instructionType, String productCode,
                               Integer quantity, Integer uom, Instant timestamp) {
        this.instructionType = instructionType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.uom = uom;
        this.timestamp = timestamp;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUom() {
        return uom;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
