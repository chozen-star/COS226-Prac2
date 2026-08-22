public class ThreadID {
    private static int id = 0;
    private static ThreadLocal<Integer> threadID = ThreadLocal.withInitial(() -> id++);
    
    public static int get() {
        return threadID.get();
    }
}