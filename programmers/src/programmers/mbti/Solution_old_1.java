package programmers.mbti;

import java.util.HashMap;

public class Solution_old_1 {
        public static void main(String[] args) {
            String[] survey = {"AN","CF","MJ", "RT", "NA"};
            int[] choices = {5,3,2,7,5};
            Solution_old_1 solution_1 = new Solution_old_1();
            String answer = solution_1.solution(survey,choices);
            System.out.println(answer);
        }


        public String solution(String[] survey, int[] choices) {
            HashMap<String,Integer> surveyResult = new HashMap<String,Integer>();
            for(int i=0;i<survey.length;i++) {
                if(choices[i]==4){
                    int compareResult = survey[i].substring(0,1).compareTo(survey[i].substring(1));
                    if(compareResult > 0 ){
                        if(surveyResult.get(survey[i].substring(1)) == null) {
                            surveyResult.put(survey[i].substring(1), getScore(choices[i]));
                        }
                    }else{
                        if(surveyResult.get(survey[i].substring(0,1)) == null) {
                            surveyResult.put(survey[i].substring(0, 1), getScore(choices[i]));
                        }
                    }
                }else if(choices[i]>4){
                    if(surveyResult.get(survey[i].substring(1)) == null){
                        System.out.println(survey[i].substring(1));
                        System.out.println(getScore(choices[i]));

                        surveyResult.put(survey[i].substring(1), getScore(choices[i]));
                    }else {
                        int sum = surveyResult.get(survey[i].substring(1)) +  getScore(choices[i]);
                        surveyResult.put(survey[i].substring(1), sum);
                    }
                }else{
                    if(surveyResult.get(survey[i].substring(0,1)) == null){
                        surveyResult.put(survey[i].substring(0,1), getScore(choices[i]));
                    }else {
                        int sum = surveyResult.get(survey[i].substring(0,1)) +  getScore(choices[i]);
                        surveyResult.put(survey[i].substring(0,1), sum);
                    }
                }

            }

            System.out.println(surveyResult.toString());

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<survey.length;i++) {
                Integer resultA = surveyResult.get(survey[i].substring(0,1));
                System.out.println(resultA);
                Integer resultB = surveyResult.get(survey[i].substring(1));
                System.out.println(resultB);
                if(resultA==null || resultB==null){
                    continue;
                }

                sb.append(resultA > resultB
                        ? survey[i].substring(0,1)
                        : (
                        resultA < resultB
                                ? survey[i].substring(1)
                                : (
                                survey[i].substring(0,1).compareTo(survey[i].substring(1)) > 0 ? survey[i].substring(1) : survey[i].substring(0,1)
                        )
                    )
                );
            }

            String answer = sb.toString();
            return answer;
        }

        private int getScore(int choice) {
            int result = 0;
            switch(choice){
                case 1:
                    result = 3;
                    break;
                case 2:
                    result = 2;
                    break;
                case 3:
                    result = 1;
                    break;
                case 4:
                    result = 0;
                    break;
                case 5:
                    result = 1;
                    break;
                case 6:
                    result = 2;
                    break;
                case 7:
                    result = 3;
                    break;
            }
            return result;
        }

}
