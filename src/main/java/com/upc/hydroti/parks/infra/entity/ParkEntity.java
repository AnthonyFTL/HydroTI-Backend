package com.upc.hydroti.parks.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parks")
public class ParkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "state", nullable = false)
    private String state = "INACTIVO";

    @Column(name = "devices_connected", nullable = false)
    private Integer devicesConnected = 0;

    @Column(name="manual_irrigation", nullable = false)
    private Boolean manualIrrigation = true;
}
