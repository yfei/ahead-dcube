package cn.ahead.dcube.task;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 确保应用退出时能关闭后台线程
 *
 * @author yangfei
 */
@Component
@Slf4j
public class TaskShutdownManager {

	@PreDestroy
	public void destroy() {
		shutdownAsyncManager();
	}

	/**
	 * 停止异步执行任务
	 */
	private void shutdownAsyncManager() {
		try {
			log.info("====关闭后台任务任务线程池====");
			AsyncTaskScheduler.me().shutdown();
			TaskScheduler.me().shutdown();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
