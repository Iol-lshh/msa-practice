package lshh.core.lib.type;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Registration {
	String guid;
	LocalDateTime registeredDateTime;
	LocalDateTime updatedDateTime;

	public static Registration from(String guid) {
		LocalDateTime dateTime = LocalDateTime.now();
		return Registration.builder()
			.guid(guid)
			.registeredDateTime(dateTime)
			.updatedDateTime(dateTime)
			.build();
	}

	public void update(String guid) {
		this.guid = guid;
		this.updatedDateTime = LocalDateTime.now();
	}
}
