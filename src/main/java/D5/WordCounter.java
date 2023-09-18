package D5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    private final String text;

    public WordCounter(String text) {
        this.text = text;
    }

    public int countWords() {
        // 使用正则表达式匹配单词，包括连字符（-）
        Pattern pattern = Pattern.compile("[\\p{L}-]+", Pattern.UNICODE_CHARACTER_CLASS);

        Matcher matcher = pattern.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String inputText = "This is a sample text. 这是一个示例文本. Esto es un texto de ejemplo. " +
                "re-enter co-operate well-known";

        WordCounter wordCounter = new WordCounter(inputText);

        int wordCount = wordCounter.countWords();
        System.out.println("单词数量为：" + wordCount);
    }
}
