package scouter.plugin.server.alert.integration.common.sender.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import scouter.plugin.server.alert.integration.slack.SlackTask;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author yj.seo on 2019. 06. 18.
 */
public class DefaultHttpSenderTest {

    @Test
    public void post() throws IOException {
        String url = "https://webhook.site/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241";

        DefaultHttpSender httpSender = new DefaultHttpSender();
        httpSender.post(url, "{}");
    }

    @Test
    public void post_givenBadUrl_thenUnknownHostException() throws IOException {
        String url = "https://webhook.site/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241-aaaaaaa";

        ResponseErrorHandler errorHandler = mock(ResponseErrorHandler.class);
        when(errorHandler.hasError(any(HttpResponse.class))).thenReturn(true);

        DefaultHttpSender httpSender = new DefaultHttpSender();
        httpSender.setErrorHandler(errorHandler);
        httpSender.post(url, "{}");

        verify(errorHandler).handleError(any(HttpResponse.class));
    }

    @Test(expected = UnknownHostException.class)
    public void post_givenUnknownHost_thenUnknownHostException() throws IOException {
        String url = "https://webhook.sit/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241";

        DefaultHttpSender httpSender = new DefaultHttpSender();
        httpSender.post(url, "{}");
    }

    @Test(expected = ClientProtocolException.class)
    public void post_givenNoProtocol_thenClientProtocolException() throws IOException {
        DefaultHttpSender httpSender = new DefaultHttpSender();
        httpSender.post("google.com", "{}");
    }
}