package pl.edu.tirex.lvlup.api.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SkipArrayAdapterFactory implements TypeAdapterFactory
{
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken)
    {
        TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, typeToken);
        TypeAdapter<JsonElement> adapter = gson.getAdapter(JsonElement.class);
        return (TypeAdapter<T>) new SkipArrayFactory((TypeAdapter<Object>) delegateAdapter, adapter);
    }

    public class SkipArrayFactory extends TypeAdapter<Object>
    {
        private final TypeAdapter<Object> defaultAdapter;
        private final TypeAdapter<JsonElement> adapter;

        public SkipArrayFactory(TypeAdapter<Object> defaultAdapter, TypeAdapter<JsonElement> elementAdapter)
        {
            this.defaultAdapter = defaultAdapter;
            this.adapter = elementAdapter;
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException
        {
            defaultAdapter.write(out, value);
        }

        @Override
        public Object read(JsonReader in) throws IOException
        {
            if (in.peek() == JsonToken.BEGIN_ARRAY)
            {
                try
                {
                    return defaultAdapter.read(in);
                }
                catch (JsonSyntaxException ignored)
                {
                    Object object = null;
                    in.beginArray();
                    if (in.peek() == JsonToken.BEGIN_OBJECT)
                    {
                        object = defaultAdapter.read(in);
                    }
                    in.endArray();
                    return object;
                }
            }
            JsonElement element = this.adapter.read(in);
            if (element == null)
            {
                return null;
            }
            return defaultAdapter.fromJsonTree(element);
        }
    }
}