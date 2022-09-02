package cn.ahead.dcube.task;

import java.util.TimerTask;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.ahead.dcube.context.SpringContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步任务管理器
 * 
 * @author yangfei
 */
public class TaskScheduler {

	/**
	 * 异步操作任务调度线程池
	 */
	private ThreadPoolTaskExecutor executor = SpringContext.getBean("taskExecutor");

	/**
	 * 单例模式
	 */
	private TaskScheduler() {
	}

	private static TaskScheduler me = new TaskScheduler();

	public static TaskScheduler me() {
		return me;
	}

	/**
	 * 执行任务
	 * 
	 * @param task 任务
	 */
	public void execute(TimerTask task) {
		executor.execute(task);
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
	public void shutdownAndAwaitTermination(ThreadPoolTaskExecutor pool) {
		pool.shutdown();
	}
}
