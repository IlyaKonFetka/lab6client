package org.example.managers;

import com.google.gson.*;
import org.example.TCP_components.Response;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPSerializationManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    public String serialize(Serializable a){
        return gson.toJson(a);
    }
    public Response response(String stringResponse) throws JsonSyntaxException {
        return gson.fromJson(stringResponse,Response.class);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // "yyyy-mm-dd"
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, Type type,
                                         JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
