package programmers;

import programmers.que.Solution_old_2;

public class Main {
    public static void main(String[] args) {

        // 두 큐 합 같게하기
        Solution_old_2 solution = new Solution_old_2();
//        int[] queue1 = {3,2,7,2};
//        int[] queue2 = {4,6,5,1};
//        int[] queue1 = {1,2,1,2};
//        int[] queue2 = {1,10,1,2};
//        int[] queue1 = {1,1};
//        int[] queue2 = {1,5};
        int[] queue1 = {2,4,6,8};
        int[] queue2 = {1,1,1,1};

        int answer = solution.solution(queue1, queue2);
        System.out.println(answer);

    }

}
