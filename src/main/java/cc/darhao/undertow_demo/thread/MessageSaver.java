package cc.darhao.undertow_demo.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.redis.Redis;

import cc.darhao.undertow_demo.entity.Constant;

/**聊天记录保存者
 * @author   HCJ
 * @date     2019年11月11日 下午4:43:46
 */
public class MessageSaver implements Runnable {

	@Override
	public void run() {
		List<String> messages = Redis.use().lrange(Constant.REDIS_MESSAGE_KEY, 0, -1);
		if (messages != null && !messages.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			File dir = new File(Constant.MESSAGE_FILE_PATH);
			if (!dir.exists()) {
				dir.mkdir();
			}
			File file = new File(Constant.MESSAGE_FILE_PATH + "message_" + format.format(new Date()) + ".txt");
			try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw);) {
				for (String message : messages) {
					bw.write(message);
					bw.newLine();
					bw.flush();
				}
				Redis.use().del(Constant.REDIS_MESSAGE_KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
