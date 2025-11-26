package androidx.camera.core.impl.utils.executor;

import androidx.core.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
final class SequentialExecutor implements Executor {
    private static final String TAG = "SequentialExecutor";
    private final Executor mExecutor;
    final Deque<Runnable> mQueue = new ArrayDeque();
    private final QueueWorker mWorker = new QueueWorker();
    WorkerRunningState mWorkerRunningState = WorkerRunningState.IDLE;
    long mWorkerRunCount = 0;

    enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    SequentialExecutor(Executor executor) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        synchronized (this.mQueue) {
            if (this.mWorkerRunningState != WorkerRunningState.RUNNING && this.mWorkerRunningState != WorkerRunningState.QUEUED) {
                long j = this.mWorkerRunCount;
                Runnable runnable2 = new Runnable() { // from class: androidx.camera.core.impl.utils.executor.SequentialExecutor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        runnable.run();
                    }
                };
                this.mQueue.add(runnable2);
                this.mWorkerRunningState = WorkerRunningState.QUEUING;
                try {
                    this.mExecutor.execute(this.mWorker);
                    if (this.mWorkerRunningState != WorkerRunningState.QUEUING) {
                        return;
                    }
                    synchronized (this.mQueue) {
                        if (this.mWorkerRunCount == j && this.mWorkerRunningState == WorkerRunningState.QUEUING) {
                            this.mWorkerRunningState = WorkerRunningState.QUEUED;
                        }
                    }
                    return;
                } catch (Error | RuntimeException e) {
                    synchronized (this.mQueue) {
                        if ((this.mWorkerRunningState != WorkerRunningState.IDLE && this.mWorkerRunningState != WorkerRunningState.QUEUING) || !this.mQueue.removeLastOccurrence(runnable2)) {
                            z = false;
                        }
                        if (!(e instanceof RejectedExecutionException) || z) {
                            throw e;
                        }
                    }
                    return;
                }
            }
            this.mQueue.add(runnable);
        }
    }

    final class QueueWorker implements Runnable {
        QueueWorker() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.mQueue) {
                    SequentialExecutor.this.mWorkerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x004e, code lost:
        
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        
            r3.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        
            androidx.camera.core.Logger.e(androidx.camera.core.impl.utils.executor.SequentialExecutor.TAG, "Exception while executing runnable " + r3, r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:?, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void workOnQueue() {
            /*
                r7 = this;
                r0 = 0
                r1 = 0
            L2:
                androidx.camera.core.impl.utils.executor.SequentialExecutor r2 = androidx.camera.core.impl.utils.executor.SequentialExecutor.this     // Catch: java.lang.Throwable -> L6e
                java.util.Deque<java.lang.Runnable> r2 = r2.mQueue     // Catch: java.lang.Throwable -> L6e
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L6e
                if (r0 != 0) goto L2c
                androidx.camera.core.impl.utils.executor.SequentialExecutor r0 = androidx.camera.core.impl.utils.executor.SequentialExecutor.this     // Catch: java.lang.Throwable -> L6b
                androidx.camera.core.impl.utils.executor.SequentialExecutor$WorkerRunningState r0 = r0.mWorkerRunningState     // Catch: java.lang.Throwable -> L6b
                androidx.camera.core.impl.utils.executor.SequentialExecutor$WorkerRunningState r3 = androidx.camera.core.impl.utils.executor.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> L6b
                if (r0 != r3) goto L1c
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L6b
                if (r1 == 0) goto L1b
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L1b:
                return
            L1c:
                androidx.camera.core.impl.utils.executor.SequentialExecutor r0 = androidx.camera.core.impl.utils.executor.SequentialExecutor.this     // Catch: java.lang.Throwable -> L6b
                long r3 = r0.mWorkerRunCount     // Catch: java.lang.Throwable -> L6b
                r5 = 1
                long r3 = r3 + r5
                r0.mWorkerRunCount = r3     // Catch: java.lang.Throwable -> L6b
                androidx.camera.core.impl.utils.executor.SequentialExecutor r0 = androidx.camera.core.impl.utils.executor.SequentialExecutor.this     // Catch: java.lang.Throwable -> L6b
                androidx.camera.core.impl.utils.executor.SequentialExecutor$WorkerRunningState r3 = androidx.camera.core.impl.utils.executor.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> L6b
                r0.mWorkerRunningState = r3     // Catch: java.lang.Throwable -> L6b
                r0 = 1
            L2c:
                androidx.camera.core.impl.utils.executor.SequentialExecutor r3 = androidx.camera.core.impl.utils.executor.SequentialExecutor.this     // Catch: java.lang.Throwable -> L6b
                java.util.Deque<java.lang.Runnable> r3 = r3.mQueue     // Catch: java.lang.Throwable -> L6b
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L6b
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L6b
                if (r3 != 0) goto L49
                androidx.camera.core.impl.utils.executor.SequentialExecutor r0 = androidx.camera.core.impl.utils.executor.SequentialExecutor.this     // Catch: java.lang.Throwable -> L6b
                androidx.camera.core.impl.utils.executor.SequentialExecutor$WorkerRunningState r3 = androidx.camera.core.impl.utils.executor.SequentialExecutor.WorkerRunningState.IDLE     // Catch: java.lang.Throwable -> L6b
                r0.mWorkerRunningState = r3     // Catch: java.lang.Throwable -> L6b
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L6b
                if (r1 == 0) goto L48
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L48:
                return
            L49:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L6b
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L6e
                r1 = r1 | r2
                r3.run()     // Catch: java.lang.RuntimeException -> L53 java.lang.Throwable -> L6e
                goto L2
            L53:
                r2 = move-exception
                java.lang.String r4 = "SequentialExecutor"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
                r5.<init>()     // Catch: java.lang.Throwable -> L6e
                java.lang.String r6 = "Exception while executing runnable "
                r5.append(r6)     // Catch: java.lang.Throwable -> L6e
                r5.append(r3)     // Catch: java.lang.Throwable -> L6e
                java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> L6e
                androidx.camera.core.Logger.e(r4, r3, r2)     // Catch: java.lang.Throwable -> L6e
                goto L2
            L6b:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L6b
                throw r0     // Catch: java.lang.Throwable -> L6e
            L6e:
                r0 = move-exception
                if (r1 == 0) goto L78
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L78:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.executor.SequentialExecutor.QueueWorker.workOnQueue():void");
        }
    }
}
