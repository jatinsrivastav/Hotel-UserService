package Hotel.UserService.Converter;

import Hotel.UserService.RestDTO.BookingDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class BookingDTOListConverter implements AttributeConverter<List<BookingDTO>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<BookingDTO> bookingDTOS) {
        try {
            return objectMapper.writeValueAsString(bookingDTOS);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list of BookingDTOs to JSON", e);
        }
    }

    @Override
    public List<BookingDTO> convertToEntityAttribute(String bookingDTOJson) {
        try {
            return objectMapper.readValue(bookingDTOJson, objectMapper.getTypeFactory().constructCollectionType(List.class, BookingDTO.class));
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to list of BookingDTOs", e);
        }
    }
}

