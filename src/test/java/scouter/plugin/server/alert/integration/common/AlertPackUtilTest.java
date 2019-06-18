package scouter.plugin.server.alert.integration.common;

import org.junit.Test;
import scouter.lang.pack.AlertPack;

import static org.junit.Assert.assertEquals;

/**
 * @author yj.seo on 2019. 06. 18.
 */
public class AlertPackUtilTest {

    private static final String MESSAGE_TEMPLATE = "app01 is not running. OBJECT  objType=tomcat objHash=z1d6f0r6 objName=app01 addr=127.0.0.1";

    @Test
    public void getObjType() {
        String objType = "tomcat";

        AlertPack alertPack = new AlertPack();
        alertPack.objType = objType;

        String actual = AlertPackUtil.getObjType(alertPack);

        assertEquals(objType, actual);
    }

    @Test
    public void getObjType_givenObjTypeIsScouterAndNormalMessage_thenObjTypeInMessage() {
        String objType = "scouter";
        String expect = "tomcat";

        AlertPack alertPack = new AlertPack();
        alertPack.objType = objType;
        alertPack.message = MESSAGE_TEMPLATE;

        String actual = AlertPackUtil.getObjType(alertPack);

        assertEquals(expect, actual);
    }

    @Test
    public void getObjType_givenObjTypeIsScouterAndAbnormalMessage_thenScouter() {
        String objType = "scouter";

        AlertPack alertPack = new AlertPack();
        alertPack.objType = objType;
        alertPack.message = MESSAGE_TEMPLATE.replaceAll("objType=", "");

        String actual = AlertPackUtil.getObjType(alertPack);

        assertEquals(objType, actual);
    }

    @Test
    public void getObjName_givenObjTypeIsScouterAndNormalMessage_thenObjNameInMessage() {
        String objType = "scouter";
        String expect = "app01";

        AlertPack alertPack = new AlertPack();
        alertPack.objType = objType;
        alertPack.objHash = 100;
        alertPack.message = MESSAGE_TEMPLATE;

        String actual = AlertPackUtil.getObjName(alertPack);

        assertEquals(expect, actual);
    }


}