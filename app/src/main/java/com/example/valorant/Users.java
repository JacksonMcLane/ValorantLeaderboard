package com.example.valorant;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable, Comparable<Users> {
    private String username;
    private int aimRating;
    private int gamesenseRating;
    private int communicationRating;
    private String email;
    private String objectId;
    private String ownerId;
    private String profilePicture;
    private int ranking;

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", aimRating=" + aimRating +
                ", gamesenseRating=" + gamesenseRating +
                ", communicationRating=" + communicationRating +
                ", email='" + email + '\'' +
                ", objectId='" + objectId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", ranking=" + ranking +
                '}';
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public int getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(int communicationRating) {
        this.communicationRating = communicationRating;
    }

    public int getGamesenseRating() {
        return gamesenseRating;
    }

    public void setGamesenseRating(int gamesenseRating) {
        this.gamesenseRating = gamesenseRating;
    }

    public int getAimRating(){
        return aimRating;
    }

    public void setAimRating(int aimRating){
        this.aimRating = aimRating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getRanking(){
        return ranking;
    }

    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    @Override
    public int compareTo(Users user) {
        return this.getRanking() - user.getRanking();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeInt(this.aimRating);
        dest.writeInt(this.gamesenseRating);
        dest.writeInt(this.communicationRating);
        dest.writeString(this.email);
        dest.writeString(this.objectId);
        dest.writeString(this.ownerId);
        dest.writeString(this.profilePicture);
        dest.writeInt(this.ranking);
    }

    protected Users(Parcel in) {
        this.username = in.readString();
        this.aimRating = in.readInt();
        this.gamesenseRating = in.readInt();
        this.communicationRating = in.readInt();
        this.email = in.readString();
        this.objectId = in.readString();
        this.ownerId = in.readString();
        this.profilePicture = in.readString();
        this.ranking = in.readInt();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
