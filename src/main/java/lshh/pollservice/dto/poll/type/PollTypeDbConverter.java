package lshh.pollservice.dto.poll.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PollTypeDbConverter implements AttributeConverter<PollOptionType, String> {
    @Override
    public String convertToDatabaseColumn(PollOptionType pollOptionType) {
        return pollOptionType.name();
    }

    @Override
    public PollOptionType convertToEntityAttribute(String s) {
        return PollOptionType.valueOf(s);
    }
}
