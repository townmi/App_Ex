/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.goubaa.harry.nightplus.Adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.goubaa.harry.nightplus.Models.ExproleBanner;

import java.lang.reflect.Type;

/**
 * Created by harry on 2018/2/27.
 */

public class ExploreBannerAdapter implements JsonDeserializer<ExproleBanner> {
  @Override
  public ExproleBanner deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if(json.isJsonObject()) {
      Gson gson = new Gson();
      return gson.fromJson(json, ExproleBanner.class);
    }
    return null;
  }
}
