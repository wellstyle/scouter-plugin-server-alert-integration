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
 *  @author Se-Wang Lee
 */
package scouter.plugin.server.alert.integration.slack;

import com.google.gson.annotations.SerializedName;

/**
 * Alert message class to send alert via Slack
 * 
 * @author Se-Wang Lee(ssamzie101@gmail.com) on 2016. 5. 2.
 * @author yj.seo on 2019. 6. 5.
 */
class SlackMessage {
	
	@SerializedName("text")
	private String text;
	@SerializedName("channel")
	private String channel;
	@SerializedName("username")
	private String botName;
	@SerializedName("icon_emoji")
	private String iconEmoji;
	@SerializedName("icon_url")
	private String iconURL;
	
	SlackMessage(String text, String channel, String botName, String iconURL, String iconEmoji) {
		this.text = text;
		this.channel = channel;
		this.botName = botName;
		this.iconURL = iconURL;
		this.iconEmoji = iconEmoji;
	}

}
