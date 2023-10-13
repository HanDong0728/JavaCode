import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] students = new int[n];

        // 初始化学生队列
        for (int i = 0; i < n; i++) {
            students[i] = i + 1;
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();

            // 找到学生的位置
            int index = -1;
            for (int j = 0; j < n; j++) {
                if (students[j] == p) {
                    index = j;
                    break;
                }
            }

            // 移出队列
            int removedStudent = students[index];

            // 计算新位置
            int newIndex = index + q;

            // 处理向前移动超出队头的情况
            if (newIndex < 0) {
                newIndex = 0;
            }

            // 处理向后移动超出队尾的情况
            if (newIndex >= n) {
                newIndex = n - 1;
            }

            // 插入新位置
            if (newIndex != index) {
                if (newIndex > index) {
                    for (int j = index; j < newIndex; j++) {
                        students[j] = students[j + 1];
                    }
                } else {
                    for (int j = index; j > newIndex; j--) {
                        students[j] = students[j - 1];
                    }
                }
                students[newIndex] = removedStudent;
            }
        }

        // 输出最终的学生学号
        for (int i = 0; i < n; i++) {
            System.out.print(students[i] + " ");
        }
    }
}
