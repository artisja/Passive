package com.example.artisja.passive;

/**
 * Created by qvk871 on 4/19/17.
 */

public class Login {
    String siteName,password;

    public Login(String site,String passwordKey){
        this.siteName = site;
        this.password = passwordKey;

    }

    public String getSiteName() {
        return siteName;
    }

    public String getPassword() {
        return password;
    }

    public void setCiteName(String citeName) {
        this.siteName = siteName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
