package com.test;

import java.util.UUID;

import com.mascloud.sdkclient.Client;

public class Demo {
	public static void main(String[] args) {
		try {
			final Client client =  Client.getInstance();
			// 正式环境IP，登录验证URL，用户名，密码，集团客户名称
			client.login("http://112.35.1.156:18888/cmcmas/app/sdk/login", "jiajy", "jiajy2017","家家悦集团股份有限公司");
//			client.login("http://mas.ecloud.10086.cn/app/sdk/login", "jjycg", "jjycg", "家家悦集团股份有限公司");
			
			// 测试环境IP
			//client.login("http://112.33.1.13/app/sdk/login", "sdk2", "123","光谷信息");
			int sendResult = client. sendDSMS (new String[] {"18660387600"},
					"sdk短信发送内容测试", "",  1,"k7GmVb28", UUID.randomUUID().toString(),true);
			System.out.println("推送结果: " + sendResult);
			
			
			
			
			
			
			
			
//			client.login("http://mas.ecloud.10086.cn/app/sdk/login", "jjycg", "jjycg", "家家悦集团股份有限公司");
//				String uuid = UUID.randomUUID().toString();
//				int sendResult = client.sendDSMS(phone, content, "", 1, "k7GmVb28", uuid, true);
//				System.out.println("推送短信给[" + phone + "]，内容为[" + content + "]，编号为[" + uuid + "]结果: " + sendResult);
			
			for(;;);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
