package com.magdaproject.myapplication.models;

public class Player {
    public String username, password, name,  email;
    public Player(){}
    public Player(String username, String password, String name, String email) {
      username = username;
      password = password;
      email = email;
      name = name;
    }

    public void setName (String setName) {
        name = setName;
    }

   public String getName () {
       return name;
    }

    public void setUserName (String setUserName) {
        username = setUserName;
    }

    public String getUserName () {
        return username;
    }

    public void setPswd (String setPswd) {
        password = setPswd;
    }

    public String getPswd () {
        return password;
    }

    public void setEmail (String setEmail) {
        email = setEmail;
    }

    public String getEmail () {
        return email;
    }
}
