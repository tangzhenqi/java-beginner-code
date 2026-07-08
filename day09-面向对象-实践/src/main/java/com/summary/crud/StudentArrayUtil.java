package com.summary.crud;

/**
 * 知识点 6：对象数组的增删改查工具方法
 *
 * 把"重复使用的小动作"抽成 static 方法，是初学者写代码最该养成的习惯。
 * 三步思考法：
 *   1. 我要干嘛？        -> 决定方法名
 *   2. 我需要什么？      -> 决定形参列表
 *   3. 调用处要不要结果？-> 决定返回值类型；要结果就 return，不要就 void
 *
 * 这里抽了 5 个工具：
 *   - contains   按 id 判断是否已存在（唯一性判断）
 *   - getIndex   按 id 找索引，找不到返回 -1
 *   - getCount   统计已经放进数组的元素个数（非 null 的格子数）
 *   - expand     扩容：新数组长度 = 老数组 + 1，把老数据拷贝过去
 *   - printArr   遍历打印（跳过 null）
 */
public class StudentArrayUtil {

    public static boolean contains(Student[] arr, int id) {
        return getIndex(arr, id) >= 0;
    }

    public static int getIndex(Student[] arr, int id) {
        for (int i = 0; i < arr.length; i++) {
            Student stu = arr[i];
            if (stu != null && stu.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static int getCount(Student[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * 扩容：返回一个长度 +1 的新数组，并把老数组元素拷贝过来。
     * 拓展：JDK 自带 System.arraycopy / Arrays.copyOf 一行也能搞定，见 ExtensionDemo。
     */
    public static Student[] expand(Student[] arr) {
        Student[] newArr = new Student[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void printArr(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Student stu = arr[i];
            if (stu != null) {
                System.out.println(stu.getId() + ", " + stu.getName() + ", " + stu.getAge());
            }
        }
    }
}
