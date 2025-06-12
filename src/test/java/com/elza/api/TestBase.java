package com.elza.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected String token;

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

}
