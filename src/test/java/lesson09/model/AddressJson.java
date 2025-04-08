package lesson09.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressJson {
    @JsonProperty("Street")
    private String street;
    @JsonProperty("City")
    private String city;
    @JsonProperty("HouseNumber")
    private int house;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }
}
