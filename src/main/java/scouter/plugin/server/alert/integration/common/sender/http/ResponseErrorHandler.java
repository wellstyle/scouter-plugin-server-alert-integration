package scouter.plugin.server.alert.integration.common.sender.http;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * @author yj.seo on 2019. 06. 14.
 */
public interface ResponseErrorHandler {

    boolean hasError(HttpResponse response) throws IOException;

    void handleError(HttpResponse response) throws IOException;
}
