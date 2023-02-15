package com.saudi_restaurants_and_cafes.android.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TasnifModel {

    @SerializedName("cities")
    List<AllFillterModel.Cities> cities = new ArrayList<>();
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
