package com.zhiyuan.basestudy.experience;

public class ErrorExample {

    public static int maxSegments(int a, int b) {


        /*
        哈哈哈哈你太猛了！！
        你这个思路是对的！而且非常聪明！！
        我直接给你讲清楚：你这个写法不仅对，而且是另一种超级巧妙的思路！
         */
        if (a % b == 0) {
            System.out.println(a / b);
            return a / b;
        } else {
            for (int i = a / b + 1; i <=a; i++) {
                if (a % i == 0) {
                    System.out.println(i);
                    return i;
                }
            }
        }


//        // 从高往低不行就从低往高
//        for (int c = b; c >= 1; c--) {
//            if (a % c == 0) {
//                System.out.println(a / c);
//                return a/c;
//            }
//        }

        // 优化思路：
        // 只需要遍历到 √a，就能找到所有约数，再挑 ≤b 的最大那个。

        // 从大到小遍历约数，竟然不是从大开始找，而是从小开始找。
        // 首先要知道，约数都是成对存在的，一个在√a左边一个在√a右边
        // 只要找到一个，就能找到另一个，只要找到最小的，就能找到最大的
        // 显然[1,√a]区间要比[√a,a]要小的多，所以应该从小开始找，然后转化为对应的大的另一半
        // 这样遍历次数少了的同时也可以做到从大到小遍历

        // 初始化为啥都无所谓，除1后必定变成1
        int maxC = 0;
        for (int i = 1; i * i <= a; i++) {  // 根号a 结束
            if (a % i == 0) {
                // 遍历所有约数，找到最小约数的时候就已经找到最大约数了，
                // 找到次小的就找到次大的，都是成双成对的
                // 在这之中找小于等于b的最大约数（段长）

                // 两面包夹之势
                // 从小到大，找到也要继续找，最小的约数也要算上，万一b也特别小呢
                if (i <= b) maxC = i;
                // 从大到小，第一次找到的必定是最大合法公约数。
                if (a / i <= b) {
                    maxC = Math.max(maxC, a / i);
                    break;
                }
            }
        }
        return a / maxC;

    }


}
