package ru.mts.jsonSerialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.mts.model.AbstractAnimal;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

public class AbstractAnimalSerialize extends JsonSerializer<AbstractAnimal> {

    @Override
    public void serialize(AbstractAnimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
    }
}
