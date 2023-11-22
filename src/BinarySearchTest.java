import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    @Test
    @DisplayName("ANY: Search for Any Occurrence in Repeating Array")
    void searchAnyInRepeatingArray() {
        int[] array = {1, 2, 2, 2, 3};
        int searchValue = 2;
        int resultIndex = BinarySearch.any(array, searchValue);
        assertEquals(searchValue, array[resultIndex], "The value at the found index should match the search value.");
    }

    @Test
    @DisplayName("ANY: Search for Any Occurrence in Single Element Array")
    void searchAnyInSingleElementArray() {
        int[] array = {1};
        int searchValue = 1;
        int resultIndex = BinarySearch.any(array, searchValue);
        assertEquals(searchValue, array[resultIndex], "The value at the found index should match the search value.");
    }

    @Test
    @DisplayName("ANY: Search for Any Occurrence in Non-Repeating Array")
    void searchAnyInNonRepeatingArray() {
        int[] array = {1, 3, 5, 7, 9};
        int searchValue = 7;
        int resultIndex = BinarySearch.any(array, searchValue);
        assertEquals(searchValue, array[resultIndex], "The value at the found index should match the search value.");
    }

    @Test
    @DisplayName("ANY: Search for Non-Existent Element")
    void searchAnyForNonExistentElement() {
        int[] array = {1, 2, 3, 5, 6};
        int searchValue = 4;
        int resultIndex = BinarySearch.any(array, searchValue);
        assertEquals(-1, resultIndex, "Should return -1 when the element is not found.");
    }

    @Test
    @DisplayName("RIGHT: Search for Rightmost Occurrence in Repeating Array")
    void searchRightmostInRepeatingArray() {
        int[] array = {1, 2, 2, 2, 3};
        int searchValue = 2;
        int expectedIndex = 3; // rightmost occurrence of 2
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("RIGHT: Search for Rightmost Occurrence in Repeating Array")
    void searchRightmostInRepeatingArray2() {
        int[] array = {1, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4};
        int searchValue = 2;
        int expectedIndex = 3; // rightmost occurrence of 2
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("RIGHT: Search for Rightmost Occurrence in Repeating Array")
    void searchRightmostInRepeatingArray3() {
        int[] array = {1, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4};
        int searchValue = 4;
        int expectedIndex = array.length - 1; // rightmost occurrence of 2
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }


    @Test
    @DisplayName("RIGHT: Search for Non-Existent Element")
    void searchForNonExistentElement() {
        int[] array = {1, 2, 3};
        int searchValue = 4;
        int expectedIndex = -1; // 4 is not in the array
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("ALL: Search in Single Element Array")
    void searchInSingleElementArray() {
        int[] array = {1};
        int searchValue = 1;
        int expectedIndex = 0; // only element in the array
        assertEquals(expectedIndex, BinarySearch.any(array, searchValue));
        assertEquals(expectedIndex, BinarySearch.left(array, searchValue));
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("ALL: Search in Empty Array")
    void searchInEmptyArray() {
        int[] array = {};
        int searchValue = 1;
        int expectedIndex = -1; // array is empty
        assertEquals(expectedIndex, BinarySearch.any(array, searchValue));
        assertEquals(expectedIndex, BinarySearch.left(array, searchValue));
        assertEquals(expectedIndex, BinarySearch.right(array, searchValue));
    }

    @Test
    @DisplayName("ALL: Search for Edge Elements")
    void searchForEdgeElements() {
        int[] array = {1, 2, 3, 4};
        assertEquals(0, BinarySearch.any(array, 1));
        assertEquals(0, BinarySearch.left(array, 1));
        assertEquals(0, BinarySearch.right(array, 1)); // leftmost occurrence of 1
        assertEquals(3, BinarySearch.any(array, 4));
        assertEquals(3, BinarySearch.left(array, 4));
        assertEquals(3, BinarySearch.right(array, 4)); // leftmost occurrence of 4
    }

    @Test
    @DisplayName("LEFT: Search in Array with Negative Numbers")
    void searchInArrayWithNegativeNumbers() {
        int[] array = {-3, -2, 0, 1, 2};
        int searchValue = -2;
        int expectedIndex = 1; // leftmost occurrence of -2
        assertEquals(expectedIndex, BinarySearch.left(array, searchValue));
    }

    @Test
    @DisplayName("LEFT: Search in Large Array")
    void searchInLargeArray() {
        int[] array = new int[1000]; // Large array of size 1000
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // Fill with consecutive numbers
        }
        int searchValue = 500;
        assertEquals(500, BinarySearch.left(array, searchValue)); // leftmost occurrence of 500
    }

    @Test
    @DisplayName("LEFT & RIGHT: Search in Large Repeating Array")
    void searchInLargeArrayWithConstantValues() {
        int[] array = new int[1000]; // Large array of size 1000
        for (int i = 0; i < array.length; i++) {
            array[i] = 500; // Fill with one number
        }
        int searchValue = 500;
        assertEquals(0, BinarySearch.left(array, searchValue)); // leftmost occurrence of 500
        assertEquals(array.length - 1, BinarySearch.right(array, searchValue)); // rightmost occurrence of 500
    }

}
