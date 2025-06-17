package com.keretaapi.transaction_service.dto;

public class CreateTransactionRequest {
    private Long scheduleId;
   

    public Long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}