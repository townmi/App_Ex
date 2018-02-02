package com.goubaa.harry.nightplus.Adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.goubaa.harry.nightplus.Models.User;

import java.lang.reflect.Type;

/**
 * Created by harry on 2018/2/2.
 */

public class UserTypeAdapter implements JsonDeserializer<User> {
  @Override
  public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if(json.isJsonObject()) {
      Gson gson = new Gson();
      return gson.fromJson(json, User.class);
    }
    return null;
  }
}
