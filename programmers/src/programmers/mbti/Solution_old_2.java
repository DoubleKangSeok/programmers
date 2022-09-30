package programmers.mbti;

import java.util.HashMap;

public class Solution_old_2 {

        public String solution(String[] survey, int[] choices) {
            HashMap<String, Integer> calculateResult = new HashMap<>();
            for (int i = 0; i < survey.length; i++) {
                String key = "";
                int value = 0;
                if (choices[i] == 4) {
                    String compareTargetA = survey[i].substring(0, 1);
                    String compareTargetB = survey[i].substring(1);
                    int compareResult = compareTargetA.compareTo(compareTargetB);
                    key = compareResult > 0 ? compareTargetB : compareTargetA;
                    value = getScore(choices[i]);
                } else if (choices[i] > 4) {
                    key = survey[i].substring(1);
                    value = getScore(choices[i]);
                } else {
                    key = survey[i].substring(0, 1);
                    value = getScore(choices[i]);
                }

                if (calculateResult.get(key) == null) {
                    calculateResult.put(key, value);
                } else {
                    int sum = calculateResult.get(key) + value;
                    calculateResult.put(key, sum);
                }
            }

            System.out.println(calculateResult.toString());

            String[] index = {"RT","CF","JM","AN"};
            String[] answerArray = new String[4];

            for (int i = 0; i < survey.length; i++) {
                String keyA = survey[i].substring(0,1);
                String keyB = survey[i].substring(1);

                Integer valueA = calculateResult.get(keyA);
                Integer valueB = calculateResult.get(keyB);

                String result = null;
                Integer value = null;
                if(valueA==null && valueB==null){
                    for (int j=0;j<index.length;j++){
                        if(index[j].contains(result)) {
                            answerArray[j] = value != null
                                    ? result
                                    : (index[j].substring(0,1).compareTo(index[j].substring(1)) > 0 ? index[j].substring(1) : index[j].substring(0,1));
                        }
                    }
                }else if(valueA==null || valueB == null){
                    result = valueA == null ? keyB : keyA;
                    value = valueA == null ? valueB : valueA;
                }else if(valueA!=null && valueB!=null) {
                    if(valueA.equals(valueB)){
                        result = keyA.compareTo(keyB) > 0 ? keyB : keyA;
                        value = keyA.compareTo(keyB) > 0 ? valueB : valueA;
                    }else {
                        result = valueA > valueB ? keyA : keyB;
                        value = valueA > valueB ? valueA : valueB;
                    }

                }

                for (int j=0;j<index.length;j++){
                    if(index[j].contains(result)) {
                        answerArray[j] = value != null
                                ? result
                                : (index[j].substring(0,1).compareTo(index[j].substring(1)) > 0 ? index[j].substring(1) : index[j].substring(0,1));
                    }
                }
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
