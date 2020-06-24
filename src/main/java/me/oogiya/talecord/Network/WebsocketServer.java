package me.oogiya.talecord.Network;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import me.oogiya.talecord.DiscordHandler;

public class WebsocketServer extends WebSocketServer{

	public static WebsocketServer server;
	
	public WebsocketServer(int port) {
		super(new InetSocketAddress(port));
	}
	
	public WebsocketServer(InetSocketAddress address) {
		super(address);
	}
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		DiscordHandler.sendMessage(conn.getRemoteSocketAddress().getAddress().getHostName() + " has connected to ws");
		conn.send("hello");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		DiscordHandler.sendMessage("Received message from client: " + message + " From: " + conn.getRemoteSocketAddress().getAddress().getHostName());
	}

	public static void runServer() {
		int port = 25564;
		server = new WebsocketServer(port);
		server.start();
		DiscordHandler.sendMessage("Created a Websocket Server");
	}
	
	@Override
	public void onError(WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	
	
}
