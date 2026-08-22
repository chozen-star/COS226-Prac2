public class FilterLock implements Lock 
{

    private final int n;
    private final VolatileInt[] level;
    private final VolatileInt[] victim;

    public FilterLock(int n) 
    {
        this.n = n;
        level = new VolatileInt[n];
        victim = new VolatileInt[n];

        for (int i = 0; i < n; i++) 
        {
            level[i] = new VolatileInt(0);
            victim[i] = new VolatileInt(0);
        }

    }

    @Override
    public void lock(int threadId) 
    {
        for (int levl= 1;levl < n; levl++)
        {
            level[threadId].value(levl);
            victim[levl].value = threadId;

            while (existsHigherOrEqualLevel(threadId, levl) && victim[levl].value() == threadId) 
            {
                // busy wait
            }
        }
        //thread has now reached the highest level and can enter the critical section

    }
    /** 
     * Checks if there exists a thread with a higher or equal level than the current thread.
     * @param threadId the ID of the current thread
     * @param levl the level to check against
     * @return true if such a thread exists, false otherwise
     */
    private boolean existsHigherOrEqualLevel(int threadId, int levl) {
        for (int i = 0; i < n; i++) {
            if (i != threadId && level[i].value >= levl) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void unlock(int threadId) 

    {
        // Reset the level of the current thread to 0, indicating it is no longer interested in entering the critical section
        
        level[threadId].value(0);
        
    }
}