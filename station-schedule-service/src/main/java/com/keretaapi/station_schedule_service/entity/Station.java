package com.keretaapi.station_schedule_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String city;

    @OneToMany(mappedBy = "stationFrom", cascade = CascadeType.ALL)
    private List<Schedule> departures;

    @OneToMany(mappedBy = "stationTo", cascade = CascadeType.ALL)
    private List<Schedule> arrivals;
}