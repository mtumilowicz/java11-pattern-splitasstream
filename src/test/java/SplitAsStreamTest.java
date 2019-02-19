import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-02-19.
 */
public class SplitAsStreamTest {

    @Test
    public void splitAsStream_letters() {
        var pattern = Pattern.compile("-");

        var top3Letters = pattern.splitAsStream("a-b-c-d-e-f-g")
                .sorted()
                .limit(3)
                .collect(Collectors.toList());

        assertThat(top3Letters, is(List.of("a", "b", "c")));
    }

    @Test
    public void splitAsStream_wordsMap() {
        var pattern = Pattern.compile(" ");

        var string = "go been go hi there go hi";

        var wordsMap = pattern.splitAsStream(string)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        assertThat(wordsMap.size(), is(4));
        assertThat(wordsMap.get("go"), is(3L));
        assertThat(wordsMap.get("been"), is(1L));
        assertThat(wordsMap.get("hi"), is(2L));
        assertThat(wordsMap.get("there"), is(1L));
    }
}
