package programmers;


import programmers.hikingCourse.Solution;
import programmers.hikingCourse.Solution_old_3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // 두 큐 합 같게하기
//        Solution solution = new Solution();
//        int[] queue1 = {3,2,7,2};
//        int[] queue2 = {4,6,5,1};
//        int[] queue1 = {1,2,1,2};
//        int[] queue2 = {1,10,1,2};
//        int[] queue1 = {1,1};
//        int[] queue2 = {1,5};
//        int[] queue1 = {2,4,6,8};
//        int[] queue2 = {1,1,1,1};
//
//        int answer = solution.solution(queue1, queue2);
//        System.out.println(answer);

        // 등산 코스 구하기

        Solution solution = new Solution();
//        int n = 6;
//        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
//        int[] gates = {1, 3};
//        int[] summits = {5};
        int n = 7;
        int[][] paths = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
        int[] gates = {3, 7};
        int[] summits = {1, 5};

        int[] answer = solution.solution(n,paths,gates,summits);


        System.out.println("\n");
        System.out.println(Arrays.toString(answer));

    }

}
