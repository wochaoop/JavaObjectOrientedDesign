package com.web;

import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

class TestFile {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/octet-stream");
        File file = new File("D:\\Projects\\github.com\\xiwangly2\\AIMLTest\\823f64bb5451f63b605689c27ed8b776.png");
        RequestBody requestBody = RequestBody.create(file, mediaType);
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestBody)
                .build();
        Request request = new Request.Builder()
                .url("https://api.oioweb.cn/api/ocr")
                .method("POST", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}