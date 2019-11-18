package cc.darhao.undertow_demo.config;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.redis.RedisPlugin;

import cc.darhao.undertow_demo.util.VisualSerializer;

public class Config {

	private static RedisPlugin rp;

	private static Cron4jPlugin cp;


	public void shutdown() {
		cp.stop();
		rp.stop();
	}


	public void start() {
		rp = new RedisPlugin("chat", "localhost");
		rp.setSerializer(new VisualSerializer());
		rp.start();
		cp = new Cron4jPlugin(PropKit.use("taskConfig.txt"), "cron4j");
		cp.start();
	}
}
