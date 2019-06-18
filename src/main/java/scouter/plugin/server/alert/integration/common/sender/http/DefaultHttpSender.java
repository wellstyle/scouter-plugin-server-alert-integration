package scouter.plugin.server.alert.integration.common.sender.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import scouter.plugin.server.alert.integration.common.AlertLogger;

import java.io.IOException;

/**
 * @author yj.seo on 2019. 06. 14.
 */
public class DefaultHttpSender implements HttpSender {

    private final AlertLogger logger = new AlertLogger();
    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public void post(String url, String payload) throws IOException {

        HttpPost post = new HttpPost(url);

        post.addHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(payload, "utf-8"));

        CloseableHttpClient client = HttpClientBuilder.create().build();

        // send the post request
        HttpResponse response = client.execute(post);
        handleResponse(url, response);
    }

    public void setErrorHandler(ResponseErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    private void handleResponse(String url, HttpResponse response) throws IOException {
        if (errorHandler.hasError(response)) {
            logger.println("Failed to send message. Invoking error handler. Verify below information.");
            logger.println("[Request] POST " + url);
            logger.println("[Response Status] : " + response.getStatusLine());
            logger.println("[Response Body] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
            errorHandler.handleError(response);
        } else {
            logger.println("Message sent to [" + url + "] successfully.");
        }
    }

}
