package main.java.homework1;

import main.java.customException.IllegalNumberException;
import main.java.customException.NullNumberException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HW1_FindCostOfPermutationTest {

    @Test
    @Order(0)
    void testEsplorativo() {

        Assertions.assertAll(
                () -> assertArrayEquals(new int[][]{new int[]{1, 4, 2}, new int[]{4, 1, 2}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, 4, 2}, 12).toArray()),

                () -> assertArrayEquals(new int[][]{new int[]{-2, 3, 1}, new int[]{3, -2, 1}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, -2, 3}, 3).toArray()),

                () -> assertArrayEquals(new int[][]{},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, 4, 2}, 11).toArray()),

                () -> assertThrows(NullNumberException.class,
                        () -> HW1_FindCostOfPermutations.costOfSums(null, 11).toArray())
        );
    }

    @DisplayName("Casi eccezionali")
    @ParameterizedTest(name = "T{index}: {0}")
    @MethodSource("streamEccezioni")
    @Order(1)
    void testEccezioni(String nome, int[] inputNumbers, int costRequested, Class<? extends Exception> exception) {
        assertThrows(exception, () -> HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested).toArray());
    }

    static Stream<Arguments> streamEccezioni() {
        return Stream.of(
                Arguments.of("inputNumbers è null", null, 5, NullNumberException.class),
                Arguments.of("inputNumbers è empty", new int[]{}, 5, IllegalNumberException.class),
                Arguments.of("inputNumbers ha una lunghezza maggiore di 10", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, 5, IllegalNumberException.class)
        );
    }

    @DisplayName("inputNumbers con lughezza uguale a 1")
    @ParameterizedTest(name = "{0}")
    @MethodSource("streamLunghezza1")
    @Order(2)
    void testLunghezza1(String nome, int[] inputNumbers, int costRequested, int[][] expected) throws NullNumberException, IllegalNumberException {
        assertArrayEquals(expected,
                HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested).toArray());
    }
    static Stream<Arguments> streamLunghezza1() {
        return Stream.of(
                Arguments.of("T4: costRequested è uguale all’unico elemento di inputNumbers",
                        new int[]{1}, 1, new int[][]{new int[]{1}}),
                Arguments.of("T5: costRequested è diverso dall’unico elemento di inputNumbers",
                        new int[]{1}, 2, new int[][]{})
        );
    }

    @DisplayName("Combinazioni di input")
    @ParameterizedTest(name = "{0}")
    @MethodSource("streamCombinazioniUguali")
    @Order(3)
    void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni(String nome, int[] inputNumbers, int costRequested, int[][] expected) throws NullNumberException, IllegalNumberException {
        assertThat(HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested), containsInAnyOrder(expected));
    }
    static Stream<Arguments> streamCombinazioniUguali() {
        return Stream.of(
                Arguments.of("T6: inputNumbers con numeri positivi e costRequested > 0 (almeno un costo delle permutazioni di inputNumbers è uguale a costRequested)"
                        , new int[]{7, 0, 3}, 13, new int[][]{new int[]{3, 0, 7}, new int[]{0, 3, 7}}),
                Arguments.of("T7: inputNumbers con numeri positivi e costRequested > 0 (nessun costo delle permutazioni di inputNumbers è uguale a costRequested)",
                        new int[]{7, 0, 3}, 11, new int[][]{}),
                Arguments.of("T8: inputNumbers con tutti numeri negativi e  costRequested < 0 (almeno un costo delle permutazioni di inputNumbers è uguale a costRequested)",
                        new int[]{-3, -2, -4}, -15, new int[][]{new int[]{-2, -4, -3}, new int[]{-4, -2, -3}}),
                Arguments.of("T9: inputNumbers con tutti numeri negativi e  costRequested < 0 (nessun costo delle permutazioni di inputNumbers è uguale a costRequested)",
                        new int[]{-3, -2, -4}, -13, new int[][]{}),
                Arguments.of("T10: inputNumbers con numeri positivi e negativi e  costRequested > 0 (almeno un costo delle permutazioni di inputNumbers è uguale a costRequested)",
                        new int[]{2, -4, -3, 3}, 5, new int[][]{new int[]{3, 2, -3, -4}, new int[]{2, 3, -3, -4}}),
                Arguments.of("T13: inputNumbers con numeri positivi e negativi e  costRequested < 0 (nessun costo delle permutazioni di inputNumbers è uguale a costRequested)",
                        new int[]{2, -4, -3, 3}, -5, new int[][]{}),
                Arguments.of("T12: inputNumbers con numeri positivi e negativi e  costRequested < 0 (almeno un costo delle permutazioni di inputNumbers è uguale a costRequested)",
                        new int[]{2, -4, -3, 3}, -9, new int[][]{new int[]{-4, 2, -3, 3}, new int[]{2, -4, -3, 3}})
        );
    }

    @DisplayName("Casi limite")
    @ParameterizedTest(name = "{0}")
    @MethodSource("streamCasiLimite")
    @Order(4)
    void testCasiLimite(String nome, int[] inputNumbers, int costRequested) throws NullNumberException, IllegalNumberException {
        assertArrayEquals(new int[][]{},
                HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested).toArray());
    }
    static Stream<Arguments> streamCasiLimite() {
        return Stream.of(
                Arguments.of("T14: costRequested è minore del costo minimo delle permutazioni di inputNumbers",
                        new int[]{4, -5, -8, -13, -14}, -139),
                Arguments.of("T15: costRequested è maggiore del costo massimo delle permutazioni di inputNumbers",
                        new int[]{4, -5, -8, -13, -14}, -67)
        );
    }

    @DisplayName("Casi creativi")
    @ParameterizedTest(name = "{0}")
    @MethodSource("streamCasiCreativi")
    @Order(5)
    void testCasiCreativi(String nome, int[] inputNumbers, int costRequested, int[][] expected) throws NullNumberException, IllegalNumberException {
        assertThat(HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested), containsInAnyOrder(expected));
    }
    static Stream<Arguments> streamCasiCreativi() {
        return Stream.of(
                Arguments.of("T16: Array con numeri tutti uguali",
                        new int[]{2, 2, 2, 2}, 18, new int[][]{new int[]{2, 2, 2, 2}}),
                Arguments.of("T17: Array con lunghezza pari e con numeri adiacenti opposti",
                        new int[]{-2, 2, 1, -1}, 0, new int[][]{ new int[]{1, -2, 2, -1}, new int[]{-1, 2, -2, 1}, new int[]{-2, 1, 2, -1}, new int[]{2, -1, -2, 1}})
        );
    }
}

