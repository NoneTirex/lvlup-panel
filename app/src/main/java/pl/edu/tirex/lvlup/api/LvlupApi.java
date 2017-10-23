package pl.edu.tirex.lvlup.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import pl.edu.tirex.lvlup.api.models.LvlupError;
import pl.edu.tirex.lvlup.api.models.auth.LvlupLoginData;
import pl.edu.tirex.lvlup.api.models.auth.LvlupToken;
import pl.edu.tirex.lvlup.api.utils.BooleanAdapter;
import pl.edu.tirex.lvlup.api.utils.SkipArrayAdapterFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class LvlupApi
{
    public static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(new SkipArrayAdapterFactory()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(boolean.class, new BooleanAdapter()).create();

    private static final String API_URL = "https://api.lvlup.pro/v3";

    public static LvlupToken login(String login, String password) throws IOException
    {
        return query("/auth/login", RequestMethod.POST, new LvlupLoginData(login, password), LvlupToken.class);
    }

    public static <T> T query(String url, RequestMethod method, Object payload, Type typeOfT) throws IOException
    {
        return query(url, method, null, payload, typeOfT);
    }

    public static <T> T query(String url, RequestMethod method, String token, Object payload, Type typeOfT) throws IOException
    {
        url = API_URL + url;
        if (method == RequestMethod.GET && payload != null)
        {
            url += "?" + objectToUrl(payload);
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        if (token != null)
        {
            connection.setRequestProperty("Authorization", "Bearer " + token);
        }
        connection.setRequestMethod(method.name());
        connection.setDoInput(true);
        if (method == RequestMethod.POST && payload != null)
        {
            connection.setDoOutput(true);
            connection.getOutputStream().write(GSON.toJson(payload).getBytes(StandardCharsets.UTF_8));
        }

        try
        {
            if (connection.getResponseCode() / 100 != 2)
            {
                throw new LvlupException(connection.getResponseCode(), GSON.fromJson(readString(connection.getErrorStream()), LvlupError.class));
            }
            String string = readString(connection.getInputStream());
            LvlupError error = GSON.fromJson(string, LvlupError.class);
            if (error != null && error.isError())
            {
                throw new LvlupException(connection.getResponseCode(), error);
            }
            return GSON.fromJson(string, typeOfT);
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String readString(InputStream inputStream) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream)))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                sb.append(line);
            }
        }
        System.out.println(sb);
        return sb.toString();
    }

    private static String objectToUrl(Object object)
    {
        JsonElement jsonElement = null;
        if (object instanceof JsonElement)
        {
            jsonElement = (JsonElement) object;
        }
        if (jsonElement == null)
        {
            jsonElement = GSON.toJsonTree(object);
        }
        StringBuilder text = new StringBuilder();
        if (jsonElement.isJsonObject())
        {
            int count = 0;
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet())
            {
                text.append(entry.getKey()).append('=').append(entry.getValue().getAsString());
                if (++count < jsonObject.size())
                {
                    text.append('&');
                }
            }
        }
        return text.toString();
    }

    public static String getJson(Object object)
    {
        return GSON.toJson(object);
    }
}
