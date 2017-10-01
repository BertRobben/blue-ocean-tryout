package bert.playground.blueocean;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator calculator;

	@Before
	public void initCalculator() {
		this.calculator = new Calculator();
	}

	@Test
	public void shouldAdd() {
		Assert.assertThat(calculator.add(2, 3), CoreMatchers.is(5l));
	}

	@Test
	public void shouldMutliply() {
		Assert.assertThat(calculator.multiply(2, 3), CoreMatchers.is(6l));
	}
}
