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
 *  @author Sang-Cheon Park
 */
package scouter.plugin.server.alert.integration.telegram;

import com.google.gson.annotations.SerializedName;

/**
 * Alert message class to send alert via telegram
 *
 * @author Sang-Cheon Park(nices96@gmail.com) on 2016. 3. 28.
 * @author yj.seo on 2019. 6. 11.
 */
public class TelegramMessage {

    @SerializedName("chat_id")
    private String chatId;
    @SerializedName("text")
    private String text;

    public TelegramMessage(String chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

}