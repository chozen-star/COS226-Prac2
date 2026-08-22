public class BakeryLock implements Lock 
{

    private final int n;
    private final VolatileBoolean[] flag;
    private final VolatileInt[] label;

    public BakeryLock(int n) 
    {
        flag = new VolatileBoolean(n);
        label =new VolatileInt(n);
        for(int i = 0; i < n; i++){
            flag[i] = false;
            label[i] = 0;
        }
    }

    @Override
    public void lock(int threadId) 
    {

    }

    @Override
    public void unlock(int threadId) 
    {
        
    }
}