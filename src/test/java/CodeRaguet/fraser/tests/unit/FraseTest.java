package CodeRaguet.fraser.tests.unit;

import CodeRaguet.fraser.model.Frase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FraseTest {

    @Test
    public void shouldBeEqualToSameFrase() {
        assertThat(new Frase("frase")).isEqualTo(new Frase("frase"));
    }
}
