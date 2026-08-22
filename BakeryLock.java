public class BakeryLock implements Lock 
{

    private final int n;
    private final VolatileBoolean[] flag; // indication on wanting to enter a cs
    private final VolatileInt[] label; // contains the number when entering the bakery

    public BakeryLock(int n) 
    {
        this.n = n;
        flag = new VolatileBoolean[n];
        label =new VolatileInt[n];
        for(int i = 0; i < n; i++){
            flag[i] = VolatileBoolean(false);
            label[i] = VolatileInt(0);
        }
    }

    @Override
    public void lock(int threadId) 
    {
        flag[threadId].value = true;
        label[threadId].label = max(label);
        for(int j = 0; j < n;j++){
            if(j == threadId){
                continue;
            }
            while(flag[j] && (label[j] < label[threadId] || (label[j] == label[threadId] && j < threadId))){
                //wait
            }
        }
    }

    @Override
    public void unlock(int threadId) 
    {
        flag[threadId].value = false;
    }

    public int max(VolatileInt[] a){
        int max = 0;
        for(int i = 0; i < n;i++){
            if(a[i].value > max){
                max = a[i].value;
            }
        }
        return max + 1;
    }
}