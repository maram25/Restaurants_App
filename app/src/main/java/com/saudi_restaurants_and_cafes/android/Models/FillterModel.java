package com.saudi_restaurants_and_cafes.android.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FillterModel {

    @SerializedName("cities")
    List<Cities> cities = new ArrayList<>();
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

    @SerializedName("categories")
    List<Categories> categories = new ArrayList<>();
    public class Categories {
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

    @SerializedName("types")
    List<Types> types = new ArrayList<>();
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
 @SerializedName("cooking_types")
    List<Cooking_types> cooking_types = new ArrayList<>();
    public class Cooking_types {
        @SerializedName("name")
        private String Name;

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

        @SerializedName("id")
        private int Id;
    }

    public List<Cities> getCities() {
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public List<Cooking_types> getCooking_types() {
        return cooking_types;
    }

    public void setCooking_types(List<Cooking_types> cooking_types) {
        this.cooking_types = cooking_types;
    }

    public List<Place_characteristics> getPlace_characteristics() {
        return place_characteristics;
    }

    public void setPlace_characteristics(List<Place_characteristics> place_characteristics) {
        this.place_characteristics = place_characteristics;
    }

    @SerializedName("place_characteristics")
    List<Place_characteristics> place_characteristics = new ArrayList<>();
    public class Place_characteristics {
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
