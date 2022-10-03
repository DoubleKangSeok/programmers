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
 *
 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 * 문제 잘못읽음 폐기.
 */
public class Solution_old_2 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // paths에서 각 지점별 인덱스 저장.
        HashMap<Integer, List<Integer>> pathsInfo = new HashMap<Integer, List<Integer>>();
        
        // 각 지점별 index 정보 저장
        // 아... paths 길이가 아니라 n길이로 했어야함
        for (Integer i = 1; i <= n; i++) {
            List<Integer> pathsIndex = pathsInfo.get(i)== null ? new ArrayList<Integer>() : pathsInfo.get(i);
            for (int j =0; j < paths.length; j++){
                if (i==paths[j][0]) {
                    pathsIndex.add(j);
                }
            }
            pathsInfo.put(i,pathsIndex);
        }

        int[] answer = new int[2];
        // 출발지 돌리기
        for (int gate : gates) {
            int intensity = getIntensity(0,gate,paths,summits,pathsInfo);
            answer[0] = answer[1] == 0 ? gate : (answer[1] < intensity ? answer[0] : gate);
            answer[1] = answer[1] == 0 ? intensity : (answer[1] < intensity ? answer[1] : intensity);
        }


        return answer;
    }

    private int getIntensity(int intensity, Integer currentSpot, int[][] paths, int[] summits, HashMap<Integer, List<Integer>> pathsInfo) {
        for(Integer index : pathsInfo.get(currentSpot)){
            int nextSpot = paths[index][1];
            intensity = intensity > paths[index][2] ? intensity : paths[index][2];
            for(int summit : summits) {
                if(nextSpot!=summit) {
                    intensity = getIntensity(intensity, nextSpot, paths, summits, pathsInfo);
                }
            }
        }

        return intensity;
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