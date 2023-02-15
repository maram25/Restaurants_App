package com.Restaurants_App.android.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllPlacesModel {


    public List<Place> getData() {
        return data;
    }

    public void setData(List<Place> data) {
        this.data = data;
    }

    @SerializedName("data")
    List<Place> data = new ArrayList<>();

    public class Place {
        @SerializedName("id")
        private int Id;
        @SerializedName("rate")
        private int rate;
        @SerializedName("name")
        private String name;
        @SerializedName("description")
        private String description;
        @SerializedName("phone")
        private String phone;
        @SerializedName("open_time")
        private String open_time;
        @SerializedName("close_time")
        private String close_time;
        @SerializedName("location")
        private String location;
        @SerializedName("facebook_link")
        private String facebook_link;
        @SerializedName("youtube_link")
        private String youtube_link;
        @SerializedName("instagram_link")
        private String instagram_link;
        @SerializedName("twitter_link")
        private String twitter_link;
        @SerializedName("can_animal")
        private int can_animal;
        @SerializedName("can_children")
        private int can_children;
        @SerializedName("can_delivery")
        private int can_delivery;


/*

        @SerializedName("sort_by")
        private String sort_by;
*/

        public int getCan_delivery() {
            return can_delivery;
        }

        public void setCan_delivery(int can_delivery) {
            this.can_delivery = can_delivery;
        }

        @SerializedName("can_music")
        private int can_music;
        @SerializedName("can_family")
        private int can_family;

      /*  @SerializedName("cooktypes")
        List<String> cooktypes = new ArrayList<>();*/

      /*  @SerializedName("placeCharacteristicPlaces")
        List<String> placeCharacteristicPlaces = new ArrayList<>();*/


        public List<String> getMenu_image_links() {
            return menu_image_links;
        }

        public void setMenu_image_links(List<String> menu_image_links) {
            this.menu_image_links = menu_image_links;
        }

        @SerializedName("featured")
        private String featured;
        @SerializedName("city")
        private String city;
        @SerializedName("type")
        private String type;
        @SerializedName("type_id")
        private int type_id;

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        @SerializedName("category")
        private String category;
        @SerializedName("logo")
        private String logo;
        @SerializedName("cover")
        private String cover;
        @SerializedName("menus")
        public List<String> menu_image_links;


        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getFacebook_link() {
            return facebook_link;
        }

        public void setFacebook_link(String facebook_link) {
            this.facebook_link = facebook_link;
        }

        public String getYoutube_link() {
            return youtube_link;
        }

        public void setYoutube_link(String youtube_link) {
            this.youtube_link = youtube_link;
        }

        public String getInstagram_link() {
            return instagram_link;
        }

        public void setInstagram_link(String instagram_link) {
            this.instagram_link = instagram_link;
        }

        public String getTwitter_link() {
            return twitter_link;
        }

        public void setTwitter_link(String twitter_link) {
            this.twitter_link = twitter_link;
        }

        public int getCan_animal() {
            return can_animal;
        }

        public void setCan_animal(int can_animal) {
            this.can_animal = can_animal;
        }

        public int getCan_children() {
            return can_children;
        }

        public void setCan_children(int can_children) {
            this.can_children = can_children;
        }

        public int getCan_music() {
            return can_music;
        }

        public void setCan_music(int can_music) {
            this.can_music = can_music;
        }

        public int getCan_family() {
            return can_family;
        }

        public void setCan_family(int can_family) {
            this.can_family = can_family;
        }


        public String getFeatured() {
            return featured;
        }

        public void setFeatured(String featured) {
            this.featured = featured;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

      /*  public String getSort_by() { return sort_by; }

        public void setSort_by(String sort_by) { this.sort_by = sort_by; }*/



    }

}
