package lshh.pollservice.dto.poll;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lshh.pollservice.dto.poll.type.PollOptionType;

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
