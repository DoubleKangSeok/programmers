package programmers.que;

/**
 * 시간 초과로 Fail
 */
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int queue1PopNum = 0;
        int queue2PopNum = 0;
        long queue1Sum = 0;
        long queue2Sum = 0;
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

        while(queue1Sum!=queue2Sum) {
            if(queue1PopNum/queue1.length + queue2PopNum/queue2.length == 2) {
                return -1;
            }else if (queue1Sum > queue2Sum) {
                long num = (queue1PopNum/queue1.length) > 0 ? queue2[(queue1PopNum%queue1.length)] : queue1[queue1PopNum];
                queue1Sum -= num;
                queue2Sum += num;
                queue1PopNum++;
            }else if (queue1Sum < queue2Sum){
                long num = (queue2PopNum/queue2.length) > 0 ? queue1[(queue2PopNum%queue2.length)] : queue2[queue2PopNum];
                queue1Sum += num;
                queue2Sum -= num;
                queue2PopNum++;
            }
            answer++;
        };

        return answer;
    }
}