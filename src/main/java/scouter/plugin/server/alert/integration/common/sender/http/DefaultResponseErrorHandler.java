package scouter.plugin.server.alert.integration.common.sender.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

/**
 * @author yj.seo on 2019. 06. 14.
 */
public class DefaultResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(HttpResponse response) {
        return response.getStatusLine().getStatusCode() != HttpStatus.SC_OK;
    }

    @Override
    public void handleError(HttpResponse response) {
        // ignore
    }

}
