package dto;

import lombok.*;

import java.util.List;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO implements Serializable {
	private long id;
	private CategoryDTO category;
	private String name;
	private List<String> photoUrls;
	private List<TagsDTO> tags;
	private String status;

}