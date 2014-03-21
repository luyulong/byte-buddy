package com.blogspot.mydailyjava.bytebuddy.instrumentation;

import com.blogspot.mydailyjava.bytebuddy.instrumentation.type.TypeDescription;
import com.blogspot.mydailyjava.bytebuddy.utility.MockitoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldAccessorEqualsHashCodeTest {

    private static final String FOO = "foo", BAR = "bar";
    private static final Class<?> TYPE = Void.class, OTHER_TYPE = Object.class;

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    private TypeDescription first, second;

    @Test
    public void testBeanPropertyFieldAccessor() throws Exception {
        assertThat(FieldAccessor.ofBeanProperty().hashCode(), is(FieldAccessor.ofBeanProperty().hashCode()));
        assertThat(FieldAccessor.ofBeanProperty(), is(FieldAccessor.ofBeanProperty()));
        assertThat(FieldAccessor.ofBeanProperty().in(first).hashCode(), is(FieldAccessor.ofBeanProperty().in(first).hashCode()));
        assertThat(FieldAccessor.ofBeanProperty().in(first), is(FieldAccessor.ofBeanProperty().in(first)));
        assertThat(FieldAccessor.ofBeanProperty().in(first).hashCode(), not(is(FieldAccessor.ofBeanProperty().hashCode())));
        assertThat((FieldAccessor) FieldAccessor.ofBeanProperty().in(first), not(is((FieldAccessor) FieldAccessor.ofBeanProperty())));
        assertThat(FieldAccessor.ofBeanProperty().in(first).hashCode(), not(is(FieldAccessor.ofBeanProperty().in(second).hashCode())));
        assertThat(FieldAccessor.ofBeanProperty().in(first), not(is(FieldAccessor.ofBeanProperty().in(second))));
    }

    @Test
    public void testExplicitFieldAccessor() throws Exception {
        assertThat(FieldAccessor.ofField(FOO).hashCode(), is(FieldAccessor.ofField(FOO).hashCode()));
        assertThat(FieldAccessor.ofField(FOO), is(FieldAccessor.ofField(FOO)));
        assertThat(FieldAccessor.ofField(FOO).hashCode(), not(is(FieldAccessor.ofField(BAR).hashCode())));
        assertThat(FieldAccessor.ofField(FOO), not(is(FieldAccessor.ofField(BAR))));
        assertThat(FieldAccessor.ofField(FOO).in(first).hashCode(), is(FieldAccessor.ofField(FOO).in(first).hashCode()));
        assertThat(FieldAccessor.ofField(FOO).in(first), is(FieldAccessor.ofField(FOO).in(first)));
        assertThat(FieldAccessor.ofField(FOO).in(first).hashCode(), not(is(FieldAccessor.ofField(FOO).hashCode())));
        assertThat((FieldAccessor) FieldAccessor.ofField(FOO).in(first), not(is((FieldAccessor) FieldAccessor.ofField(FOO))));
        assertThat(FieldAccessor.ofField(FOO).in(first).hashCode(), not(is(FieldAccessor.ofField(FOO).in(second).hashCode())));
        assertThat(FieldAccessor.ofField(FOO).in(first), not(is(FieldAccessor.ofField(FOO).in(second))));
        assertThat(FieldAccessor.ofField(FOO).defineAs(TYPE).hashCode(), is(FieldAccessor.ofField(FOO).defineAs(TYPE).hashCode()));
        assertThat(FieldAccessor.ofField(FOO).defineAs(TYPE), is(FieldAccessor.ofField(FOO).defineAs(TYPE)));
        assertThat(FieldAccessor.ofField(FOO).defineAs(TYPE).hashCode(), not(is(FieldAccessor.ofField(FOO).defineAs(OTHER_TYPE).hashCode())));
        assertThat(FieldAccessor.ofField(FOO).defineAs(TYPE), not(is(FieldAccessor.ofField(FOO).defineAs(OTHER_TYPE))));
    }
}
