package cc.darhao.undertow_demo.thread;

import cc.darhao.undertow_demo.config.Config;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Main implements SignalHandler {

	private Config config = new Config();

	private UndertowBoot undertowBoot = new UndertowBoot();


	public static void main(String[] args) throws Exception {
		// 开始运行系统
		Main main = new Main();
		// 注册Linux信号量
		Signal.handle(new Signal("INT"), main);// 对应Ctrl+C
		Signal.handle(new Signal("TERM"), main);// 对应kill
		// 开启系统
		main.start();
	}


	private void start() throws Exception {
		config.start();
		undertowBoot.start();
		System.out.println("聊天服务已成功开启");
	}


	@Override
	public void handle(Signal arg0) {
		try {
			undertowBoot.stop();
			config.shutdown();
			System.out.println("程序正常结束");
			System.exit(0);
		} catch (Exception e) {
			System.err.println("程序异常结束");
			System.exit(1);
		}
	}

}
