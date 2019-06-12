package scouter.plugin.server.alert.integration.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * @author yj.seo on 2019. 06. 11.
 */
public class RestClient {

    private RestClient() {
        throw new IllegalStateException("utility class");
    }

    public static HttpResponse post(String url, String payload) throws IOException {

        HttpPost post = new HttpPost(url);

        post.addHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(payload, "utf-8"));

        CloseableHttpClient client = HttpClientBuilder.create().build();

        // send the post request
        return client.execute(post);
    }
}
