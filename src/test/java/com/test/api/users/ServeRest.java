package com.test.api.users;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.json.JSONObject;


import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

@DisplayName("Teste API - Users")
public class ServeRest {

    Faker faker = new Faker();
    String id;

    @Before
    public void createUser(){
        JSONObject user = new JSONObject();
        user.put("nome", faker.name().fullName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", "passwordStrong");
        user.put("administrador", "true");

        id = given().contentType("application/json").body(user.toString()).post("https://serverest.dev/usuarios").then().statusCode(201).extract().path("_id");
        System.out.println(id);
    }

    @Test
    public void editUser(){
        JSONObject user = new JSONObject();
        user.put("nome", faker.name().fullName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", "passwordStrong");
        user.put("administrador", "true");

        given().contentType("application/json").body(user.toString()).put("https://serverest.dev/usuarios/"+id).then().statusCode(200).log().all();
    }

    @Test
    public void getAllUsers(){
        given().get("https://serverest.dev/usuarios").then().statusCode(200).log().all();
    }

    @Test
    public void getUser(){
        given().get("https://serverest.dev/usuarios?email="+faker.internet().emailAddress()).then().statusCode(200).log().all();
    }

    @Test
    public void deleteUser(){
        given().delete("https://serverest.dev/usuarios/"+id).then().statusCode(200).log().all();
    }
}
