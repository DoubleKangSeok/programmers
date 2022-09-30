package programmers.mbti;

import java.util.HashMap;

public class Solution {
    public static String[] index = {"RT","CF","JM","AN"};
    public String solution(String[] survey, int[] choices) {
        HashMap<String, Integer> calculateResult = new HashMap<>();
        for (int i = 0; i < survey.length; i++) {
            String key = "";
            int value = 0;
            if (choices[i] > 4) {
                key = survey[i].substring(1);
                value = getScore(choices[i]);
            } else if(choices[i] < 4){
                key = survey[i].substring(0, 1);
                value = getScore(choices[i]);
            }

            int sum = calculateResult.get(key) == null ? value : calculateResult.get(key) + value;
            calculateResult.put(key, sum);

        }


        String[] answerArray = new String[4];

        for (int i = 0; i < index.length; i++) {
            String keyA = index[i].substring(0,1);
            String keyB = index[i].substring(1);

            Integer valueA = calculateResult.get(keyA);
            Integer valueB = calculateResult.get(keyB);

            String result = "";
            if(valueA==null && valueB==null){
                result = keyA.compareTo(keyB) > 0 ? keyB : keyA;
            }else if(valueA==null || valueB == null){
                result = valueA == null ? keyB : keyA;
            }else if(valueA!=null && valueB!=null) {
                if(valueA.equals(valueB)){
                    result = keyA.compareTo(keyB) > 0 ? keyB : keyA;
                }else {
                    result = valueA > valueB ? keyA : keyB;
                }

            }


            answerArray[i] = result;

        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<answerArray.length;i++){
            sb.append(answerArray[i]);
        }

        String answer = sb.toString();
        return answer;
    }

    private int getScore(int choice) {
        int result = 0;
        switch(choice){
            case 1:
            case 7:
                result = 3;
                break;
            case 2:
            case 6:
                result = 2;
                break;
            case 3:
            case 5:
                result = 1;
                break;
            case 4:
                result = 0;
                break;
        }
        return result;
    }

}
