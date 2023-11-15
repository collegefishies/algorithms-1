import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    @Test
    @DisplayName("Search for Rightmost Occurrence in Repeating Array")
    void searchRightmostInRepeatingArray() {
        int[] array = {1, 2, 2, 2, 3};
        int searchValue = 2;
        int expectedIndex = 3; // rightmost occurrence of 2
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("Search for Non-Existent Element")
    void searchForNonExistentElement() {
        int[] array = {1, 2, 3};
        int searchValue = 4;
        int expectedIndex = -1; // 4 is not in the array
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("Search in Single Element Array")
    void searchInSingleElementArray() {
        int[] array = {1};
        int searchValue = 1;
        int expectedIndex = 0; // only element in the array
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("Search in Empty Array")
    void searchInEmptyArray() {
        int[] array = {};
        int searchValue = 1;
        int expectedIndex = -1; // array is empty
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("Search for Edge Elements")
    void searchForEdgeElements() {
        int[] array = {1, 2, 3, 4};
        assertEquals(0, BinarySearch.left(array, 1)); // leftmost occurrence of 1
        assertEquals(3, BinarySearch.left(array, 4)); // leftmost occurrence of 4
    }

    @Test
    @DisplayName("Search in Array with Negative Numbers")
    void searchInArrayWithNegativeNumbers() {
        int[] array = {-3, -2, 0, 1, 2};
        int searchValue = -2;
        int expectedIndex = 1; // leftmost occurrence of -2
        assertEquals(expectedIndex, BinarySearch.left(array, searchValue));
    }

    @Test
    @DisplayName("Search in Large Array")
    void searchInLargeArray() {
        int[] array = new int[1000]; // Large array of size 1000
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill with consecutive numbers
        }
        int searchValue = 500;
        assertEquals(500, BinarySearch.left(array, searchValue)); // leftmost occurrence of 500
    }

}
