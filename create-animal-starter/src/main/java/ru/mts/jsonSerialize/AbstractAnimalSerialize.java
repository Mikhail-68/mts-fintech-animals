package ru.mts.jsonSerialize;

//public class AbstractAnimalSerialize extends JsonSerializer<AbstractAnimal> {
//
//    @Override
//    public void serialize(AbstractAnimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//
//        StringWriter writer = new StringWriter();
//        objectMapper.writeValue(writer, value);
//        gen.writeFieldName(writer.toString());
//    }
//}
