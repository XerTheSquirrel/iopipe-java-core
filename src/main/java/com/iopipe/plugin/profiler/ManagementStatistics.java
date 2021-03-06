package com.iopipe.plugin.profiler;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains the information on all of the management statistics
 * which have been measured.
 *
 * @since 2018/05/22
 */
public final class ManagementStatistics
{
	/** The absolute time the sample was taken. */
	public final long abstime;
	
	/** The relative time the sample was taken. */
	public final long reltime;
	
	/** Class loader statistics. */
	public final ClassLoaderStatistics classloader;
	
	/** Compilation statistics. */
	public final CompilerStatistics compiler;
	
	/** Garbage collection statistics. */
	public final List<GarbageCollectorStatistics> gc;
	
	/** Uptime of the virtual machine. */
	public final UptimeStatistics uptime;
	
	/** Memory pools. */
	public final List<MemoryPoolStatistics> mempools;
	
	/** Memory statistics. */
	public final MemoryStatistics memory;
	
	/** Information on threads. */
	public final List<ThreadStatistics> threads;
	
	/** Buffer pools. */
	public final List<BufferPoolStatistics> buffers;
	
	/**
	 * Initializes the management statistics.
	 *
	 * @param __abs Absolute time.
	 * @param __rel Relative time.
	 * @param __cl The class loader statistics.
	 * @param __jit Compiler statistics.
	 * @param __gc Garbage collection statistics.
	 * @param __up Virtual machine uptime.
	 * @param __mpools Memory pool snapshots.
	 * @param __mem Memory statistics.
	 * @param __threads Thread information.
	 * @param __bufs Buffer information.
	 * @since 2018/05/22
	 */
	public ManagementStatistics(long __abs, long __rel,
		ClassLoaderStatistics __cl, CompilerStatistics __jit,
		GarbageCollectorStatistics[] __gc, UptimeStatistics __up,
		MemoryPoolStatistics[] __mpools, MemoryStatistics __mem,
		ThreadStatistics[] __threads, BufferPoolStatistics[] __bufs)
	{
		this.abstime = __abs;
		this.reltime = __rel;
		this.classloader = (__cl != null ? __cl :
			new ClassLoaderStatistics(0, 0, 0));
		this.compiler = (__jit != null ? __jit :
			new CompilerStatistics(-1));
		this.gc = Collections.<GarbageCollectorStatistics>unmodifiableList(
			Arrays.<GarbageCollectorStatistics>asList(
			(__gc == null ? (__gc = new GarbageCollectorStatistics[0]) :
			(__gc = __gc.clone()))));
		this.uptime = (__up != null ? __up :
			new UptimeStatistics(0, -1));
		this.mempools = Collections.<MemoryPoolStatistics>unmodifiableList(
			Arrays.<MemoryPoolStatistics>asList(
			(__mpools == null ? (__mpools = new MemoryPoolStatistics[0]) :
			(__mpools = __mpools.clone()))));
		this.memory = (__mem != null ? __mem :
			new MemoryStatistics(null, null, -1));
		this.threads = Collections.<ThreadStatistics>unmodifiableList(
			Arrays.<ThreadStatistics>asList(
			(__threads != null ? (__threads = __threads.clone()) :
			(__threads = new ThreadStatistics[0]))));
		this.buffers = Collections.<BufferPoolStatistics>unmodifiableList(
			Arrays.<BufferPoolStatistics>asList(
			(__bufs != null ? (__bufs = __bufs.clone()) :
			(__bufs = new BufferPoolStatistics[0]))));
		
		// Initialize values in the event they are null
		for (int i = 0, n = __gc.length; i < n; i++)
			if (__gc[i] == null)
				__gc[i] = new GarbageCollectorStatistics();
		
		for (int i = 0, n = __mpools.length; i < n; i++)
			if (__mpools[i] == null)
				__mpools[i] = new MemoryPoolStatistics();
		
		for (int i = 0, n = __threads.length; i < n; i++)
			if (__threads[i] == null)
				__threads[i] = new ThreadStatistics();
		
		for (int i = 0, n = __bufs.length; i < n; i++)
			if (__bufs[i] == null)
				__bufs[i] = new BufferPoolStatistics();
	}
	
	/**
	 * Creates a snapshot of the statistics used for management.
	 *
	 * @param __rel The relative time.
	 * @return The snapshot of the all statistics.
	 * @since 2018/05/22
	 */
	public static ManagementStatistics snapshot(long __rel)
	{
		return new ManagementStatistics(
			System.nanoTime(), __rel,
			ClassLoaderStatistics.snapshot(),
			CompilerStatistics.snapshot(),
			GarbageCollectorStatistics.snapshots(),
			UptimeStatistics.snapshot(),
			MemoryPoolStatistics.snapshots(),
			MemoryStatistics.snapshot(),
			ThreadStatistics.snapshots(),
			BufferPoolStatistics.snapshots());
	}
}

