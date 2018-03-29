package com.java_dapps.store.model.bindingModel;

public class CustomerRegisterModel {
    private String name;
    private String username;
    private String password;
    private String keystoreJson;

    public CustomerRegisterModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeystoreJson() {
        return keystoreJson;
    }

    public void setKeystoreJson(String keystoreJson) {
        this.keystoreJson = keystoreJson;
    }
}
