package operations;

import operations.ListMergeSorter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListMergeSorterTest {
    @Test
    public void test1() {
        ListMergeSorter<Integer> sorter = new ListMergeSorter<>();

        List<Integer> listToSort = Arrays.asList(5, 2, 8, -100, 180, 1, 0);
        List<Integer> expected = Arrays.asList(-100, 0, 1, 2, 5, 8, 180);

        sorter.setComparator(Integer::compareTo);
        sorter.sort(listToSort);

        assertEquals(expected, listToSort);
    }

    @Test
    public void test2() {
        ListMergeSorter<Integer> sorter = new ListMergeSorter<>();

        List<Integer> listToSort = Arrays.asList(5, 2, 8, -100, 180, 1, 0);
        List<Integer> expected = Arrays.asList(180, 8, 5, 2, 1, 0, -100);

        sorter.setComparator((x, y) -> - Integer.compare(x, y));
        sorter.sort(listToSort);

        assertEquals(expected, listToSort);
    }

    @Test
    public void test3() {
        ListMergeSorter<String> sorter = new ListMergeSorter<>();

        List<String> listToSort = Arrays.asList("aca", "aab", "fly", "rabbit", "zzz", "dictionary", "love");
        List<String> expected = Arrays.asList("aab", "aca", "dictionary", "fly", "love", "rabbit", "zzz");

        sorter.setComparator(String::compareTo);
        sorter.sort(listToSort);

        assertEquals(expected, listToSort);
    }

    @Test
    public void test4() {
        ListMergeSorter<Integer> sorter = new ListMergeSorter<>();

        List<Integer> listToSort = new ArrayList<>();

        sorter.setComparator((x, y) -> - Integer.compare(x, y));

        sorter.sort(listToSort);

        assertEquals(new ArrayList<Integer>(), listToSort);
    }

    @Test
    public void test5() {
        ListMergeSorter<Integer> sorter = new ListMergeSorter<>();

        List<Integer> listToSort = new ArrayList<>();
        listToSort.add(189);

        sorter.setComparator((x, y) -> - Integer.compare(x, y));

        sorter.sort(listToSort);

        List<Integer> expected = Arrays.asList(189);

        assertEquals(expected, listToSort);
    }
}