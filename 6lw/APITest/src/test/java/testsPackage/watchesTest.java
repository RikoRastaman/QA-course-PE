package testsPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class watchesTest {

    static String GET_ALL_PRODUCTS = "http://52.136.215.164:9000/api/products";
    static String GET_DELETE_BY_ID = "http://52.136.215.164:9000/api/deleteproduct";
    static String POST_ADD_PRODUCT = "http://52.136.215.164:9000/api/addproduct";
    static String POST_EDIT_PRODUCT = "http://52.136.215.164:9000/api/editproduct";
    static int ID_OF_NOT_TEST_PRODUCT = 269;
    static String LOGIN = "test177";
    static String PASSWORD = "test177";
    public int firstTestID = 591;
    public int lastTestID = 602;

    @Test
    public void test1_GetAllProducts_ResponseHeaderAndStatusCode_ShouldBeCorrect() {
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
    public void test2_BasicAuthenticationToAddProduct_ShouldBeGivenAccess() {
        given().
                auth().
                preemptive().
                basic(LOGIN, PASSWORD).
                when().
                get(POST_ADD_PRODUCT).
                then().
                assertThat().
                statusCode(200).body("status", equalTo(0));
        System.out.println("Authentication to add access given, status 200");
    }

    @Test
    public void test3_POSTProductWithAllValidParams_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1))
                .extract().response();

        int IDProduct = body.path("id");
        System.out.println("Product with valid params added, with ID:" + IDProduct);
    }

    @Test
    public void test4_POSTProductWithRussianTitle_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("title", "тест на русском 177");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with russian title added, with ID:" + IDProduct);
    }

    @Test
    public void test5_POSTProductWithEmptyTitle_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("title", "");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with empty title added, with ID:" + IDProduct);
    }

    @Test
    public void test6_POSTProductWithCategoryOutOfRange_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("category", "16");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with category out of range added, with ID:" + IDProduct);
    }

    @Test
    public void test7_POSTProductWithNegativeCategory_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("category", "-2");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with negative category added, with ID:" + IDProduct);
    }

    @Test
    public void test8_POSTProductWithNegativePrice_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("price", "-100");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with negative price added, with ID:" + IDProduct);
    }

    @Test
    public void test9_POSTProductWithZeroPrice_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("price", "0");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with zero price added, with ID:" + IDProduct);
    }

    @Test
    public void test10_POSTProductWithMAXPrice_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("price", Integer.MAX_VALUE + 1);

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with MAX price added, overflow of int, with ID:" + IDProduct);
    }

    @Test
    public void test11_POSTProductWithOldPriceLeastThenActualPrice_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("price", "999");
        params.ChangeValue("old_price", "777");

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product with old price least then actual added, with ID:" + IDProduct);
    }

    @Test
    public void test12_POSTProductWithStatus0_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("status", 0);

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product With Status 0 added, with ID:" + IDProduct);
    }

    @Test
    public void test13_POSTProductWithHit0_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();
        params.ChangeValue("hit", 0);

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product With Hit 0 added, with ID:" + IDProduct);
    }

    @Test
    public void test14_POSTProductWithKeyWords_GetResponseStatus_StatusIs1() {
        RestAssured.baseURI = POST_ADD_PRODUCT;
        JSONParams params = new JSONParams();

        params.ChangeValue("title", "IHaveKeys");
        params.PutArrayInKeys();

        ResponseBodyExtractionOptions body = given().
                contentType("application/json").
                body(params.GetParams().toString()).
                when().
                post("").
                then().
                body("status", equalTo(1)).
                extract().body();

        int IDProduct = body.path("id");
        System.out.println("Product With keys, with ID:" + IDProduct);
    }

    @Test
    public void test99_DeleteAllCreatedTests() {
        if (firstTestID > ID_OF_NOT_TEST_PRODUCT) {
            for (int IDtoDelete = firstTestID; IDtoDelete <= lastTestID; IDtoDelete++)
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
    }
}
