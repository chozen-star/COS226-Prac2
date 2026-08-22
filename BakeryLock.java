public class BakeryLock implements Lock 
{

    private final int n;
    private final VolatileBoolean[] flag; // indication on wanting to enter a cs
    private final VolatileInt[] label; // contains the number when entering the bakery

    public BakeryLock(int n) 
    {
        this.n = n;
        flag = new VolatileBoolean[n];
        label = new VolatileInt[n];
        for(int i = 0; i < n; i++){
            flag[i] = new VolatileBoolean(false);
            label[i] = new VolatileInt(0);
        }
    }

    @Override
    public void lock(int threadId) 
    {
        flag[threadId].value = true;
        label[threadId].value = max(label);
        for(int j = 0; j < n;j++){
            if(j == threadId){
                continue;
            }
            while(flag[j].value && (label[j].value < label[threadId].value || (label[j].value == label[threadId].value && j < threadId))){
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