package com.Restaurants_App.android.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class CitiesModel {

    public List<Cities> getData() {
        return data;
    }

    public void setData(List<Cities> data) {
        this.data = data;
    }

    @SerializedName("data")
    List<Cities> data = new ArrayList<>();
    public class Cities {
        @SerializedName("name")
        private String Name;
       @SerializedName("id")
        private int Id;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }
    }
}

