
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        //用一个单调递减的双端队列来存储信息，队首始终是当前窗口的最大值，其余都是未来可能的最大值
        //关键思想：当有新的元素入队时，直接清除比它小的弱者，然后再把它入队。
        //因为只要新元素在窗口内，比它小的老元素永远被压一头，绝无可能是最大值。
        //而老元素又要比新元素更早滑出窗口，所以老元素直到滑出窗口都有新元素在，永远上不了桌，没有记录的意义。
        //有点像公司里面，如果老登比小登牛逼，那老登是有可能熬到顶点的。但如果新来的小登已经比老登牛逼了，那老登直到退休都不可能熬到全公司最牛逼的那一天，因为总被这个小登压一头。
        //然后这个单调队列应该存储下标而不是值，因为窗口滑动后左边界要退出队列，需要通过下标来判断最大值是不是左边界
        //左边界要么不在队列，要么就是最大值，如果不是最大值早就被后来的值踢飞了
        //比如窗口大小5，队列5432,5可能是第二个数也可能是左边界，直接看值判断不出来
        //知道下标可以找到对应的值，但是知道对应的值可找不到对应的下标
        Deque<Integer> deque=new LinkedList<>();
        int[] res=new int[n-k+1];//下标的最大是n-k
        //初始化，我去差点忘了要存的是下标而不是值，还好有AI
        //deque.addLast(0);
        //deque有第一个值，就不用担心deque会空了，不需要每次都判断。吗？不对！在循环踢掉老元素的过程中有可能踢完，所以循环要加判空，所以给初值也没什么意义！
        for (int i=0;i<k;i++){
            //只要队列最后一个比新元素小就踢掉，最后要么最后一个更大，要么是空的
            while (!deque.isEmpty()&&nums[i]>nums[deque.peekLast()]){
                deque.pollLast();
            }
            //新元素总是要入队的，如果更小不入队的想法是错的，所以这样写的100%对的
            deque.addLast(i);
        }
        int p=0;
        res[p++]=nums[deque.peekFirst()];

        for (int i=1;i<n-k+1;i++){
            //此时deque还是之前那个窗口的状态，需要更新
            if (deque.peekFirst()==i-1){
                deque.pollFirst();
            }
            //把下标i+k-1送进来
            while (!deque.isEmpty()&&nums[deque.peekLast()]<nums[i+k-1]){
                deque.pollLast();
            }
            deque.addLast(i+k-1);
            //更新完毕记录最大值
            res[i]=nums[deque.peekFirst()];
        }
        return res;
    }
}
