import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Tester {

    public static void start(Class clazz) throws InvocationTargetException, IllegalAccessException {
        Method beforeMethod = null;
        Method afterMethod = null;
        ArrayList<Method> testMethods = new ArrayList<>();

        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            } else if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) {
                    beforeMethod = m;
                } else {
                    throw new RuntimeException("Должен быть однин метод @BeforeSuite");
                }
            } if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) {
                    afterMethod = m;
                } else {
                    throw new RuntimeException("Должен быть однин метод @AfterSuite");
                }
            }
        }

        if (beforeMethod != null) {
            beforeMethod.invoke(object);
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        for (Method m : testMethods) {
            m.invoke(object);
        }

        if (afterMethod != null) {
            afterMethod.invoke(object);
        }
    }
}