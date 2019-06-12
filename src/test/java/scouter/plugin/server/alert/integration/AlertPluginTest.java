package scouter.plugin.server.alert.integration;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;

import java.util.Date;

public class AlertPluginTest {
    private MonitoringGroupConfigure groupConf;
    private AlertPack pack;
    private String objType = "app";

    @Before
    public void setup() {
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
    public void alert() throws InterruptedException {
        AlertPlugin alertPlugin = new AlertPlugin();
        alertPlugin.alert(pack);
        Thread.sleep(3000);
    }

}
