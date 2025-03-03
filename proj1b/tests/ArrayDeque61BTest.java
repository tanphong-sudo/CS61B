import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
    void someTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addLast(2);

        assertThat(ad.get(0)).isEqualTo(1);
        assertThat(ad.get(1)).isEqualTo(2);

        ad.addFirst(0);
        assertThat(ad.get(0)).isEqualTo(0);
    }

    @Test
    void emptyTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        assertThat(ad.isEmpty()).isTrue();
        ad.addFirst(1);
        assertThat(ad.isEmpty()).isFalse();
        assertThat(ad.size()).isEqualTo(1);
        ad.addFirst(2);
        assertThat(ad.size()).isEqualTo(2);
        ad.addLast(3);
        assertThat(ad.size()).isEqualTo(3);
    }

    @Test
    void toList() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    void removeTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.removeFirst()).isEqualTo(1);
        assertThat(ad.removeLast()).isEqualTo(3);
        assertThat(ad.size()).isEqualTo(1);
    }

    @Test
    void superTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        // Test initial state
        assertThat(ad.isEmpty()).isTrue();
        assertThat(ad.size()).isEqualTo(0);

        // Fill the deque to capacity (8 elements)
        for (int i = 0; i < 8; i++) {
            ad.addLast(i);
        }

        // Verify full deque state
        assertThat(ad.size()).isEqualTo(8);
        assertThat(ad.get(0)).isEqualTo(0);
        assertThat(ad.get(7)).isEqualTo(7);

        // Test circular behavior by removing from front and adding to back
        assertThat(ad.removeFirst()).isEqualTo(0);
        assertThat(ad.removeFirst()).isEqualTo(1);
        ad.addLast(8);
        ad.addLast(9);

        // Verify circular wrap-around
        assertThat(ad.get(0)).isEqualTo(2);
        assertThat(ad.get(7)).isEqualTo(9);

        // Test front additions after wrap-around
        ad.addFirst(10);
        ad.addFirst(11);
        assertThat(ad.get(0)).isEqualTo(11);
        assertThat(ad.get(1)).isEqualTo(10);

        // Test multiple removals
        for (int i = 0; i < 5; i++) {
            ad.removeLast();
        }
        assertThat(ad.size()).isEqualTo(5);

        // Final state verification
        assertThat(ad.toList()).containsExactly(11, 10, 2, 3, 4).inOrder();
        ad.addLast(12);
        ad.addLast(13);
        ad.addFirst(14);
        ad.addFirst(15);
        assertThat(ad.toList()).containsExactly(15, 14, 11, 10, 2, 3, 4, 12, 13).inOrder();
        // Final size check
        assertThat(ad.size()).isEqualTo(9);

    }

    @Test
    void resizeTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        for (int i = 0; i < 10; i++) {
            ad.addLast(i);
        }
        assertThat(ad.size()).isEqualTo(10);
        for (int i = 0; i < 5; i++) {
            ad.removeFirst();
        }
        assertThat(ad.size()).isEqualTo(5);
    }
}
