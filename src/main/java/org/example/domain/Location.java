package org.example.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Location {
    private int code;

    private String city;

    private String district;
    private String neighborhood;

    private int nx;
    private int ny;

    public Location(int nx, int ny){
        this.nx=nx;
        this.ny=ny;
    }

}
