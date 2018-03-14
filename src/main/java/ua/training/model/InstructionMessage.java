package ua.training.model;

import java.time.Instant;

public final class InstructionMessage {
    private final InstructionType instructionType;
    private final String productCode;
    private final Integer quantity;
    private final Integer uom;
    private final Instant timestamp;

    private InstructionMessage(Builder builder) {
        this.instructionType = builder.instructionType;
        this.productCode = builder.productCode;
        this.quantity = builder.quantity;
        this.uom = builder.uom;
        this.timestamp = builder.timestamp;
    }

    public static class Builder {
        private InstructionType instructionType;
        private String productCode;
        private Integer quantity;
        private Integer uom;
        private Instant timestamp;

        public Builder setInstructionType(InstructionType instructionType) {
            this.instructionType = instructionType;
            return this;
        }

        public Builder setProductCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUom(Integer uom) {
            this.uom = uom;
            return this;
        }

        public Builder setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public InstructionMessage build() {
            return new InstructionMessage(this);
        }
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
