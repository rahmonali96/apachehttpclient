package main;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class App {
    public static void main(String[] args) {
        String url = "";
        String token1086 = "";
        String token981 = "";
        String h1 = "Authorization";
        String h2 = "Bearer " + token981;
//        h2 = h2.concat("test");
        String body = "{\"l1\": \"2022-02-02\",\"l2\":\"2022-02-02\"}";
        HttpUriRequest httpPost = RequestBuilder.post(url).addHeader(new BasicHeader(h1, h2)).setEntity(new StringEntity(body, ContentType.APPLICATION_JSON)).build();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            InputStream inputStream = response.getEntity().getContent();
            Files.copy(inputStream, Paths.get("1.xlsx"), StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
            response.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
