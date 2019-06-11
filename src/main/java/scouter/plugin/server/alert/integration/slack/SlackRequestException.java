package scouter.plugin.server.alert.integration.slack;

/**
 * @author yj.seo on 2019. 06. 10.
 */
public class SlackRequestException extends RuntimeException {

    public SlackRequestException(String message) {
        super(message);
    }
}
