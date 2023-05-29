import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {

	private TestDemo testDemo = new TestDemo();
	
	@BeforeEach
	void setUp() throws Exception {
		TestDemo testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean exceptException) {
		if(!exceptException) {
			assertThat(testDemo.addPositive(a,  b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> 
				testDemo.addPositive(a, b))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}
	public static Stream<Arguments> argumentsForAddPositive() {
		//@formatter:off
		return Stream.of(
		arguments(1, 2, 3, false),
		arguments(2, 4, 6, false));
		//@formatter:on
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect () {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
	
}
