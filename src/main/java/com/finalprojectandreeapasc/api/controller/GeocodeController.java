package com.finalprojectandreeapasc.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectandreeapasc.FinalProjectAndreeaPascApplication;
import com.finalprojectandreeapasc.api.GeocodeResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

import static com.finalprojectandreeapasc.UrlMapping.MAP;

@RestController
public class GeocodeController {

    @RequestMapping(path = MAP, method = RequestMethod.GET )
    public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "dd7563e9a4msh6f4530cf47683d1p17dfdbjsn5ea20aa7f496"/*  Use your API Key here */)
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
        return result;
    }
}