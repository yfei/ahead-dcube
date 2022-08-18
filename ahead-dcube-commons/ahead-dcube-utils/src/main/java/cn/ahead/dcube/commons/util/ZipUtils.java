package cn.ahead.dcube.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * 压缩 解压缩 工具类
 *
 * @Author peiwenlong
 * @Date 2021/2/4 15:02
 * @Version 1.0
 */
@Slf4j
public class ZipUtils {
    /***
     * 压缩GZip
     *
     * @param data
     * @return
     */
    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        GZIPOutputStream gzip = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            b = bos.toByteArray();
        } catch (Exception ex) {
            log.error("gZip发生异常：", ex);
        } finally {
            try {
                if (null != gzip) gzip.close();
                if (null != bos) bos.close();
            } catch (IOException e) {
                log.error("gZip流关闭时发生异常：", e);
            }
        }
        return b;
    }

    /***
     * 解压GZip
     *
     * @param data
     * @return
     */
    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        ByteArrayInputStream bis = null;
        GZIPInputStream gzip = null;
        ByteArrayOutputStream baos = null;
        try {
            bis = new ByteArrayInputStream(data);
            gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
        } catch (Exception ex) {
            log.error("unGZip发生异常：", ex);
        } finally {
            try {
                if (null != bis) bis.close();
                if (null != gzip) gzip.close();
                if (null != baos) baos.close();
            } catch (IOException e) {
                log.error("unGZip流关闭时发生异常：", e);
            }
        }
        return b;
    }
}
