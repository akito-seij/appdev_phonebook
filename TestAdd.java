import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestAdd
{
    private Phonebook pb;

    public TestAdd()
    {
        pb = new Phonebook();
    }

    @Test
    public void test1()
    {
        Person p = new Person("123", "Juan", "Dela Cruz", "Male", "Faculty", "12345", 63, 81);
        pb.insert(p);
        assertEquals(pb.getContactAtIndex(0).compareTo(p), 0);
    }

    @Test
    public void test2()
    {
        Person p1 = new Person("123", "Juan", "Dela Cruz", "Male", "Faculty", "12345", 63, 81);
        Person p2 = new Person("321", "Maria", "Clara", "Female", "Maiden", "18721", 63, 122);
        Person p3 = new Person("67667", "Jose", "Rizal", "Male", "Makata", "19911", 60, 12);
        pb.insert(p1);
        pb.insert(p2);
        pb.insert(p3);
        assertEquals(pb.getContactAtIndex(0).getFullName(), "Maria Clara");
        assertEquals(pb.getContactAtIndex(2).getFullName(), "Jose Rizal");
    }

    @Test
    public void test3()
    {
        Person p1 = new Person("123", "Juan", "Dela Cruz", "Male", "Faculty", "12345", 63, 81);
        Person p2 = new Person("321", "Maria", "Clara", "Female", "Maiden", "18721", 63, 122);
        Person p3 = new Person("67667", "Jose", "Rizal", "Male", "Makata", "19911", 60, 12);
        pb.insert(p1);
        pb.insert(p2);
        pb.insert(p3);
        assertEquals(null, pb.getContactAtIndex(3));
    }

    @Test
    public void test4()
    {
        Person p1 = new Person("123", "Juan", "Dela Cruz", "Male", "Faculty", "12345", 63, 81);
        Person p2 = new Person("321", "Maria", "Clara", "Female", "Maiden", "18721", 63, 122);
        Person p3 = new Person("67667", "Jose", "Rizal", "Male", "Makata", "19911", 60, 12);
        Person p4 =
                new Person("11919", "Charlizz", "Betista", "Male", "Programmer", "10091", 670, 195);
        Person p5 = new Person("86711", "David", "Teeger", "Male", "Teacher", "997751", 84, 100);
        pb.insert(p5);
        pb.insert(p2);
        pb.insert(p4);
        pb.insert(p1);
        pb.insert(p3);
        assertEquals(pb.getContactAtIndex(4).getFullName(), "David Teeger");
        assertEquals(pb.getContactAtIndex(0).getFullName(), "Charlizz Betista");
    }
}
