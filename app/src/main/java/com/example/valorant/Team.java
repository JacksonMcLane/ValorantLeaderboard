package com.example.valorant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Team implements Parcelable {
    private String teamName;
    private int rating;
    private int aimRating;
    private int gamesenseRating;
    private int communicationRating;
    private String teamPicture;
    private String objectId;
    private List<Users> members;

    public Team(){}

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", rating=" + rating +
                ", aimRating=" + aimRating +
                ", gamesenseRating=" + gamesenseRating +
                ", communicationRating=" + communicationRating +
                ", teamPicture='" + teamPicture + '\'' +
                ", objectId='" + objectId + '\'' +
                ", members=" + members +
                '}';
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAimRating() {
        return aimRating;
    }

    public void setAimRating(int aimRating) {
        this.aimRating = aimRating;
    }

    public int getGamesenseRating() {
        return gamesenseRating;
    }

    public void setGamesenseRating(int gamesenseRating) {
        this.gamesenseRating = gamesenseRating;
    }

    public int getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(int communicationRating) {
        this.communicationRating = communicationRating;
    }

    public String getTeamPicture() {
        return teamPicture;
    }

    public void setTeamPicture(String teamPicture) {
        this.teamPicture = teamPicture;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<Users> getMembers() {
        return members;
    }

    public void setMembers(List<Users> members) {
        this.members = members;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.teamName);
        dest.writeInt(this.rating);
        dest.writeInt(this.aimRating);
        dest.writeInt(this.gamesenseRating);
        dest.writeInt(this.communicationRating);
        dest.writeString(this.teamPicture);
        dest.writeString(this.objectId);
        dest.writeTypedList(this.members);
    }

    protected Team(Parcel in) {
        this.teamName = in.readString();
        this.rating = in.readInt();
        this.aimRating = in.readInt();
        this.gamesenseRating = in.readInt();
        this.communicationRating = in.readInt();
        this.teamPicture = in.readString();
        this.objectId = in.readString();
        this.members = in.createTypedArrayList(Users.CREATOR);
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
