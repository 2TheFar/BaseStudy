public class Rotate {
    public static void main(String[] args) {

    }

    /*
     * 一开始恍惚了以为是中心对称，先上下翻转，再左右翻转不就好了
     * 好在先让豆包验证思路，直接给我了个❌
     * 原来是旋转90度，不过也注意到了中心对称是旋转180度
     * 虽然是偷看了答案知道的提示：两次翻转，先上下再沿对角线
     * 不过也是注意到，旋转180度可以通过两次翻转实现，那旋转90度应该也可以
     * 这个真是我画图悟出来的，先左右翻转，跟旋转90度的图像比，就是相对于y=x对称了
     * 放到矩阵里，就是先左右翻转，再沿副对角线进行翻转
     * 不过既然标准答案是先上下翻转再沿主对角线斜着翻转，我就这样写吧！沿主对角线翻转，行变列列变行，其实也是转置
     * 转置！你说转置了对吧，那转置矩阵不就是第i行变第i列，第j列变第i行，(i,j)变(j,i)吗？也就是说(i,j)对称后的坐标是(j,i)
     * 那这就简单了嘛！
     */
    public void rotate(int[][] matrix) {
        int high = matrix.length;
        int width = matrix[0].length;
        int top = 0;
        int bottom = high - 1;
        while (top < bottom) {
            int temp = 0;
            for (int i = 0; i < width; i++) {
                temp = matrix[top][i];
                matrix[top][i] = matrix[bottom][i];
                matrix[bottom][i] = temp;
            }
            top++;
            bottom--;
        }

        //遍历下三角，交换(i,j)和(j,i)
        for (int i = 1; i < high; i++) {
            int temp=0;
            for (int j = 0; j < i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}