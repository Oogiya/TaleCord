package me.oogiya.talecord.Network;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.IO.Options;
import io.socket.client.Socket;
import me.oogiya.talecord.Utils.StreamerConfig;

public abstract class SocketManager {

	private String socketToken = "";

	private String platform = "";
	
	Socket socket;

	public SocketManager(String platform) {
		//this.socketToken = socketToken;
		this.platform = platform;
		
		StreamerConfig config = new StreamerConfig();
		this.socketToken = config.getApiToken();
	}

	protected Socket createSocket() {
		
		Options opts = createOptions();
		
		Socket socket;
		try {
			socket = IO.socket(this.platform, opts);
			
			socket.on(Socket.EVENT_CONNECT, args -> onConnect(args));
			socket.on(Socket.EVENT_DISCONNECT, args -> onDisconnect(args));
			socket.on("event", args -> onEvent(args));
			
		} catch (URISyntaxException e) {
			throw new InternalError("Invalid URI");
		}
		
		return socket;
		
	}
	
	protected String getPlatform() {
		return this.platform;
	}
	
	protected String getToken() {
		return this.socketToken;
	}
	
	protected Options createOptions() {
		
		Options opts = new Options();
		opts.forceNew = true;
		opts.transports = new String[] {"websocket"};
		
		return opts;
		
	}
	
	protected abstract void onConnect(Object... args);
	
	protected abstract void onDisconnect(Object... args);
	
	protected abstract void onEvent(Object... args);
	
	

}
