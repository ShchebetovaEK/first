package by.tms.project.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Protocol extends Entity<Protocol> {
    private long protocolId;
    private LocalDate protocolData;
    private String protocolPayer;
    private BigDecimal protocolCost;

    public Protocol() {
    }

    public Protocol(long protocolId, LocalDate protocolData, String protocolPayer, BigDecimal protocolCost) {
        this.protocolId = protocolId;
        this.protocolData = protocolData;
        this.protocolPayer = protocolPayer;
        this.protocolCost = protocolCost;
    }

    public long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(long protocolId) {
        this.protocolId = protocolId;
    }

    public LocalDate getProtocolData() {
        return protocolData;
    }

    public void setProtocolData(LocalDate protocolData) {
        this.protocolData = protocolData;
    }

    public String getProtocolPayer() {
        return protocolPayer;
    }

    public void setProtocolPayer(String protocolPayer) {
        this.protocolPayer = protocolPayer;
    }

    public BigDecimal getProtocolCost() {
        return protocolCost;
    }

    public void setProtocolCost(BigDecimal protocolCost) {
        this.protocolCost = protocolCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Protocol protocol = (Protocol) o;

        if (protocolId != protocol.protocolId) return false;
        if (!protocolData.equals(protocol.protocolData)) return false;
        if (!protocolPayer.equals(protocol.protocolPayer)) return false;
        return protocolCost.equals(protocol.protocolCost);
    }

    @Override
    public int hashCode() {
        int result = (int) (protocolId ^ (protocolId >>> 32));
        result = 31 * result + protocolData.hashCode();
        result = 31 * result + protocolPayer.hashCode();
        result = 31 * result + protocolCost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Protocol{");
        sb.append("protocolId=").append(protocolId);
        sb.append(", protocolData=").append(protocolData);
        sb.append(", protocolPayer='").append(protocolPayer).append('\'');
        sb.append(", protocolCost=").append(protocolCost);
        sb.append('}');
        return sb.toString();
    }

    public static class ProtocolBuilder {
        private long protocolId;
        private LocalDate protocolData;
        private String protocolPayer;
        private BigDecimal protocolCost;

        private Protocol protocol = new Protocol();

        public ProtocolBuilder(){}

        public ProtocolBuilder setProtocolId() {
            this.protocolId = protocolId;
            return this;
        }

        public ProtocolBuilder setProtocolData() {
            this.protocolData = protocolData;
            return this;
        }

        public ProtocolBuilder setProtocolPayer() {
            this.protocolPayer = protocolPayer;
            return this;
        }

        public ProtocolBuilder setProtocolCost() {
            this.protocolCost = protocolCost;
            return this;
        }

        public Protocol buildProtocol() {
            return new Protocol(protocolId, protocolData, protocolPayer, protocolCost);
        }
    }
}
