package cc.darhao.undertow_demo.ws.controller;

import javax.websocket.Session;

import com.jfinal.kit.StrKit;

import cc.darhao.undertow_demo.util.ResultUtil;
import cc.darhao.undertow_demo.ws.container.SessionBox;

/**登录处理器
 * @author   HCJ
 * @date     2019年11月11日 下午4:45:56
 */
public class LoginController {

	public ResultUtil login(Session session, String nickName) {
		if (StrKit.isBlank(nickName)) {
			return ResultUtil.failed(412, "昵称不能为空");
		}
		String name = SessionBox.getIdBySession(session);
		if (name != null) {
			return ResultUtil.failed(412, "请勿重复登录");
		}
		SessionBox.addSession(nickName, session);
		SessionBox.sendMessage(nickName + " 上线", nickName);
		return ResultUtil.succeed("登录成功");
	}
}
