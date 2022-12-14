package programmers.hikingCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * 내가 파악하지 못한 조건들
 * 1. return은 [산봉우리 번호, intensity]이다.
 * 2. intensity가 같은 경로가 있을 경우 산봉우리 번호가 낮은것을 반환한다.
 *
 */
public class Solution_old_3 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // paths에서 각 지점별 인덱스 저장.
        HashMap<Integer, List<Integer>> pathsInfo = new HashMap<>();
        
        // 각 지점별 index 정보 저장
        for (Integer i = 1; i <= n; i++) {
            for (int j =0; j < paths.length; j++){
                List<Integer> pathsIndex = pathsInfo.get(i)== null ? new ArrayList<>() : pathsInfo.get(i);
                if (i==paths[j][0]) {
                    pathsIndex.add(j);
                }
                pathsInfo.put(i,pathsIndex);
            }
        }
        System.out.printf("pathsInfo: %s\n", pathsInfo.toString());
        // gates 별 루트를 구해야한다.

        int[] answer = {0,0};
        for (int gate : gates) {
            // 각 gate별 summit과 intensity를 담을 변수
            int[] courseInfo = getAnswer(answer, gate, paths, summits, pathsInfo);

            System.out.printf("111answer info: %d\n", answer[0]);
            System.out.printf("111answer info: %d\n", answer[1]);

            answer[0] = courseInfo[0];
            answer[1] = courseInfo[1];

            if (gates.length > 1) {
                // 1. 각 게이트별 intensity 크기 비교 작은 것을 answer에 담는다.
                answer[0] = answer[1] < courseInfo[1]
                        ? answer[0]
                        : answer[1] == courseInfo[1] && answer[0] > courseInfo[0]
                            ? courseInfo[0]
                            : answer[0];

                answer[1] = answer[1] < courseInfo[1]
                        ? answer[1]
                        : answer[1] == courseInfo[1] && answer[0] > courseInfo[0]
                        ? courseInfo[1]
                        : answer[1];
            }

            // 2. 같을 경우에는 정상 번호가 작은 것.

        }


        return answer;
    }

    private int[] getAnswer(int[] answer, int gate, int[][] paths, int[] summits, HashMap<Integer, List<Integer>> pathsInfo) {
        // 해당 gate에 대한 최소 intensity를 담은 배열을 초기화.
        System.out.printf("<<<<<<<<<<<<<<<<<<<<<<<<< gate: %d >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n", gate);
        // 이 gate에 연결된 spot들이 있다. 이는 위에서 pathsInfo에서 미리 index번호를 List에 저장해두었다.
        for(Integer index : pathsInfo.get(gate)) {
            for(int summit : summits) {
                if(paths[index][0]==summit){
                    System.out.printf("summit: %d\n", summit);
                    answer[0] = summit;
                    return answer;
                }
            }
            System.out.printf("index info: %d\n", index);
            int[] spotInfo = {0,0};
            Integer nextSpot = paths[index][1];
            System.out.printf("nextSpot index info: %d\n", nextSpot);
            spotInfo[1] = paths[index][2];
            System.out.printf("spot info per index - summit : {%d} %d\n", index, spotInfo[0]);
            System.out.printf("spot info per index - intensity : {%d} %d\n", index, spotInfo[1]);
            System.out.printf("bigger - intensity : %d\n", spotInfo[1]);

            System.out.printf("-------------------has path----------------------{%d} to {%d}\n", gate, nextSpot);
            int[] nextSpotInfo = getAnswer(answer, nextSpot,paths, summits, pathsInfo);
            spotInfo[1] = spotInfo[1] > nextSpotInfo[1] ? spotInfo[1] : nextSpotInfo[1];



            answer[0] = answer[0] == 0 ? spotInfo[0] : spotInfo[1] > answer[1] ?  answer[0] : spotInfo[0];
            answer[1] = answer[1] == 0 ? spotInfo[1] : spotInfo[1] > answer[1] ? answer[1] : spotInfo[1];

            System.out.printf("answer info per index - summit : {%d} %d\n", index, answer[0]);
            System.out.printf("answer info per index - intensity : {%d} %d\n", index, answer[1]);
        }

        return answer;
    }

}
/*
아이디어
1. paths의 길이가 얼마나 길지 모르는데 각 지점별로 다음 지점과 소요 시간을 구하기 위해 매번 for문을 돌리기에는
성능이 좋지 않을 수 있다. 그래서, 처음에 각 지점별이 필요한 정보들만 따로 정리한 후 gates 부터 시작해
필요한 값에서만 for문을 돌리려고한다.
-> 그래서 생각한 데이터 포맷이
HashMap<String, List<Integer>> 이다. 배열만 사용하고 싶었지만... 베열은 크기를 선언해야한다는 단점에
초기 for문에서 구할 수 없고, 단순히 크기만을 얻기위해 for문을 사용하기에는 아쉽다고 생각했다.
List<Integer>에는 paths의 index에 대한 정보를 담는다.

2. paths부터 시작해서 summits까지 가면서 가장 큰 intensity를 구하는 메소드를 태운다. 다만 각 지점별 가지는
여러 경로의 분기또한 for문을 돌리고 구해진 intensity중 값이 적은 것을 구한다.


 */