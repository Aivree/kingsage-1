package pl.piomin.kingsage.machine.http;

import java.io.IOException;
import java.text.MessageFormat;
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
import org.apache.log4j.Logger;

import pl.piomin.kingsage.machine.model.Mission;

public class Sender {

	private static final Logger logger = Logger.getLogger(Sender.class);

	private DefaultHttpClient httpClient = new DefaultHttpClient();

	private static Sender instance = null;

	private boolean loggedIn = false;

	public static Sender getInstance() {
		if (instance == null) {
			instance = new Sender();
		}
		return instance;
	}

	private Sender() {

	}

	public synchronized void login() throws IOException {
		HttpPost post = new HttpPost("http://kingsage.pl/index.php?s=main&a=login&p=0d83");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cookie", "1"));
		nvps.add(new BasicNameValuePair("passwd", "piot123"));
		nvps.add(new BasicNameValuePair("server_id", "17"));
		nvps.add(new BasicNameValuePair("user", "piomin"));

		post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

		httpClient.getCookieStore().addCookie(new BasicClientCookie("user_id", "632628"));

		HttpResponse response = httpClient.execute(post);
		logger.info(response.getStatusLine());

		EntityUtils.consume(response.getEntity());

		HttpGet get = new HttpGet("http://s17.kingsage.pl/game.php?a=login&p=0d83&user=piomin&pass=4ab544b2ab7442ed55ef324fbb847697");

		httpClient.getCookieStore().addCookie(new BasicClientCookie("game_hash", "fadce4db15b80253e6fb995b38834df5"));
		httpClient.getCookieStore().addCookie(new BasicClientCookie("KingsAge-Game", "qf84ra1vnf3hkhvd8eomi9icg6"));

		response = httpClient.execute(get);
		logger.info(response.getStatusLine());
		loggedIn = true;

		EntityUtils.consume(response.getEntity());
	}

	public synchronized void logout() throws IOException {
		HttpGet get = new HttpGet("http://s17.kingsage.pl/logout.php");
		HttpResponse response = httpClient.execute(get);
		logger.info(response.getStatusLine());
		loggedIn = false;

		EntityUtils.consume(response.getEntity());
	}

	public synchronized void send(Mission mission) throws IOException {	
		HttpPost post = new HttpPost("http://s17.kingsage.pl/game.php?village=" + mission.getArmy().getVillage().getKingsageId() + "&s=build_barracks&m=command&sub=send");
		post.setEntity(new UrlEncodedFormEntity(mission.prepare("")));
		HttpResponse response = httpClient.execute(post);
		logger.info(response.getStatusLine());
		String content = Parser.inputToString(response.getEntity().getContent());
		
		EntityUtils.consume(response.getEntity());
				
		Source source = new Source(content);
		Element el = source.getFirstElement("form");
		logger.info(el.getAttributeValue("action"));
		
		post = new HttpPost("http://s17.kingsage.pl/" + el.getAttributeValue("action"));
		post.setEntity(new UrlEncodedFormEntity(mission.prepare("Atak")));
		for (int i=0; i<mission.getReplyCount(); i++) {
			response = httpClient.execute(post);
			logger.info(response.getStatusLine());
			EntityUtils.consume(response.getEntity());
		}
		
		logger.info(MessageFormat.format("Wys³ano do -> {0}, {1} ({2})", mission.getDestination().getX(), mission.getDestination().getY(), mission.getReplyCount()));
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

}
