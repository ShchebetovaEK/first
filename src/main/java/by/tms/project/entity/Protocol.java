package by.tms.project.entity;

import java.time.LocalDate;

public class Protocol {
 private long id;
 private LocalDate protocolData;
 private String protocolPayer;

    public Protocol(long id, LocalDate protocolData, String protocolPayer) {
        this.id = id;
        this.protocolData = protocolData;
        this.protocolPayer = protocolPayer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Protocol protocol = (Protocol) o;

        if (id != protocol.id) return false;
        if (!protocolData.equals(protocol.protocolData)) return false;
        return protocolPayer.equals(protocol.protocolPayer);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + protocolData.hashCode();
        result = 31 * result + protocolPayer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "id=" + id +
                ", protocolData=" + protocolData +
                ", protocolPayer='" + protocolPayer + '\'' +
                '}';
    }
}
