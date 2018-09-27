package br.com.caelum.cadastro.service;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebClient {

    public String fetchMean(String json){

        OkHttpClient client = new OkHttpClient();

        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, json);

        Request request = new Request.Builder()
                .url("https://www.caelum.com.br/mobile")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return  e.getLocalizedMessage();
        }

    }
}
