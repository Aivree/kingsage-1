package pl.piomin.kingsage.machine.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import pl.piomin.kingsage.machine.model.Mission;

public class Sender {

	public void send(Mission mission) throws IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://kingsage.pl/index.php?s=main&a=login&p=0d83");
		            
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cookie", "1"));
		nvps.add(new BasicNameValuePair("passwd", "piot123"));
		nvps.add(new BasicNameValuePair("server_id", "17"));
		nvps.add(new BasicNameValuePair("user", "piomin"));
		        
		post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		        
		client.getCookieStore().addCookie(new BasicClientCookie("user_id", "632628"));
		       
		HttpResponse response = client.execute(post);
		System.out.println(response.getStatusLine());
		        
		EntityUtils.consume(response.getEntity());
		        
		HttpGet get = new HttpGet("http://s17.kingsage.pl/game.php?a=login&p=0d83&user=piomin&pass=4ab544b2ab7442ed55ef324fbb847697");
		        
		client.getCookieStore().addCookie(new BasicClientCookie("game_hash", "fadce4db15b80253e6fb995b38834df5"));
		client.getCookieStore().addCookie(new BasicClientCookie("KingsAge-Game", "qf84ra1vnf3hkhvd8eomi9icg6"));
		        
		response = client.execute(get);
		System.out.println(response.getStatusLine());
		
		EntityUtils.consume(response.getEntity());
		
		post = new HttpPost("http://s17.kingsage.pl/game.php?village=" + mission.getArmy().getVillage().getKingsageId() + "&s=build_barracks&m=command&sub=send");
		post.setEntity(new UrlEncodedFormEntity(mission.prepare("")));
		response = client.execute(post);
		System.out.println(response.getStatusLine());
		String content = Parser.inputToString(response.getEntity().getContent());
		
		EntityUtils.consume(response.getEntity());
				
		Source source = new Source(content);
		Element el = source.getFirstElement("form");
		System.out.println(el.getAttributeValue("action"));
		
		post = new HttpPost("http://s17.kingsage.pl/" + el.getAttributeValue("action"));
		post.setEntity(new UrlEncodedFormEntity(mission.prepare("Atak")));
		response = client.execute(post);
		System.out.println(response.getStatusLine());
		content = Parser.inputToString(response.getEntity().getContent());
		
		System.out.println("Wys³ano do -> " + mission.getDestination().getX() + ", " + mission.getDestination().getY());
	}
}
