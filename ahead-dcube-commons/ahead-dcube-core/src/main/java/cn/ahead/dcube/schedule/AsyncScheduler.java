package cn.ahead.dcube.schedule;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ahead.dcube.context.SpringContext;

/**
 * 异步任务管理器
 * 
 * @author ruoyi
 */
public class AsyncScheduler {

	private static Logger logger = LoggerFactory.getLogger(AsyncScheduler.class);
	/**
	 * 操作延迟10毫秒
	 */
	private final int OPERATE_DELAY_TIME = 10;

	/**
	 * 异步操作任务调度线程池
	 */
	private ScheduledExecutorService executor = SpringContext.getBean("scheduledExecutorService");

	/**
	 * 单例模式
	 */
	private AsyncScheduler() {
	}

	private static AsyncScheduler me = new AsyncScheduler();

	public static AsyncScheduler me() {
		return me;
	}

	/**
	 * 执行任务
	 * 
	 * @param task 任务
	 */
	public void execute(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}

	/**
	 * 停止任务线程池
	 */
	public void shutdown() {
		shutdownAndAwaitTermination(executor);
	}

	/**
	 * 停止线程池 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务. 如果超时, 则调用shutdownNow,
	 * 取消在workQueue中Pending的任务,并中断所有阻塞函数. 如果仍人超時，則強制退出. 另对在shutdown时线程本身被调用中断做了处理.
	 */
	public static void shutdownAndAwaitTermination(ExecutorService pool) {
		if (pool != null && !pool.isShutdown()) {
			pool.shutdown();
			try {
				if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
					pool.shutdownNow();
					if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
						logger.info("Pool did not terminate");
					}
				}
			} catch (InterruptedException ie) {
				pool.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}
}
