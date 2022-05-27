package cn.dcube.ahead.base.io;

import cn.dcube.ahead.base.exception.AheadRuntimeException;
import cn.dcube.ahead.base.util.StringUtils;

import java.io.*;

/**
 * 文件夹工具
 *
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:10:41
 * @since 1.0
 */
public class FileUtil {

    /**
     * 获取系统跟路径文件对象,window系统下是各个盘符;linux系统下是根目录(/)
     *
     * @return
     */
    public static File[] getSystemRoots() {
        File[] files = File.listRoots();
        return files;
    }

    /**
     * 获取系统跟路径,window系统下是各个盘符;linux系统下是根目录(/)
     *
     * @return
     */
    public static String[] getSystemRootPaths() {
        File[] files = getSystemRoots();
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            paths[i] = files[i].getPath();
        }
        return paths;
    }

    /**
     * 复制文件/文件夹
     *
     * @param src 源文件/文件夹
     * @param dst 目的文件/文件夹
     * @throws IOException
     */
    public static void copyFile(File src, File dst) {
        // 如果目的文件夹不存在,创建文件夹
        if (!dst.exists() && src.isDirectory()) {
            dst.mkdirs();
        }

        if (src.isDirectory()) { // 目录,递归
            for (File file : src.listFiles()) {
                copyFile(file, new File(dst.getPath() + File.separator + file.getName()));
            }
        } else {
            // 文件
            BufferedInputStream input = null;
            FileOutputStream out = null;
            try {
                input = new BufferedInputStream(new FileInputStream(src));
                out = new FileOutputStream(dst);
                byte[] buffer = new byte[1024];
                int byteread = 0;
                while ((byteread = input.read(buffer)) != -1) {
                    out.write(buffer, 0, byteread);
                }
            } catch (IOException e) {
                throw new AheadRuntimeException(e);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        throw new AheadRuntimeException(e);
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        throw new AheadRuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 获取文件输出流.使用结束后,务必关闭输出流.
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public OutputStream getFileOutputStream(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new AheadRuntimeException("file not exists!");
        }
        OutputStream out = new FileOutputStream(file);
        return out;
    }

    public static String[] getFileNameAndSuffix(String fileName) {
        String[] result = new String[2];
        if (StringUtils.isNotEmpty(fileName)) {
            int lastDotPosition = fileName.lastIndexOf(".");
            if (lastDotPosition != -1) {
                result[0] = fileName.substring(0, lastDotPosition);
                result[1] = fileName.substring(lastDotPosition + 1);
            } else {
                result[0] = fileName;
            }
        }
        return result;
    }
}
