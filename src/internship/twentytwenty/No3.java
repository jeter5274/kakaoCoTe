package internship.twentytwenty;

import java.util.HashMap;
import java.util.Map;

public class No3 {
	public static void main(String[] args) {
		
		String[] gems = { "AA", "AB", "AC", "AA", "AC" };

		int start = 0, end = 0, gemsCnt = 0;
		Map<String, Integer> rangeDisplay = new HashMap<String, Integer>();

		// 보석 갯수 파악
		for (int i = 0; i < gems.length; i++) {
			int checkCnt = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (gems[i].equals(gems[j]))
					checkCnt++;
			}
			if (checkCnt == 0)
				gemsCnt++;
		}

		// 보석 범위 찾기
		for (int i = 0; i < gems.length; i++) {

			if (rangeDisplay.get(gems[i]) == null) {
				// 보석이 키값에 존재하지 않으면 추가
				rangeDisplay.put(gems[i], 1);
				end++;

			} else {
				if (gems[start].equals(gems[i])) {
					// 시작위치의 보석과 지금 보석가 같을 경우
					start++;
					while (rangeDisplay.get(gems[start]) > 1) {
						// 시작위치 +1, 키의 값 -1
						rangeDisplay.replace(gems[start], rangeDisplay.get(gems[start]) - 1);
						start++;
					}
					end++;
				} else {
					// 키의 값 +1
					rangeDisplay.replace(gems[i], rangeDisplay.get(gems[i]) + 1);
					end++;
				}

			}

			// 보석의 갯수를 충족하면 반복을 멈춤
			if (rangeDisplay.size() == gemsCnt)
				break;
		}

		int[] answer = { start + 1, end };
		//return answer;
		System.out.println(answer[0]+"에서 "+answer[1]+"까지 다 주세요!");
	}
}
