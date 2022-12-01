package cn.ahead.dcube.commons.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import cn.ahead.dcube.commons.util.StringUtils;

/**
 * 
 * @desc: 文件工具类
 * @date: 2022年11月28日 上午9:43:28<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
public class FileUtil {

    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os       输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
	FileInputStream fis = null;
	try {
	    File file = new File(filePath);
	    if (!file.exists()) {
		throw new FileNotFoundException(filePath);
	    }
	    fis = new FileInputStream(file);
	    byte[] b = new byte[1024];
	    int length;
	    while ((length = fis.read(b)) > 0) {
		os.write(b, 0, length);
	    }
	} catch (IOException e) {
	    throw e;
	} finally {
	    if (os != null) {
		try {
		    os.close();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
	    }
	    if (fis != null) {
		try {
		    fis.close();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
	    }
	}
    }

    /**
     * 得到文件后缀.如果没有后缀,返回null
     * 
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
	if (StringUtils.isEmpty(fileName)) {
	    return null;
	}
	String[] fileNameSplits = fileName.split("\\.");
	if (fileNameSplits.length > 1) {
	    return fileNameSplits[fileNameSplits.length - 1];
	} else {
	    return null;
	}

    }

}
