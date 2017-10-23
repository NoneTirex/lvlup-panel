package pl.edu.tirex.lvlup.api.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class BooleanAdapter implements JsonDeserializer<Boolean>
{
    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        String booleanString = json.getAsString();
        if ("true".equalsIgnoreCase(booleanString) || "1".equals(booleanString))
        {
            return true;
        }
        else if ("false".equalsIgnoreCase(booleanString) || "0".equals(booleanString))
        {
            return false;
        }
        return null;
    }
}
