package com.micromerger.ssms.addemployee.bean;

/* loaded from: classes2.dex */
public class AddStaffTicketing {
    private String ticketCnic;
    private String ticketData;
    private String ticketDateTime;
    private int ticketId;
    private String ticketReason;

    public int getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCnic() {
        return this.ticketCnic;
    }

    public void setTicketCnic(String ticketCnic) {
        this.ticketCnic = ticketCnic;
    }

    public String getTicketReason() {
        return this.ticketReason;
    }

    public void setTicketReason(String ticketReason) {
        this.ticketReason = ticketReason;
    }

    public String getTicketDateTime() {
        return this.ticketDateTime;
    }

    public void setTicketDateTime(String ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
    }

    public String getTicketData() {
        return this.ticketData;
    }

    public void setTicketData(String ticketData) {
        this.ticketData = ticketData;
    }

    public String toString() {
        return "AddStaffTicketing{ticketId=" + this.ticketId + ", ticketCnic='" + this.ticketCnic + "', ticketReason='" + this.ticketReason + "', ticketDateTime='" + this.ticketDateTime + "', ticketData='" + this.ticketData + "'}";
    }
}
