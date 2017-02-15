package sondow.fishies;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AquariumBuilderTest {

	@Test
	public void testBuildSimpleAquariums() {

		List<Integer> values = Arrays.asList(2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1);
		Randomizer random = new TestRandomizer(values);
		AquariumBuilder builder = new AquariumBuilder(random);

		String aquarium = builder.build();
		assertEquals("       🌿🏰", aquarium);
	}

}
