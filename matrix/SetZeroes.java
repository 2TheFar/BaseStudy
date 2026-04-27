
import java.util.Arrays;

public class SetZeroes {
    public void setZeroes(int[][] matrix) {
        // 列长-二维数组长度-行数
        int lenColumn = matrix.length;
        // 行长-一维数组长度-列数
        int lenRow = matrix[0].length;

        boolean firstColHas0=false;
        boolean firstRowHas0 =false;
        for (int i = 0; i < lenRow; i++) {
            if (matrix[0][i]==0){
                firstRowHas0 =true;
            }
        }
        for(int i=0;i<lenColumn;i++){
            if(matrix[i][0]==0){
                firstColHas0=true;
            }
        }
        // 先放置play1行1列，反正就算被置0也是本来就要置0的
        for (int i=1;i<lenColumn;i++){
            for (int j=1;j<lenRow;j++){
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }
        // 依旧不用管1行1列，1行中为0时清零当前列，1列中为0时清零当前行
        // 转变思维：
        // 只需要清零第二行到最后一行，第二列到最后一列
        // 如果是matrix[i][j]，如果matrix[i][0]==0||matrix[0][j]==0，那matrix就置0（以元素为单位，而不是以向量为单位）
        for (int i=1;i<lenColumn;i++){
            for (int j=1;j<lenRow;j++){
                if(matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
        // 等等！遍历第一列，那就是看列长lenColumn啊！
        if(firstColHas0){
            for (int i=0;i<lenColumn;i++){
                matrix[i][0]=0;
            }
        }
        if(firstRowHas0){
            Arrays.fill(matrix[0],0);
        }
    }
}
