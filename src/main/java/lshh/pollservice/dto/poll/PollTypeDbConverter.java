package lshh.pollservice.dto.poll;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PollTypeDbConverter implements AttributeConverter<PollType, String> {
    @Override
    public String convertToDatabaseColumn(PollType pollType) {
        return pollType.name();
    }

    @Override
    public PollType convertToEntityAttribute(String s) {
        return PollType.valueOf(s);
    }
}
