package com.example.valorant;

public class Users {
    private String username;
    private int aimRating;
    private int gamesenseRating;
    private int communicationRating;
    private String email;
    private String objectId;
    private String ownerId;
    private String profilePicture;
    private int ranking;
    private int teamRank;

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

    public int getTeamRank() {
        return teamRank;
    }

    public void setTeamRank(int teamRank) {
        this.teamRank = teamRank;
    }
}
