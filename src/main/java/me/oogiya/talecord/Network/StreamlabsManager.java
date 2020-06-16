package me.oogiya.talecord.Network;

import io.socket.client.IO.Options;
import io.socket.client.Socket;
import me.oogiya.talecord.Main;

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
		// TODO Auto-generated method stub
		
	}

}
