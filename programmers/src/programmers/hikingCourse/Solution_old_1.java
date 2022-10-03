package programmers.hikingCourse;

/**
 * @author DoubleKangSeok
 * @description<br/>
 * 등산코스 정하기<br/>
 * <hr/>
 * 조건:<br/>
 * 1. 여러 출입구 중 한 곳에서 출발하여 산봉우리 중 한곳만 방문한 뒤 다시 원래의 출입구로 돌아와야한다.<br/>
 * 2. 산봉우리는 한곳만 들린다.<br/>
 * 3. 위 규칙 하에 휴식없이 등산하는 가장 짧은 시간인 intensity를 가진 코스를 구하라.<br/>
 * 4. 원래의 출입구로 돌아가더라도 다른 출입구를 경유해서 가는 것은 잘못된 등산 코스이다<br/>
 * <br/>
 * 변수: <br/>
 * - intensity: 휴식 없이 이동해야하는 시간
 * -
 *
 */
public class Solution_old_1 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[][] answers = new int[gates.length][2];

        // 이 로직은 paths에서 앞에있는 배열만 체크하고 들어간다.
        // [[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]]
        // gate가 1이라면 처음 [1, 2, 3]에서 start를 2로 만들고 j=0
        // start 2로 만나는 가장 빠른 배열은 [2, 3, 5] intensity는 5로 바뀌고 start는 3이된다.
        // 3이 만나는 가장 빠른 배열은 [3, 4, 4] intensity는 유지에 start는 4로 바뀐다.
        // 4가 만나는 가장 빠른 배열은 [4, 5, 3]
        // 5가 만나는 가장 빠른 배열은 [5, 6, 1]

        // 이 로직은 또한 summits인 정상을 고려하지 않았다
        // j= 0 으로 초기화 하는 것도 좋아보이지 않는다.
        for (int i = 0; i < gates.length; i++) {
            int intensity = 0;

            int start = gates[i];
            for (int j = 0; j < paths.length; j++) {
                if(start == paths[j][0]) {
                    start = paths[j][1];
                    intensity = paths[j][2] > intensity ? paths[j][2] : intensity;
                    j=0;
                }
            }
            answers[i] = new int[]{gates[i], intensity};
        }

        int[] answer = {};
        for(int i = 0; i < answers.length; i++) {
            answer[1] = answers[i][1] < summits[1] ? answers[i][1] : summits[1];
            answer[0] = answers[i][1] < summits[1] ? answers[i][0] : summits[0];
        }







        return answer;
    }
}
/*
아이디어
1. 주어진 paths와 n을 기반으로 가상 맵을 구현
2. gates를 가상 맵에서 분기 사용
3. 산봉우리 번호와 가장 낮은 intensity 반환

// 최단 시간 게이트 거르기 (여러 게이트 중 가장 짧은 intensity를 가진 것 혹은 같은 것 두 개이상을 걸러낸다)
-> 시작시 a게이트는 다음 연결지가 1시간, b가 다음 연결지 2시간 걸렸을 때 단순히 이렇게 걸러내는 것은 a가 다음 지점이후에 4~5시간씩만 걸릴 경우와
b가 다음 지점 이후 2~3시간 걸린 케이스를 계산하지 못한다.

// 지점수를 괜히 준게 아닐것이여...

// 시작점을 통해 각 지점을 이동하면서 가장 큰 intensity을 뱉어내는 메소드를 만들어보자.

// 아무래도 n을 그냥 준게 아닌것같다. -> 이놈은 곧 각 지점 번호까지 for문을 돌릴 수 있는 놈이다.
-> for문을 돌려서 각 지점별로 연결된 길의 개수를 파악할 수 있을 것이다.
각 지점이 갖고있는 지점의 수를 어떻게 저장할 것인가... HashMap을 사용해야하나.
아니면 이중 배열?

 */