package com.example.coronacasestracker.models;

import java.util.ArrayList;
import java.util.List;

public class LocationStats {

        private String location;
        private String last_updated_date;
        private int new_cases;



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLast_updated_date() {
        return last_updated_date;
    }

    public void setLast_updated_date(String last_updated_date) {
        this.last_updated_date = last_updated_date;
    }


    public int getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(int new_cases) {
        this.new_cases = new_cases;
    }

   @Override
        public String toString() {
            return "LocationStats{" +
                    "location=" + location+ '\'' +
                    ", last_updated_date='" + last_updated_date + '\'' +
                    ",new_cases=" +new_cases +
                    '}';
        }
    }
