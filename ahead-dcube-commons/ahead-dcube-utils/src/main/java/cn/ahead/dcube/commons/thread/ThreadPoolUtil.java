package cn.ahead.dcube.commons.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:09:28
 * @since 1.0
 */
public class ThreadPoolUtil {
	/**
	 * 线程池维护线程的数量,与CPU核数一致
	 */
	public static final int COREPOOLSIZE = Runtime.getRuntime().availableProcessors();

	/**
	 * 创建具有固定线程数的线程池,线程数量为CPU核数。
	 *
	 * @param prefix
	 *            线程实例名称的前缀
	 * @return 线程池对象
	 */
	public static ExecutorService createFixedThreadPool(String prefix) {
		return Executors.newFixedThreadPool(COREPOOLSIZE, new NamedThreadFactory(prefix, false));
	}

	/**
	 * 创建指定线程数量的线程
	 *
	 * @param prefix
	 *            线程实例名称的前缀
	 * @return 线程池对象
	 */
	public static ExecutorService createFixedThreadPool(int poolSize, String prefix) {
		return Executors.newFixedThreadPool(poolSize, new NamedThreadFactory(prefix, false));
	}

	/**
	 * 使用工厂方法创建线程池<br>
	 * 无界线程池,可以进行自动线程回收
	 *
	 * @param prefix
	 *            线程实例名称的前缀
	 * @return 线程池对象
	 */
	public static ExecutorService getCachedExecutor(String prefix) {
		return Executors.newCachedThreadPool(new NamedThreadFactory(prefix));
	}

	/**
	 * 创建基于线程池设计的任务调度
	 *
	 * @param prefix
	 *            线程实例名称的前缀
	 * @return 线程池对象
	 */
	public static ScheduledExecutorService createScheduledExecutor(String prefix) {
		return Executors.newScheduledThreadPool(1, new NamedThreadFactory(prefix, false));
	}

}
