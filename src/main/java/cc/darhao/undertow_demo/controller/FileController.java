package cc.darhao.undertow_demo.controller;

import java.io.File;
import java.util.Arrays;
import com.jfinal.core.Controller;

import cc.darhao.undertow_demo.entity.Constant;

/**文件管理控制器
 * @author   HCJ
 * @date     2019年11月11日 下午4:41:38
 */
public class FileController extends Controller {

	/**@author HCJ
	 * 查询所有文件
	 * @date 2019年11月11日 下午4:41:51
	 */
	public void listFile() {
		File file = new File(Constant.MESSAGE_FILE_PATH);
		if (file.exists()) {
			String[] fileNames = file.list();
			renderJson(Arrays.asList(fileNames));
		} else {
			renderJson("记录文件不存在");
		}
	}


	/**@author HCJ
	 * 下载文件
	 * @param fileName 文件名
	 * @date 2019年11月11日 下午4:42:05
	 */
	public void downloadFile(String fileName) {
		File file = new File(Constant.MESSAGE_FILE_PATH + fileName);
		if (file.exists()) {
			renderFile(file);
		} else {
			renderJson("文件不存在");
		}
	}
}
