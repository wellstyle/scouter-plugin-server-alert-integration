package scouter.plugin.server.alert.integration;

/**
 * @author yj.seo on 2019. 06. 10.
 */
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String handlerName;

    public ThreadExceptionHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(handlerName + " caught exception in thread - "
            + t.getName() + " => " + e);
    }
}
