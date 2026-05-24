import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 1, 2 }, { 3, 4 } };
        SpiralOrder spiralOrder = new SpiralOrder();
        spiralOrder.spiralOrder(matrix);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        /*
         * 核心思路：
         * 按圈遍历，每一轮循环遍历一圈，否则每一步都要判断是否到达拐点极为困难
         * 用四条边界来替代拐点的计算，当从一条边界到达另一条边界时就要拐弯了，这样的方式更加动态，而不是一开始算好拐点，这种静态的方法
         * 用边界来算步数，那就太复杂了，直接下标从一条边界到另一条边界停下，更加直白
         * 最后可能只剩下一行或者一列，如果每一行都已经遍历完了，可能会因为行长还够导致重复遍历这一行，所以在上行右列遍历完后要检查是否还有行列需要遍历
         * 收缩到交叉就停止：
         * 上下交叉：上面的线跑到下面的线下面去了：中间已经没有任何行可以遍历了。
         * 左右交叉：左边的线跑到右边的线右边去了：中间已经没有任何列可以遍历了。
         * 只要任意一种交叉，就代表：所有圈都遍历完了，没东西可走了，直接停。
         * 四边界像四扇推拉门，慢慢往中间关；
         * 没撞上就继续，怼穿重叠就立刻停。
         */
        int high = matrix.length;
        int width = matrix[0].length;
        int top = 0;
        int bottom = high - 1;
        int left = 0;
        int right = width - 1;

        List<Integer> res = new ArrayList();
        while (left <= right && top <= bottom) {
            // 按圈遍历拆分成按行、列遍历，真正的按行列遍历不需要i,j来标记当前元素位置
            // 遍历的是哪一行（列）直接用指针就行，行（列）中指针i就算越界了也没关系，用完就丢
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            // top行已经遍历完了，top也+1了，现在top就是最顶行，所以右列直接从top开始就行
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            // 现在top也缩了，right也缩了，要及时检查是否已经左右或上下交叉了，一旦发生交叉就没必要继续了，否则还有可能重复遍历
            if (top > bottom || left > right)
                break;
            // 如果这里没有待遍历行了，就会重复遍历之前的行
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            // 一圈过后，四个指针又围成一圈，直接从top行的left列开始，前后衔接非常自然
        }
        return res;
    }
}
