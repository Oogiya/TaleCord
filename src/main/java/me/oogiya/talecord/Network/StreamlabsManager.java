package me.oogiya.talecord.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO.Options;
import io.socket.client.Socket;
import me.oogiya.talecord.Main;
import me.oogiya.talecord.Utils.JSONUtils;

public class StreamlabsManager extends SocketManager {

	private boolean isConnected = false;
	
	public StreamlabsManager(String platform) {
		super(platform);
		
		start();
	}

	private void start() {
		
		Socket socket = this.createSocket();
		
		socket.connect();
	}
	
	public void stop() {
		socket.disconnect();
	}
	
	public boolean isConnect() {
		return this.isConnected;
	}
	
	@Override
	protected Options createOptions() {
		
		Options opts = super.createOptions();
		opts.query = "token=" + this.getToken();
		return opts;
		
	}
	
	@Override
	protected void onConnect(Object... args) {
		// TODO Auto-generated method stub
		this.isConnected = true;
		Main.getPlugin().getLogger().info("Connected");
		
	}

	@Override
	protected void onDisconnect(Object... args) {
		// TODO Auto-generated method stub
		this.isConnected = false;
		Main.getPlugin().getLogger().info("Disconnected");
	}

	@Override
	protected void onEvent(Object... args) {
		JSONObject event = (JSONObject)args[0];
		JSONArray messages = JSONUtils.extractMessages(event);
		
		if (messages == null) {
			Main.getPlugin().getLogger().info("Bug onEvent");
			return;
		}
		
		String eventType = JSONUtils.extractJSON(event, "type", String.class, null);
		
		if (eventType.equals("follow")) {
			
			try {
				JSONObject msg = messages.getJSONObject(0);
				Main.getPlugin().getLogger().info("new follower: " + msg.getString("name"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
}
