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
package scouter.plugin.server.alert.integration.teams;

import com.google.gson.annotations.SerializedName;
import scouter.lang.AlertLevel;
import scouter.lang.pack.AlertPack;
import scouter.server.Configure;

import java.util.ArrayList;
import java.util.List;

/**
 * Alert message class to send alert via Teams
 * 
 * @author yj.seo on 2020. 4. 20.
 *
 * reference: https://docs.microsoft.com/ko-kr/outlook/actionable-messages/message-card-reference
 */
class TeamsMessage {
	@SerializedName("@type")
	private String type = "MessageCard";

	@SerializedName("@context")
	private String context = "http://schema.org/extensions";

	@SerializedName("themeColor")
	private String themeColor = "0076D7";

	@SerializedName("summary")
	private String summary = "none";

	@SerializedName("sections")
	private List<Section> sections = new ArrayList<Section>();

	TeamsMessage(AlertPack alertPack, String objType, String objName) {
		String title = String.format("Notification from %s", Configure.getInstance().server_id);
		String subTitle = String.format("on %s - %s", objType, objName);

		List<Fact> facts = new ArrayList<Fact>();
		facts.add(new Fact("Title", alertPack.title));
		facts.add(new Fact("Message", alertPack.message));
		facts.add(new Fact("Level", AlertLevel.getName(alertPack.level)));

		sections.add(new Section(title, subTitle, facts));
	}

	class Section {
		@SerializedName("activityTitle")
		private String activityTitle;

		@SerializedName("activitySubTitle")
		private String activitySubTitle;

		@SerializedName("facts")
        private List<Fact> facts;

		@SerializedName("markdown")
		private boolean markdown = false;

		public Section(String activityTitle, String activitySubTitle, List<Fact> facts) {
			this.activityTitle = activityTitle;
			this.activitySubTitle = activitySubTitle;
			this.facts = facts;
		}
	}

	class Fact {
		@SerializedName("name")
		private String name;

		@SerializedName("value")
		private String value;

		public Fact(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
}
