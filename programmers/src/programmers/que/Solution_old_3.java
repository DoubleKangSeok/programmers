package programmers.que;

public class Solution_old_3 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int queue1PopNum = 0;
        int queue2PopNum = 0;
        int queue1Sum = 0;
        int queue2Sum = 0;
        int queue1Length = queue1.length;
        int queue2Length = queue2.length;

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

        do {
            if (queue1Sum == queue2Sum) {
                break;
            }else if (queue1Sum > queue2Sum) {
                if(queue1Length == 1){
                    return -1;
                }
                int num = (queue1PopNum/queue1.length) > 0 ? queue2[queue1PopNum%queue1.length] : queue1[queue1PopNum%queue1.length];
                queue1Sum -= num;
                queue2Sum += num;
                queue1PopNum++;
                queue1Length--;
                queue2Length++;
            }else {
                if(queue2Length == 1){
                    return -1;
                }
                int num = (queue1PopNum/queue2.length) > 0 ? queue1[queue2PopNum%queue2.length] : queue2[queue2PopNum%queue2.length];
                queue1Sum += num;
                queue2Sum -= num;
                queue2PopNum++;
                queue1Length++;
                queue2Length--;
            }
            answer++;

        }while (true);

        return answer;
    }
}
/*
 * [Ideas]
 * 1. 배열을 재구축하는 것은 시간초과가 이루어진다.
 * 2. 그래서 나는 배열 재구축을 가상의 느낌으로 구현하려고한다.
 * ex)  q1 = [2,4,6,8], q2 = [1,1,1,1] 이상태에서 합이 큰놈은 q1이다. 그러면 이놈에서 pop 하여 q2에 insert 해야하는데 재구축을 하지 않고 가상으로 재구축을 구현하려면
 * q1에서 pop이 이루어질때마다 수가 증가하는 q1PopNum이 필요하다. 최초 popNum=0 상태에서 q1 -> q2로 pop을 한다면
 * q2는 [1,1,1,1, q1[popNum=0]]의 형태를 가진셈이다.
 * 이후 q1PopNum++를 수행.
 *
 * pop 이전
 * answer = 0
 * q1PopNum = 0%4
 * q2PopNum = 0%4(length)
 * q2 원소의 합 = q2[0] + q2[1] + q2[2] + q2[3] = 4
 * q1 원소의 합 = q1[0] + q1[1] + q1[2] + q1[3] = 20
 *
 * pop 발생
 * answer = 0
 * q1PopNum = 0
 * q2PopNum = 0
 * q2 원소의 합 += q1[0]    6
 * q1 원소의 합 -= q1[0]    18
 * answer++
 * q1PopNum++;
 *
 * pop 발생
 * answer = 1
 * q1PopNum = 1
 * q2PopNum = 0
 * q2 원소의 합 += q1[1]; = 10
 * q1 원소의 합 -= q1[1]; = 14
 * answer++
 * q1PopNum++;
 *
 * pop 발생
 * answer = 2
 * q1PopNum = 2
 * q2PopNum = 0
 * q2 원소의 합 += q1[2]; 16
 * q1 원소의 합 -= q1[2]; 8
 * answer++
 * q1PopNum++;
 *
 * pop 발생
 * answer = 3
 * q1PopNum = 3
 * q2PopNum = 0
 * q2 원소의 합 -= q2[0]; 16 - 1 = 15
 * q1 원소의 합 += q2[0]  8 + 1 = 9
 * answer++;
 * q2PopNum++;
 *
 * pop 발생
 * answer = 4
 * q1PopNum = 3
 * q2PopNum = 1
 * q2 원소의 합 -= q2[1] 15 - 1 = 14
 * q1 원소의 합 += q2[1] 9 + 1 = 10
 * answer++;
 * q2PopNum++;
 *
 * pop 발생
 * answer = 5
 * q1PopNum = 3
 * q2PopNum = 2
 * q2 원소의 합 -= q2[1] 14 - 1 = 13
 * q1 원소의 합 += q2[1] 10 + 1 = 11
 * answer++;
 * q2PopNum++;
 *
 * pop 발생
 * answer = 6
 * q1PopNum = 3
 * q2PopNum = 3
 * q2 원소의 합 -= q2[1] 13 - 1 = 12
 * q1 원소의 합 += q2[1] 11 + 1 = 12
 * break;
 */