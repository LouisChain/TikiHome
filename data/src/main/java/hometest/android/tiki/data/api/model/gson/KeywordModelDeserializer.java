package hometest.android.tiki.data.api.model.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import hometest.android.tiki.data.api.model.ApiKeyword;

public class KeywordModelDeserializer implements JsonDeserializer<ApiKeyword> {
    @Override
    public ApiKeyword deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        ArrayList<String> data = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                data.add(jsonArray.get(i).getAsString());
            }
        }
        return new ApiKeyword(data);
    }
}
