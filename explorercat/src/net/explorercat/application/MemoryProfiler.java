package net.explorercat.application;

import java.lang.management.ManagementFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * Class to log memory
 */
public class MemoryProfiler {
	// Logging
	private static Log log = LogFactory.getLog(ApplicationController.class);

	public void logMemoryUsage() {

		int mb = 1024 * 1024;

		// Maximum available memory
		long maxMemory = Runtime.getRuntime().maxMemory();
		log.info("Memory: Maximum amount of memory the JVM will attempt to use (MB) : "
				+ (maxMemory == (Long.MAX_VALUE / mb) ? "no limit"
						: (maxMemory / mb)));

		// Total available memory
		log.info("Memory: Total memory (MB): "
				+ (Runtime.getRuntime().totalMemory() / mb));

		// Used memory
		log.info("Memory: Used Memory (MB) :"
				+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
						.freeMemory()) / mb);

		log.info("Memory: Total amount of free memory available to the JVM (MB) : "
				+ (Runtime.getRuntime().freeMemory() / mb));

		log.info("Memory: Heap Memory: "
				+ ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
		log.info("Memory: NonHeap Memory: "
				+ ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());

	}
}
