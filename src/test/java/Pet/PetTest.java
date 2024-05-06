package Pet;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.SoftAssertions;
import dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.PetApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PetTest {
/**
 * check name and status pet*/
    @Test
    public void createPet(){

        List<TagsDTO> tags = new ArrayList<>();
        List<String> photoUrls = Collections.singletonList("uuu");

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1)
                .name("grey")
                .build();

        TagsDTO tagsDTO = TagsDTO.builder()
                .id(1)
                .name("grey")
                .build();

        tags.add(tagsDTO);

        PetApi petApi = new PetApi();

        PetDTO petDTO = PetDTO.builder()
                .id(100)
                .category(categoryDTO)
                .name("Fisa")
                .photoUrls(photoUrls)
                .tags(tags)
                .status("happy")
                .build();

        petApi.createNewPet(petDTO)
                .statusCode(200);

        PetResponseDTO actualPet = petApi.createNewPet(petDTO).extract().body().as(PetResponseDTO.class);

        assertEquals("Fisa", actualPet.getName());
        assertEquals("happy", actualPet.getStatus());

    }

/**
* check category and tags pet*/
    @Test
    public void checkPet(){

        List<TagsDTO> tags = new ArrayList<>();
        List<String> photoUrls = Collections.singletonList("uuu");

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1)
                .name("grey")
                .build();

        TagsDTO tagsDTO = TagsDTO.builder()
                .id(1)
                .name("grey")
                .build();

        tags.add(tagsDTO);

        PetApi petApi = new PetApi();

        PetDTO petDTO = PetDTO.builder()
                .id(100)
                .category(categoryDTO)
                .name("Fisa")
                .photoUrls(photoUrls)
                .tags(tags)
                .status("happy")
                .build();

        petApi.createNewPet(petDTO)
                .statusCode(200);

        PetResponseDTO actualPet = petApi.createNewPet(petDTO).extract().body().as(PetResponseDTO.class);

        assertEquals(categoryDTO.getName(), actualPet.getCategory().getName());
        assertEquals(tagsDTO.getName(), actualPet.getTags().get(0).getName());

    }

/**
* check find pet by status*/
    @Test
    public void findPetById() {
        PetApi petApi = new PetApi();

        petApi.findPetByStatus("available").statusCode(200);

        PetResponseDTO[] petResponseDTOArray = petApi.findPetByStatus("available").extract().body().as(PetResponseDTO[].class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (PetResponseDTO pet : petResponseDTOArray) {
            softAssertions.assertThat(pet.getStatus()).isEqualTo("available").as("Status is not 'available' for pet with ID " + pet.getId());
        }

        softAssertions.assertAll();
    }

/**
* check find pet by is not status*/
    @Test
    public void findPetByIdIsFalseParameters() {
        PetApi petApi = new PetApi();

        petApi.findPetByStatus("sold").statusCode(200);

        PetResponseDTO[] petResponseDTOArray = petApi.findPetByStatus("sold").extract().body().as(PetResponseDTO[].class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (PetResponseDTO pet : petResponseDTOArray) {
            softAssertions.assertThat(pet.getStatus()).isNotEqualTo("available").as("Status is not 'available' for pet with ID " + pet.getId());
        }

        softAssertions.assertAll();
    }
}
