import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

// 예외 상황들에 대한 테스트 구현할 것
public class KioskTest {
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private final Map<String, String> inputs = new HashMap<>();
    private static final InputStream originalIn = System.in;

    @BeforeAll
    public static void setInputs() {
        // inputs 채워넣을 것
    }

    @BeforeEach
    public void setIO() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void rollbackIO() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testConfirmOrder() {

    }

    @Test
    public void testDeleteOrder() {

    }

    @Test
    public void testDisplayMenu() {

    }

    @Test
    public void testDisplayMenus() {

    }

    @Test
    public void testStart() {

    }
}
