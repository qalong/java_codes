package okhttp;

import java.io.IOException;

import javax.script.ScriptException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpTest {
	private static OkHttpClient client = new OkHttpClient();

	public static void main(String[] args) throws ScriptException, IOException {
		String jsessionid=getJSessionId();
		login(jsessionid);
		getData(jsessionid);
	}
		
	
	public static String getJSessionId() throws IOException{
		 String url = "http://www.sditds.cn:8088/sdeportts/website/login.jsp";
		 Request request = new Request.Builder().url(url).get().build();
		
		 Response response = client.newCall(request).execute();
		
		 System.out.println("Message:" + response.message());
		 for (String name : response.headers().names()) {
			 System.out.println(name+":"+response.headers().get(name));
		 }
		 String result = response.body().string();
		 System.out.println(result);

		 
		 String jsessionid=response.header("Set-Cookie");
		 System.out.println(jsessionid);
		 return jsessionid;
	}
	public static void login(String jsessionid) throws IOException{
		 String url = "http://www.sditds.cn:8088/sdeportts/loginAction.do";
		 FormBody body = new FormBody.Builder()
				 .add("loginName", "WHBCofficer")
				 .add("password", "ED73BEC7E4AB81D8D0F71026DA777ADF")
				 .add("verification_code",	"Jtle")
				 .build();
		 Request request = new Request.Builder().url(url)
				 .header("Cookie", jsessionid)
		 .post(body).build();
		
		 Response response = client.newCall(request).execute();
		
		 System.out.println("Message:" + response.message());
		
		 for (String name : response.headers().names()) {
			 System.out.println(name+":"+response.headers().get(name));
		 }
		
		 String result = response.body().string();
		 System.out.println(result);
	}
		 
		 
		 
	public static void getData(String jsessionid) throws IOException{
		String url = "http://www.sditds.cn:8088/sdeportts/manage/tradeStatistic.action?act=statisticlist&analysisQO.export_date=2017-01-10&analysisQO.export_date_right=2017-01-12";
		FormBody body = new FormBody.Builder()
				.add("page", "1")
				.add("rows", "1000")
				.build();
		Request request = new Request.Builder().url(url)
				.header("Cookie", jsessionid)
				.post(body).build();

		Response response = client.newCall(request).execute();

		System.out.println("Message:" + response.message());

		for (String string : response.headers().names()) {
			System.out.println(response.headers().get(string));
		}

		String result = response.body().string();
		System.out.println(result);
	}
}
