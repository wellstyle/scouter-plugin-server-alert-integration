package scouter.plugin.server.alert.integration.telegram;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.common.Properties;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author yj.seo on 2019. 06. 11.
 */
public class TelegramTaskTest {

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;
    private String objType = "app";

    @Before
    public void setUp() {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);

        alertPack.objType = "scouter";
        alertPack.level = 0;
        alertPack.title = "An object has been inactivated.";
        alertPack.message = "/app-018c7d617462955c5/app-018c7d617462955c5 is not running. OBJECT  objType=" + objType
            + " objHash=z1d6f0r6 objName=/app-018c7d617462955c5/app-018c7d617462955c5 addr=127.0.0.1 2.5.1 2018-12-16 08:55 GMT_ENV_java8plus 2019-06-05 02:50:08.02 {counter=0,detected=tomcat,ADC=false}";

        when(groupConfigure.isTrace()).thenReturn(true);
    }

    @Test
    public void run() {
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType)).thenReturn("857756898:AAF9sWTKAw9_Bhacv9S_yDpmmXQ5qPn27As");
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType)).thenReturn("@ScouterAlertDemoChannel");

        TelegramTask telegramTask = new TelegramTask(groupConfigure, alertPack);
        telegramTask.run();
    }

    @Test
    public void anonymousChatId_Then() {
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType)).thenReturn("857756898:AAF9sWTKAw9_Bhacv9S_yDpmmXQ5qPn27As");
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType)).thenReturn("@asldfjasldfjslkdjfskjfds");

        TelegramTask telegramTask = new TelegramTask(groupConfigure, alertPack);
        telegramTask.run();
    }

    @Test(expected = AssertionError.class)
    public void emptyToken_ThenAssertionError() {
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType)).thenReturn("");
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType)).thenReturn("@ScouterAlertDemoChannel");

        TelegramTask telegramTask = new TelegramTask(groupConfigure, alertPack);
        telegramTask.run();
    }
}