package scouter.plugin.server.alert.integration.common;

import org.junit.Test;
import scouter.lang.pack.AlertPack;
import sun.swing.StringUIClientPropertyKey;

import static org.junit.Assert.*;

/**
 * @author yj.seo on 2019. 06. 18.
 */
public class StringUtilTest {

    private static final String MESSAGE_TEMPLATE = "app01 is not running. OBJECT  objType=tomcat objHash=z1d6f0r6 objName=app01 addr=127.0.0.1";

    @Test
    public void getValue() {
        String expectObjName = "app01";
        String expectObjType = "tomcat";
        AlertPack alertPack = new AlertPack();
        alertPack.message = MESSAGE_TEMPLATE;

        String objName = StringUtil.getValue(alertPack.message, "objName");
        String objType = StringUtil.getValue(alertPack.message, "objType");

        assertEquals(expectObjName, objName);
        assertEquals(expectObjType, objType);
    }

    @Test
    public void getValue_givenNoAttrMessage_thenEmptyString() {
        String expectObjName = "app01";
        AlertPack alertPack = new AlertPack();
        alertPack.message = MESSAGE_TEMPLATE.replaceAll("objName=", "objName");

        String objName = StringUtil.getValue(alertPack.message, "objName");

        assertTrue(objName.isEmpty());
    }

    @Test
    public void getValue_givenAbnormalMessage_thenEmptyString() {
        String objectName = StringUtil.getValue("message", "objName");
        String objectType = StringUtil.getValue(null, "objType");

        assertEquals("", objectName);
        assertEquals("", objectType);
    }

    @Test
    public void isEmpty() {
        assertTrue(StringUtil.isEmpty(null));
        assertTrue(StringUtil.isEmpty(""));
        assertFalse(StringUtil.isEmpty("abcd"));
    }

    @Test
    public void contains() {
        String message = "404 : [no body]";
        String text = "404 : ,";

        assertTrue(StringUtil.contains(message, text));

        assertFalse(StringUtil.contains(message, null));
        assertFalse(StringUtil.contains(message, "333,222"));
        assertFalse(StringUtil.contains(message, " 403 : [no body],Bad chunk header "));
    }
}
