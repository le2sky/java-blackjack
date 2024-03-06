package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        String input = readLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return split(input);
    }

    private List<String> split(String input) {
        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .toList();
    }

    private String readLine(String message) {
        System.out.println(message);

        return scanner.nextLine().trim();
    }
}