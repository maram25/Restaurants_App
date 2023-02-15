package com.saudi_restaurants_and_cafes.android.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TypesModel {



    @SerializedName("data")
    List<Types> data = new ArrayList<>();

    public List<Types> getData() {
        return data;
    }

    public void setData(List<Types> data) {
        this.data = data;
    }

    public class Types {
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
