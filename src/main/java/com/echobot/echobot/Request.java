package com.echobot.echobot;

import org.apache.http.client.fluent.Content;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Request {
    CloseableHttpClient httpclient = HttpClients.createDefault();
public void test() throws IOException {

	final Content postResult = org.apache.http.client.fluent.Request.Post("https://api.vk.com/method/messages.send?user_id=74598334&random_id=1000011&domain=naomh_padraig&chat_id=11&message=test+test+test&dont_parse_links=0&disable_mentions=0&access_token=vk1.a.r51Hp0lRNo-tj0Lb0hUp579lVnlVRDtfmGmbX-GUthgBM9mCcP5xQYV2jtQQQ2q7qA6Gud2XuNBRYX6NM4ETtFd1uc-GnB7nI1o9wYMULuuImcnphPfYDcOIxbHXNKLwD1iaG6V4QVtZXG24RB6v2DKjwLiFEKiZvp-WqrIIU9cGeUsVJi5F3fat7g12rImqduXzEAStdIZ6XZhhROkRBw&v=5.131")
			//.bodyString("{'user_id':'74598334','random_id':'100001','domain':'naomh_padraig','chat_id':11,'message':'Vlad i Kot','dont_parse_links':0,'disable_mentions':0,'access_token':vk1.a.r51Hp0lRNo-tj0Lb0hUp579lVnlVRDtfmGmbX-GUthgBM9mCcP5xQYV2jtQQQ2q7qA6Gud2XuNBRYX6NM4ETtFd1uc-GnB7nI1o9wYMULuuImcnphPfYDcOIxbHXNKLwD1iaG6V4QVtZXG24RB6v2DKjwLiFEKiZvp-WqrIIU9cGeUsVJi5F3fat7g12rImqduXzEAStdIZ6XZhhROkRBw,'v':5.131}", ContentType.APPLICATION_JSON)
			.execute().returnContent();
	System.out.println(postResult.asString());
}

}
