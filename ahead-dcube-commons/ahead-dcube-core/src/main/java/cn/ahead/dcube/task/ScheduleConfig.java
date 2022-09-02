package cn.ahead.dcube.task;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.Data;

/**
 * 线程池配置
 *
 * @author yangfei
 **/
@Configuration
@ConfigurationProperties(prefix = "schedule")
@Data
public class ScheduleConfig {

	private static Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);

	// 核心线程池大小
	private int poolSize = 50;

	// 最大可创建的线程数
	private int maxPoolSize = 200;

	// 队列最大长度
	private int queueCapacity = 1000;

	// 线程池维护线程所允许的空闲时间
	private int keepAliveSeconds = 300;

	// 线程池关闭时等待时间
	private int awaitTerminationSeconds = 120;

	@Bean(name = "taskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(maxPoolSize);
		executor.setCorePoolSize(poolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		// 线程池对拒绝任务(无线程可用)的处理策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	/**
	 * 执行周期性或定时任务
	 */
	@Bean(name = "scheduledTaskService")
	protected ScheduledExecutorService scheduledExecutorService() {
		ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(poolSize,
				new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				printException(r, t);
			}
		};
		threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
		return threadPoolExecutor;
	}

	/**
	 * 打印线程异常信息
	 */
	public static void printException(Runnable r, Throwable t) {
		if (t == null && r instanceof Future<?>) {
			try {
				Future<?> future = (Future<?>) r;
				if (future.isDone()) {
					future.get();
				}
			} catch (CancellationException ce) {
				t = ce;
			} catch (ExecutionException ee) {
				t = ee.getCause();
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}
		if (t != null) {
			logger.error(t.getMessage(), t);
		}
	}
}
