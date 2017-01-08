package CodeRaguet.fraser.unit;

import CodeRaguet.fraser.Frase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FraseTest {

    @Test
    public void shouldBeEqualToSameFrase() {
        assertThat(new Frase("frase")).isEqualTo(new Frase("frase"));
    }
}
