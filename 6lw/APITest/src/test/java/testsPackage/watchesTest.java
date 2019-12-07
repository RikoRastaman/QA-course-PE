package testsPackage;

import com.sun.xml.internal.bind.v2.model.core.ID;
import io.restassured.http.ContentType;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
public class watchesTest {

    @Test
    public void test_ResponseHeaderData_ShouldBeCorrect() {
        given().
        when().
            get("http://52.136.215.164:9000/api/products").
        then().assertThat().statusCode(200).
            and().
            contentType(ContentType.JSON).
        and().
            header("Content-Type", equalTo("application/json"));
    }

    @Test
    public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {

        given().
            auth().
            preemptive().
            basic("test177", "test177").
        when().
            get("http://52.136.215.164:9000/api/addproduct").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void test_APIDeleteProduct_ProductDeleted() {
        String IDtoDelete = "403";
        given().
            param("id", IDtoDelete).
            auth().
            preemptive().
            basic("test177", "test177").
        when().
            get("http://52.136.215.164:9000/api/deleteproduct").
        then().
            assertThat().
            statusCode(200);
    }
}
