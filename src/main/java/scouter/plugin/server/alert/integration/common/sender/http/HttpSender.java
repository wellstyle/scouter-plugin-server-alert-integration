package scouter.plugin.server.alert.integration.common.sender.http;

import java.io.IOException;

/**
 * @author yj.seo on 2019. 06. 14.
 */
public interface HttpSender {

    public void post(String url, String payload) throws IOException;
}
