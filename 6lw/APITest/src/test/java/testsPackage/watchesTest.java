package testsPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class watchesTest {

    static String GET_ALL_PRODUCTS = "http://52.136.215.164:9000/api/products";
    static String GET_DELETE_BY_ID = "http://52.136.215.164:9000/api/deleteproduct";
    static String POST_ADD_PRODUCT = "http://52.136.215.164:9000/api/addproduct";
    static String POST_EDIT_PRODUCT = "http://52.136.215.164:9000/api/editproduct";
    static String LOGIN = "test177";
    static String PASSWORD = "test177";

    @Test
    public void GetAllProducts_ResponseHeaderAndStatusCode_ShouldBeCorrect() {
        given().
                when().
                get(GET_ALL_PRODUCTS).
                then().assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                header("Content-Type", equalTo("application/json"));
        System.out.println("All products response header correct, status 200");
    }

    @Test
    public void BasicAuthenticationToAddProduct_ShouldBeGivenAccess() {

        given().
                auth().
                preemptive().
                basic(LOGIN, PASSWORD).
                when().
                get(POST_ADD_PRODUCT).
                then().
                assertThat().
                statusCode(200);
        System.out.println("Authentication to add access given, status 200");
    }

    @Test
    public void APIDeleteProduct_ProductDeleted() {
        String IDtoDelete = "507";
            given().
                    param("id", IDtoDelete).
                    auth().
                    preemptive().
                    basic(LOGIN, PASSWORD).
                    when().
                    get(GET_DELETE_BY_ID).
                    then().
                    assertThat().
                    statusCode(200);
    }

    @Test
    public void POSTProduct_WithJSONParams() {
        RestAssured.baseURI = "http://52.136.215.164:9000/api/addproduct";
        given().
                contentType("application/json").
                body(JSONParams.Params().toString()).
                when().
                post("").then();
    }

    @Test
    public void POSTProduct_WithJSONParams_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = "http://52.136.215.164:9000/api/addproduct";
        int status = given().
                contentType("application/json").
                body(JSONParams.Params().toString()).
                when().
                post("").
                then().
                assertThat().
                statusCode(200).extract().path("status");
        assertEquals(1, status);
    }

    @Test
    public void DeleteAllCreatedTests() {
        String IDtoDelete = "507";
        for (int IDtoDelete2 = 505; IDtoDelete2 < 511; IDtoDelete2++) {
            given().
                    param("id", IDtoDelete2).
                    auth().
                    preemptive().
                    basic(LOGIN, PASSWORD).
                    when().
                    get(GET_DELETE_BY_ID).
                    then().
                    assertThat().
                    statusCode(200);
        }
    }
}
