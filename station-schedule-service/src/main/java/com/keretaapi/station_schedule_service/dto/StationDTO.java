package com.keretaapi.station_schedule_service.dto;

import lombok.Data;

@Data
public class StationDTO {
    private Long id;
    private String code;
    private String name;
    private String city;

}
