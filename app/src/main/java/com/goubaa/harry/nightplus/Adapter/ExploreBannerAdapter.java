package com.goubaa.harry.nightplus.Adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.goubaa.harry.nightplus.Models.ExproleBanner;

import java.lang.reflect.Type;

public class ExploreBannerAdapter implements JsonDeserializer<ExproleBanner> {
  @Override
  public ExproleBanner deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
    JsonParseException {
    if (json.isJsonObject()) {
      Gson gson = new Gson();
      return gson.fromJson(json, ExproleBanner.class);
    }
    return null;
  }
}
