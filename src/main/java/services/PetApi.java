package services;

import dto.PetDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/pet";
    private static final String PATH_PET_FIND_BY_STATUS = "/findByStatus";
    private RequestSpecification spec;
    public PetApi(){
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }
    public ValidatableResponse createNewPet(PetDTO petDTO) {

        return given(spec)
                .basePath(BASE_PATH)
                .body(petDTO)
                .log().all()
                .when()
                .post()
                .then()
                .log().all();
    }

    public ValidatableResponse findPetByStatus(String status) {
        return given(spec)
            .queryParam("status", status)
            .basePath(BASE_PATH + PATH_PET_FIND_BY_STATUS)
            .log().all()
            .when()
            .get()
            .then()
            .log().all();
    }
}
