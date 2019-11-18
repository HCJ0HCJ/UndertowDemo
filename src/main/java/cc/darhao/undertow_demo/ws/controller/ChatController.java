package cc.darhao.undertow_demo.ws.controller;

import javax.websocket.Session;

import com.jfinal.plugin.redis.Redis;

import cc.darhao.undertow_demo.entity.Constant;
import cc.darhao.undertow_demo.util.ResultUtil;
import cc.darhao.undertow_demo.ws.container.SessionBox;

/**聊天处理器
 * @author   HCJ
 * @date     2019年11月11日 下午4:45:20
 */
public class ChatController {

	public ResultUtil chat(Session session, String message) {
		String nickName = SessionBox.getIdBySession(session);
		if (nickName == null) {
			return ResultUtil.failed(412, "用户未登录");
		}
		SessionBox.sendMessage("用户： " + nickName + " ，说了： " + message, nickName);
		Redis.use().rpush(Constant.REDIS_MESSAGE_KEY, "用户： " + nickName + " ，说了： " + message);
		return ResultUtil.succeed("信息接收成功");
	}
}
