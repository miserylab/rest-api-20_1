package in.reqres.tests;

import in.reqres.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests extends BaseTest{

    @Test
    @Tag("remote")
    @DisplayName("Successful Create User Test")
     void successfulCreateUserTest() {

        PostUserResponseModel postUserResponse = step("Make request", () ->
                given(userPostRequestSpec)
                .body(testData.createData)
                .when()
                .post("/users")
                .then()
                .spec(userPostResponseSpec)
                .extract().as(PostUserResponseModel.class)
        );

        step("check response", () -> {
            assertEquals(testData.createData.getName(), postUserResponse.getName());
            assertEquals(testData.createData.getJob(), postUserResponse.getJob());
        });
    }

    @Test
    @Tag("remote")
    @DisplayName("Successful Update User Test")
    void successfulUpdateUserTest() {

        PutUserResponseModel putUserResponse = step("Make request", () ->
                given(userPutRequestSpec)
                        .body(testData.updateData)
                        .when()
                        .put("/users/" + testData.getUserId())
                        .then()
                        .spec(userPutResponseSpec)
                        .extract().as(PutUserResponseModel.class)
        );

        step("check response", () -> {
            assertEquals(testData.updateData.getName(), putUserResponse.getName());
            assertEquals(testData.updateData.getJob(), putUserResponse.getJob());
        });
    }

    @Test
    @Tag("remote")
    @DisplayName("Successful Get User Test")
    void successfulGetUserInfoTest() {
        GetUserResponseModel getUserResponse = step("Make request", () ->
                given(userGetRequestSpec)
                        .when()
                        .get("/users/" + testData.getUserId())
                        .then()
                        .spec(userGetResponseSpec)
                        .extract().as(GetUserResponseModel.class));

        step("check response", () -> {
            assertEquals(Integer.parseInt(testData.user_get_data.get("id")), getUserResponse.getData().getId());
            assertEquals(testData.user_get_data.get("email"), getUserResponse.getData().getEmail());
            assertEquals(testData.user_get_data.get("first_name"), getUserResponse.getData().getFirst_name());
            assertEquals(testData.user_get_data.get("last_name"), getUserResponse.getData().getLast_name());
            assertEquals(testData.user_get_data.get("avatar"), getUserResponse.getData().getAvatar());
        });

    }

    @Test
    @Tag("remote")
    @DisplayName("User not found Test")
    void userNotFoundTest() {
        GetUserResponseModel getUserNotFound = step("Make request", () ->
                given(userGetRequestSpec)
                        .when()
                        .get("/users/" + testData.getNonExistedUserId())
                        .then()
                        .spec(getUserNotFoundResponseSpec)
                        .extract().as(GetUserResponseModel.class));

        step("check response", () -> {
            assertEquals(null,getUserNotFound.getData());
            assertEquals(null,getUserNotFound.getSupport());
        });
    }

    @Test
    @Tag("remote")
    @DisplayName("Successful Get User List on Page Test")
    void successfulUsersListOnPageTest() {
        GetUserListOnPageResponseModel getUsersListOnPageResponse = step("Make request", () ->
                given(userGetRequestSpec)
                        .when()
                        .param("page", testData.getPage())
                        .get("/users/")
                        .then()
                        .spec(userListOnPageGetResponseSpec)
                        .extract().as(GetUserListOnPageResponseModel.class));

        step("check response", () -> {
            assertEquals(Integer.parseInt(testData.user_list_data.get("page")), getUsersListOnPageResponse.getPage());
            assertEquals(Integer.parseInt(testData.user_list_data.get("per_page")), getUsersListOnPageResponse.getPer_page());
            assertEquals(Integer.parseInt(testData.user_list_data.get("total")), getUsersListOnPageResponse.getTotal());
            assertEquals(Integer.parseInt(testData.user_list_data.get("total_pages")), getUsersListOnPageResponse.getTotal_pages());
            assertEquals(Integer.parseInt(testData.user_list_data.get("per_page")), getUsersListOnPageResponse.getData().size());
        });
   }
}
