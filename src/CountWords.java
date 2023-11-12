import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountWords {
    private final String path;

    public CountWords(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        this.path = path;
    }

    public List<String> getResult() throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String row;
            Pattern pattern = Pattern.compile("(?<word>\\w+)(\\s*|\\p{Punct})");
            while ((row = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(row);
                while (matcher.find()) {
                    map.put(matcher.group("word"), map.getOrDefault(matcher.group("word"), 0) + 1);
                }
            }
        }
        List<String> wordsAndQty = new ArrayList<>();
        map.forEach((key, value) -> wordsAndQty.add(key + " " + value));
        return wordsAndQty;
    }
}
