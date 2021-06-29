package com.finalprojectandreeapasc;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String RECIPES = API_PATH + "/recipes";
    public static final String ENTITY = "/{id}";
    public static final String EXPORT_REPORT = "/export/{type}";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
    public static final String CUSTOMERS = API_PATH + "/customers";

    public static final String MESSAGE = "/messages";
    public static final String SEND = RECIPES + MESSAGE;
    public static final String WEBSOCKET = API_PATH + "/websocket";
}
