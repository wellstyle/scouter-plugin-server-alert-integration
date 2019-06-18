package scouter.plugin.server.alert.integration.telegram;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.common.MonitoringGroupConfigure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author yj.seo on 2019. 06. 11.
 */
public class TelegramTaskTest {

    private static final String MESSAGE_TEMPLATE = "app01 is not running. OBJECT  objType=tomcat objHash=z1d6f0r6 objName=app01 addr=127.0.0.1";

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;
    private String objType = "tomcat";

    @Before
    public void setUp() {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);

        alertPack.objType = "scouter";
        alertPack.level = 0;
        alertPack.title = "An object has been inactivated.";
        alertPack.message = MESSAGE_TEMPLATE;

        when(groupConfigure.isTrace()).thenReturn(true);
    }

    @Test
    public void run() {
        when(groupConfigure.getValue(TelegramTask.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType)).thenReturn("857756898:AAF9sWTKAw9_Bhacv9S_yDpmmXQ5qPn27As");
        when(groupConfigure.getValue(TelegramTask.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType)).thenReturn("@ScouterAlertDemoChannel");

        TelegramTask telegramTask = new TelegramTask(groupConfigure, alertPack);
        telegramTask.run();
    }

    @Test
    public void run_givenBadChatId_ThenErrorLogging() {
        when(groupConfigure.getValue(TelegramTask.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType)).thenReturn("857756898:AAF9sWTKAw9_Bhacv9S_yDpmmXQ5qPn27As");
        when(groupConfigure.getValue(TelegramTask.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType)).thenReturn("@asldfjasldfjslkdjfskjfds");

        TelegramTask telegramTask = new TelegramTask(groupConfigure, alertPack);
        telegramTask.run();
    }

    @Test(expected = AssertionError.class)
    public void test_emptyToken_ThenAssertionError() {
        when(groupConfigure.getValue(TelegramTask.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType)).thenReturn("");
        when(groupConfigure.getValue(TelegramTask.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType)).thenReturn("@ScouterAlertDemoChannel");

        TelegramTask telegramTask = new TelegramTask(groupConfigure, alertPack);
        telegramTask.run();
    }
}