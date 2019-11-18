package cc.darhao.undertow_demo.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import cc.darhao.pasta.Pasta;

@ServerEndpoint("/chatServer.ws")
public class WebSocketServerEndpoint {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("用户连接WS，Session的ID为" + session.getId());
	}


	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println("ID为 " + session.getId() + " 的客户端Session发送信息，信息为 " + message);
		try {
			Pasta.receiveMessage(session, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@OnClose
	public void onClose(Session session) {
		System.out.println("ID为 " + session.getId() + " 的客户端Session断开WS");
	}


	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("ID为 " + session.getId() + " 的客户端Session发生错误，信息为 " + error.getMessage());
	}

}