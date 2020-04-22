/*
 *  Copyright 2016 Scouter Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 *  
 *  @author Gookeun Lim
 */
package scouter.plugin.server.alert.integration.common;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import scouter.server.Configure;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit Test For MonitoringGroupConfigure With scouter.conf
 *  
 * @author yj.seo on 2018.07.24
 */
public class MonitoringGroupConfigureFileTest {
	MonitoringGroupConfigure groupConf;

	@BeforeClass
	public static void setup() {
		System.setProperty("scouter.config", "./src/test/resources/scouter.conf");
	}

	@Before
	public void init() {
		groupConf = new MonitoringGroupConfigure(Configure.getInstance());
	}

	@Test
	public void test_getBoolean() {
		String objType = "group-1";

		assertTrue(groupConf.getBoolean("ext_plugin_alert_xlog_enabled", objType, false));
	}

	@Test
	public void test_getValue() {
		String objType = "group-1";
		String objTypeNotDefined = "no-define";

		assertNull(groupConf.getValue("not_found", objType, null));
		assertNotNull(groupConf.getValue("ext_plugin_alert_teams_webhook_url", objType, null));

		assertEquals("https://outlook.office365.com/webhook/value1@value2/IncomingWebhook/value3/group1",
			groupConf.getValue("ext_plugin_alert_teams_webhook_url", objType, null));
		assertEquals("https://outlook.office365.com/webhook/value1@value2/IncomingWebhook/value3/value4",
			groupConf.getValue("ext_plugin_alert_teams_webhook_url", objTypeNotDefined, null));
	}

}
