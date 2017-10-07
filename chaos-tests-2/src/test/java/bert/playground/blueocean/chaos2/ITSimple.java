package bert.playground.blueocean.chaos2;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bert.playground.blueocean.Calculator;

public class ITSimple {

	private Calculator calculator;

	@Before
	public void initCalculator() {
		this.calculator = new Calculator();
	}

	@Test
	public void shouldAddLargeNumbers() {
		Assert.assertThat(calculator.add(2000, 3000), CoreMatchers.is(5000l));
	}
	
}
