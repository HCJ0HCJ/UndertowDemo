package cc.darhao.undertow_demo.ws.container;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.Session;

/**会话容器
 * @author   HCJ
 * @date     2019年11月8日 上午11:19:00
 */
public class SessionBox {

	private static final Map<String, Session> sessionMap = new HashMap<>();


	public static synchronized Session getSessionById(String id) {
		return sessionMap.get(id);
	}


	public static synchronized String getIdBySession(Session session) {
		for (Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
			if (sessionEntry.getValue().equals(session)) {
				return sessionEntry.getKey();
			}
		}
		return null;
	}


	public static synchronized void addSession(String id, Session session) {
		sessionMap.put(id, session);
	}


	public static synchronized void removeSession(Session session) {
		sessionMap.remove(getIdBySession(session));
	}


	public static synchronized void sendMessage(String message, String nickName) {
		for (Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
			if (!sessionEntry.getKey().equals(nickName) && sessionEntry.getValue().isOpen()) {
				try {
					sessionEntry.getValue().getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
