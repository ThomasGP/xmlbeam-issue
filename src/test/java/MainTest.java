import org.junit.Test;
import org.xmlbeam.XBProjector;
import org.xmlbeam.annotation.XBRead;

import java.util.Optional;

import static org.junit.Assert.*;

public class MainTest {

    public interface OptionalReturningProjection {
        @XBRead("/foo/bar")
        Optional<Integer> getSomeValue();
    }

    @Test
    public void testValueIsPresent() {
        OptionalReturningProjection projection = new XBProjector().projectXMLString("<foo><bar>1</bar></foo>", OptionalReturningProjection.class);
        Optional<Integer> value = projection.getSomeValue();
        assertTrue(value.isPresent());
        assertEquals(Integer.valueOf(1), value.get());
    }

    @Test
    public void testValueIsNotPresent() {
        OptionalReturningProjection projection = new XBProjector().projectXMLString("<foo><bar2>2</bar2></foo>", OptionalReturningProjection.class);
        Optional<Integer> value = projection.getSomeValue();
        assertFalse(value.isPresent());
    }
}
