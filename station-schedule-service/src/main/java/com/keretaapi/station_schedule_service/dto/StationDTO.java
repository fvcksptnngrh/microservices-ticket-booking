package com.keretaapi.station_schedule_service.dto;

public class StationDTO {
    private Long id;
    private String code;
    private String name;
    private String city;

    public StationDTO() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
