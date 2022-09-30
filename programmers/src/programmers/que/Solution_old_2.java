package programmers.que;

/**
 * 시간 초과로 Fail
 */
public class Solution_old_2 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int queue1Sum = 0;
        int queue2Sum = 0;
        do {
            queue1Sum = 0;
            queue2Sum = 0;
            for (int i = 0; i < queue1.length; i++) {
                queue1Sum += queue1[i];
            }
            for (int i = 0; i < queue2.length; i++) {
                queue2Sum += queue2[i];
            }
            // 큐의 합이 각각 짝수, 홀수라면 같아질 수 없다.
            if (queue1Sum%2 != queue2Sum%2) {
                return -1;
            }
            if (queue1Sum == queue2Sum) {
                break;
            } else if(queue1Sum > queue2Sum) {
                if(queue1.length == 1 && (queue1Sum > queue2Sum)){
                    answer = -1;
                    break;
                }
                int[] tempQueue1 = new int[queue1.length-1];
                int[] tempQueue2 = new int[queue2.length+1];
                for (int i=0;i<tempQueue1.length;i++) {
                    tempQueue1[i] = queue1[i+1];
                }
                for (int i=0;i<queue2.length;i++){
                    tempQueue2[i] = queue2[i];
                }
                tempQueue2[tempQueue2.length-1]=queue1[0];
                queue1 = tempQueue1;
                queue2 = tempQueue2;
                answer++;
            }else {
                if(queue2.length == 1 && (queue2Sum > queue1Sum)){
                    answer = -1;
                    break;
                }
                int[] tempQueue1 = new int[queue2.length-1];
                int[] tempQueue2 = new int[queue1.length+1];
                for (int i=0;i<tempQueue1.length;i++) {
                    tempQueue1[i] = queue2[i+1];
                }
                for (int i=0;i<queue1.length;i++){
                    tempQueue2[i] = queue1[i];
                }
                tempQueue2[tempQueue2.length-1]=queue2[0];
                queue1 = tempQueue1;
                queue2 = tempQueue2;
                answer++;
            }


        }while (true);

        return answer;
    }
}
/*
 * [Old Ideas]
 *  // {1,5 , 4} {1,1,2}
    // 이놈은 양쪽이 짝수이지만 뭔짓거리를 해도 합이 같을 수 없다.
    // 특징 - 적은 숫자 개수

    // 1 1 4 9  // 1 1 1
    //  9 // 4 1 1 1 1 1


    // 1 2 3 7 7 8 11       // 1 1 1
    // 8 11 1 1 // 1 1 2 3 7 7
    // 합이 작은 큐에 큐 배열의 원소만큼 곱한 것이 합이 큰 큐의 가장 큰 원소보다 작다면. 합이 같을 수 없다. -> 위의 예시로 아닌 것을 확인.

    // 1 7 / 1 1
    // 7 나누기 2는 3(두 큐의 합보다 크다.)에 1
    // 1 5 / 1 1
    // 5 나누기 2는 2(두 큐의 합과 같다.)에 1
    // 1 3 / 1 1
    // 3 나누기 2는 1(두 큐의 합보다 작다.)에 1
    // 합이 작은 수로 합이 큰 큐의 큰 원소를 나누었을 때 몫이 작은 수의 큐 보다 크거나 같다면? -> 1이 백개, 101 / 1인 경우에 fail임.
 *
 */