package com.web.D5;

import java.text.BreakIterator;
import java.util.Locale;

public class  MultilingualWordCounter {
    private final String text;

    public MultilingualWordCounter(String text) {
        this.text = text;
    }

    public int countWords() {
        BreakIterator breakIterator = BreakIterator.getWordInstance(Locale.US);
        breakIterator.setText(text);

        int count = 0;
        int lastIndex = 0;

        while (breakIterator.next() != BreakIterator.DONE) {
            int current = breakIterator.current();
            String word = text.substring(lastIndex, current).trim();
            if (!word.isEmpty()) {
                count++;
            }
            lastIndex = current;
        }

        return count;
    }

    public static void main(String[] args) {
        String inputText = "This is a sample text. 这是一个示例文本. Esto es un texto de ejemplo.";

        MultilingualWordCounter wordCounter = new MultilingualWordCounter(inputText);

        int wordCount = wordCounter.countWords();
        System.out.println(inputText);
        System.out.println("单词数量为：" + wordCount);
    }
}
