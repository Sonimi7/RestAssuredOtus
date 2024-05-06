package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.List;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDTO implements Serializable {
	private long id;
	private CategoryDTO category;
	private String name;
	private List<String> photoUrls;
	private List<TagsDTO> tags;
	private String status;
}