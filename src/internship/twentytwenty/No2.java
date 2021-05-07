package internship.twentytwenty;

public class No2 {

	static String[] numbers = new String[500]; // 숫자 리스트
	static char[] operators = new char[499]; // 연산자 리스트

	public static void main(String[] args) {

		String expression = "100-200*300-500+20";
		long answer = 0;
		// String[] stringNums = new String[350];
		String numTemp = ""; // 숫자구분을 위한 임시변수
		int numIndex = 0, operIndex = 0; // 숫자 및 연산자 배열의 인덱스
		String[] operPriority = { "+-*", "+*-", "-+*", "-*+", "*+-", "*-+" };
		// 우선순위 1. +-*, 2. +*-, 3. -+*, 4. -*+, 5. *+-, 6. *-+

		// 문자열을 문자로 나누워 비교 및 숫자배열 & 연산자배열로 구분
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (c >= 48 && c <= 57)
				numTemp += c; // 숫자만 저장

			else {
				numbers[numIndex++] = numTemp; // 숫자를 문자열배열에 저장
				operators[operIndex++] = c; // 연산자 저장
				numTemp = "";
			}
			// 마지막 숫자
			numbers[numIndex] = numTemp;
		}

		/*
		 * 숫자를 문자열 배열에서 long배열로 변경 for(int i=0; i<stringNums.length; i++) {
		 * if(stringNums[i] != null) numbers[i] = Integer.parseInt(stringNums[i]); }
		 */

		// 우선순위에 따라 연산하여 결과배열에 저장
		for (int i = 0; i < operPriority.length; i++) {
			long newAnswer = operExpression(operPriority[i]);
			if (newAnswer < 0)
				newAnswer = Math.abs(newAnswer);
			answer = answer > newAnswer ? answer : newAnswer;
		}

		//return answer;
		System.out.println(answer);
	}

	private static long operExpression(String operPriority) {

		String[] cloneNums = numbers.clone();
		long result = 0;

		for (int i = 0; i < operPriority.length(); i++) {
			char c = operPriority.charAt(i);
			for (int j = 0; j < operators.length; j++) {
				if (operators[j] == c) {
					calculation(cloneNums, c, j);
				}
			}
		}

		for (int i = 0; i < cloneNums.length; i++) {
			if (!("del".equals(cloneNums[i])) && cloneNums[i] != null)
				result = Long.parseLong(cloneNums[i]);
		}

		return result;
	}

	private static void calculation(String[] cNums, char operator, int index) {

		long operLandL, operLandR;
		int indexL = index, indexR = index + 1;

		while (indexL > 0 && "del".equals(cNums[indexL])) {
			indexL--;
		}

		operLandL = Long.parseLong(cNums[indexL]);
		operLandR = Long.parseLong(cNums[indexR]);

		switch (operator) {
		case '+':
			cNums[indexL] = String.valueOf(operLandL + operLandR);
			cNums[indexR] = "del";
			break;
		case '-':
			cNums[indexL] = String.valueOf(operLandL - operLandR);
			cNums[indexR] = "del";
			break;
		case '*':
			cNums[indexL] = String.valueOf(operLandL * operLandR);
			cNums[indexR] = "del";
			break;
		}
	}

}