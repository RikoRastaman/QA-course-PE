package testsPackage;

import static io.restassured.RestAssured.given;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.util.Collection;
import java.util.List;

public class GoodRequests {

    public static Collection<GoodOutDto> getAllGoods() {
        return given()
                .get("http://52.136.215.164:9000/api/products")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<GoodOutDto>>() {
                });
    }

    public void deleteGoodThroughApi(String id) {
        deleteThroughApi("http://52.136.215.164:9000/api/deleteproduct" + id);
    }

    public static GoodOutDto getGoodByIdThroughApi(String id) {
        Collection<GoodOutDto> out = getAllGoods();
        return out.stream().filter(i -> i.getId().equals(id)).findFirst()
                .orElseThrow(null);
    }

    public void deleteThroughApi(String url) {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
