package scouter.plugin.server.alert.integration;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;

import java.util.Date;

import static org.mockito.Mockito.*;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({MonitoringGroupConfigure.class, Configure.class})
public class AlertPluginTests {
//    private MonitoringGroupConfigure groupConf;
    private AlertPack pack;
    private String objType = "app";

    @Before
    public void setup() {
//        System.setProperty("scouter.config", "classpath:scouter.conf");
        System.setProperty("scouter.config", "./src/test/resources/scouter.conf");

        pack = new AlertPack();
        pack.level = 1;
        pack.objType = "scouter";
        pack.time = new Date().getTime();
        pack.objHash = 0;
        pack.title = "An object has been inactivated.";
        pack.message = "/app-018c7d617462955c5/app-018c7d617462955c5 is not running. OBJECT  objType=" +
            objType +
            " objHash=z1d6f0r6 objName=/app-018c7d617462955c5/app-018c7d617462955c5 addr=10.40.202.120 2.5.1 2018-12-16 08:55 GMT_ENV_java8plus 2019-06-05 02:50:08.02 {counter=0,detected=tomcat,ADC=false}";
    }

    @Test
    public void alert() {
//        Configure conf = mock(Configure.class);
//
//        when(conf.getBoolean("ext_plugin_slack_send_alert", false)).thenReturn(true);
//        when(conf.getInt("ext_plugin_slack_level", 0)).thenReturn(0);
//        when(conf.getValue("ext_plugin_slack_webhook_url")).thenReturn("http://webhook.example.com/tomcat");

//        MonitoringGroupConfigure groupConf = new MonitoringGroupConfigure(conf);
//        MonitoringGroupConfigure spy = spy(groupConf);
//        MonitoringGroupConfigure mock = mock(MonitoringGroupConfigure.class);
//        whenNew(MonitoringGroupConfigure.class).withArguments(conf).thenReturn(spy);

//        String objectType = "scouter";
//
//        when(mock.getBoolean("ext_plugin_slack_send_alert", objectType, false)).thenReturn(true);
//        when(mock.getInt("ext_plugin_slack_level", objectType, 0)).thenReturn(0);
//        when(mock.getValue("ext_plugin_slack_webhook_url", objectType)).thenReturn("http://webhook.example.com/tomcat");

        AlertPlugin alertPlugin = new AlertPlugin();
        alertPlugin.alert(pack);

//        verify(mock).getBoolean("ext_plugin_slack_send_alert", objectType, false);
    }

}
