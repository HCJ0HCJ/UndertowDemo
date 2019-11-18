package cc.darhao.undertow_demo.thread;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import cc.darhao.pasta.Pasta;
import cc.darhao.undertow_demo.controller.FileController;
import cc.darhao.undertow_demo.ws.WebSocketServerEndpoint;
import cc.darhao.undertow_demo.ws.controller.ChatController;
import cc.darhao.undertow_demo.ws.controller.LoginController;

public class UndertowBoot extends JFinalConfig {

	private UndertowServer undertowServer;


	public void start() {
		// 配置Pasta
		Pasta.bindRoute("login", LoginController.class);
		Pasta.bindRoute("chat", ChatController.class);
		// 配置web服务器
		undertowServer = UndertowServer.create(UndertowBoot.class);
		undertowServer.setPort(8080);
		undertowServer.configWeb(builder -> {
			builder.addWebSocketEndpoint(WebSocketServerEndpoint.class);
		});
		undertowServer.start();
	}


	public void stop() {
		undertowServer.stop();
	}


	public void configConstant(Constants me) {
	}


	@Override
	public void configRoute(Routes me) {
		me.add("/file", FileController.class);
	}


	public void configEngine(Engine me) {
	}


	public void configPlugin(Plugins me) {
	}


	public void configInterceptor(Interceptors me) {
	}


	public void configHandler(Handlers me) {
	}
}
