public class BakeryLock implements Lock 
{

    private final int n;
    private final VolatileBoolean[] flag; // indication on wanting to enter a cs
    private final VolatileInt[] label; // contains the number when entering the bakery

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
        flag[threadId] = true;


    }

    @Override
    public void unlock(int threadId) 
    {
        
    }

    public int max(VolatileInt[] a){
        int max = 0;
        for(int i = 0; i < n;i++){
            if(a[i] > max){
                max = a[i];
            }
        }
        return max + 1;
    }
}