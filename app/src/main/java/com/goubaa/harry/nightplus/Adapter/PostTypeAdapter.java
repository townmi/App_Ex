package com.goubaa.harry.nightplus.Adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.goubaa.harry.nightplus.Models.Post;

import java.lang.reflect.Type;

public class PostTypeAdapter implements JsonDeserializer<Post> {
  @Override
  public Post deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (json.isJsonObject()) {
      Gson gson = new Gson();
      return gson.fromJson(json, Post.class);
    }
    return null;
  }
}
