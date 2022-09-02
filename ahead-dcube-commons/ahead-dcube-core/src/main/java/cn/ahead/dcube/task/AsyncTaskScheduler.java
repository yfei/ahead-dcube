package cn.ahead.dcube.task;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ahead.dcube.context.SpringContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步任务管理器
 * 
 * @author yangfei
 */
@Slf4j
public class AsyncTaskScheduler {

	/**
	 * 操作延迟10毫秒
	 */
	private final int OPERATE_DELAY_TIME = 10;

	/**
	 * 异步操作任务调度线程池
	 */
	private ScheduledExecutorService executor = SpringContext.getBean("scheduledTaskService");
	
	
	/**
	 * 异步操作任务调度线程池
	 */
	private static ScheduleConfig config = SpringContext.getBean(ScheduleConfig.class);

	/**
	 * 单例模式
	 */
	private AsyncTaskScheduler() {
	}

	private static AsyncTaskScheduler me = new AsyncTaskScheduler();

	public static AsyncTaskScheduler me() {
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
	 * 执行任务
	 * 
	 * @param task 任务
	 */
	public void execute(TimerTask task, long delay, TimeUnit unit) {
		executor.schedule(task, delay, unit);
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
	public void shutdownAndAwaitTermination(ExecutorService pool) {
		if (pool != null && !pool.isShutdown()) {
			pool.shutdown();
			try {
				if (!pool.awaitTermination(config.getAwaitTerminationSeconds(), TimeUnit.SECONDS)) {
					pool.shutdownNow();
					if (!pool.awaitTermination(config.getAwaitTerminationSeconds(), TimeUnit.SECONDS)) {
						log.info("Pool did not terminate");
					}
				}
			} catch (InterruptedException ie) {
				pool.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}
}
