package dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagsDTO implements Serializable {
	private long id;
	private String name;

}