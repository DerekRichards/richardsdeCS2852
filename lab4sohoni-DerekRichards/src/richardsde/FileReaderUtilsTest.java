/*
 * Course: CS2852
 * Spring 2021
 * Lab 4 - Call Stack
 * Name: Derek Richards
 * Created: 4/3/2021
 */
package richardsde;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderUtilsTest {

    @Test
    public void isVoidReturn(){
        assertTrue(FileReaderUtils.isVoidReturn("return"));
    }

    @Test
    public void isntVoid(){
        assertFalse(FileReaderUtils.isVoidReturn("return 1"));
    }

    @Test
    public void isVoidNonReturnStatement(){
        assertFalse(FileReaderUtils.isVoidReturn("int first()"));
    }

    @Test
    public void parseReturnValueIntReturn(){
        OptionalInt optionalInt = OptionalInt.empty();
        OptionalInt returnValue = FileReaderUtils.parseReturnValue("return 3");
        assertNotEquals(optionalInt, returnValue);
        assertEquals(OptionalInt.of(3), returnValue);
    }

    @Test
    public void parseReturnValueVoidReturn(){
        OptionalInt optionalInt = OptionalInt.empty();
        assertEquals(optionalInt, FileReaderUtils.parseReturnValue("return"));
    }

    @Test
    public void parseReturnValueNoReturn(){
        OptionalInt optionalInt = OptionalInt.empty();
        assertEquals(optionalInt, FileReaderUtils.parseReturnValue("int first()"));

    }

    @Test
    public void parseMethodNameIntNoParam(){
        Optional<String> stringOptional = Optional.empty();
        Optional<String> methodName = FileReaderUtils.parseMethodName("int first()");
        assertNotEquals(stringOptional, methodName);
        assertEquals(Optional.of("first"), methodName);
    }

    @Test
    public void parseMethodNameIntSingleParam(){
        Optional<String> stringOptional = Optional.empty();
        Optional<String> methodName = FileReaderUtils.parseMethodName("int first(4)");
        assertNotEquals(stringOptional, methodName);
        assertEquals(Optional.of("first"), methodName);
    }

    @Test
    public void parseMethodNameIntMultiParam(){
        Optional<String> stringOptional = Optional.empty();
        Optional<String> methodName = FileReaderUtils.parseMethodName("int first(4, 6, 3");
        assertNotEquals(stringOptional, methodName);
        assertEquals(Optional.of("first"), methodName);
    }

    @Test
    public void parseMethodNameVoidNoParam(){
        Optional<String> stringOptional = Optional.empty();
        Optional<String> methodName = FileReaderUtils.parseMethodName("void second()");
        assertNotEquals(stringOptional, methodName);
        assertEquals(Optional.of("second"), methodName);
    }

    @Test
    public void parseMethodNameVoidSingleParam(){
        Optional<String> stringOptional = Optional.empty();
        Optional<String> methodName = FileReaderUtils.parseMethodName("void second(4)");
        assertNotEquals(stringOptional, methodName);
        assertEquals(Optional.of("second"), methodName);
    }

    @Test
    public void parseMethodNameVoidMultiParam(){
        Optional<String> stringOptional = Optional.empty();
        Optional<String> methodName = FileReaderUtils.parseMethodName("void second(3, 4, 5");
        assertNotEquals(stringOptional, methodName);
        assertEquals(Optional.of("second"), methodName);
    }

    @Test
    public void parseNonMethod(){
        Optional<String> stringOptional = Optional.empty();
        assertEquals(stringOptional, FileReaderUtils.parseMethodName("void third"));
    }

    @Test
    public void parseArgumentsNonMethod(){
        int[] args = null;
        assertEquals(args, FileReaderUtils.parseArguments("void third"));
    }

    @Test
    public void parseArgumentsNoArgs() {
        int[] args = new int[0];
        assertEquals(args.length, FileReaderUtils.parseArguments("int first()").length);
    }

    @Test
    public void parseArgumentSingleArg() {
        int[] args = {1};
        int[] returned = FileReaderUtils.parseArguments("int first(1)");
        assertEquals(args[0], returned[0]);
    }

    @Test
    public void parseArgumentsMultiArgs() {
        int[] args = {4, 6, 3};
        int[] returned = FileReaderUtils.parseArguments("int second(4, 6, 3)");
        assertEquals(args[0], returned[0]);
        assertEquals(args[1], returned[1]);
        assertEquals(args[2], returned[2]);
    }

    @Test
    public void parseArgumentsInvalidArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> FileReaderUtils.parseArguments("int third(third)"));
    }

}