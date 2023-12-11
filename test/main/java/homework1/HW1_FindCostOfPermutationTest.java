package main.java.homework1;

import main.java.customException.IllegalCostException;
import main.java.customException.IllegalNumberException;
import main.java.customException.NullNumberException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HW1_FindCostOfPermutationTest {

    @Test
    @Order(0)
    void testEsplorativo(){

        Assertions.assertAll(
                () -> assertArrayEquals(new int [][]{new int[]{1, 4, 2}, new int[]{4, 1, 2}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, 4, 2}, 12).toArray()),

                () -> assertArrayEquals(new int [][]{new int[]{-2, 3, 1}, new int[]{3, -2, 1}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, -2, 3}, 3).toArray()),

                () -> assertArrayEquals(new int [][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, 4, 2}, 11).toArray()),

                () -> assertThrows(NullNumberException.class ,
                        () -> HW1_FindCostOfPermutations.costOfSums(null, 11).toArray())
        );
    }

    @Nested
    @DisplayName("Casi eccezionali")
    class exceptionalCases{
        @DisplayName("T1: inputNumbers è null")
        @Test
        @Order(1)
        void inputNumbersIsNull(){
            assertThrows(NullNumberException.class, () -> HW1_FindCostOfPermutations.costOfSums(null, 5).toArray());
        }

        @DisplayName("T2: inputNumbers è empty")
        @Test
        @Order(2)
        void inputNumbersIsEmpty() {
            assertThrows(IllegalNumberException.class, () -> HW1_FindCostOfPermutations.costOfSums(new int[]{}, 5).toArray());
        }

        @DisplayName("T3: inputNumbers ha una lunghezza maggiore di 10")
        @Test
        @Order(3)
        void inputNumbersIsLongerThan10() {
            assertThrows(IllegalNumberException.class, () -> HW1_FindCostOfPermutations.costOfSums(new int[]{1,2,3,4,5,6,7,8,9,10,11}, 5).toArray());
        }
    }

    @Nested
    @DisplayName("inputNumbers con lughezza uguale a 1")
    class inputNumbersLength1{
        @DisplayName("T4: costRequested è uguale a l’unico elemento di inputNumbers")
        @Test
        @Order(4)
        void costRequestedIsEqualToInputNumbers() throws NullNumberException, IllegalNumberException, IllegalCostException {
            assertArrayEquals(new int [][]{new int[]{1}},
                    HW1_FindCostOfPermutations.costOfSums(new int[]{1}, 1).toArray());
        }

        @DisplayName("T5: costRequested è diverso dall’unico elemento di inputNumbers")
        @Test
        @Order(5)
        void costRequestedIsDifferentFromInputNumbers() throws NullNumberException, IllegalNumberException, IllegalCostException {
            assertArrayEquals(new int [][]{},
                    HW1_FindCostOfPermutations.costOfSums(new int[]{1}, 2).toArray());
        }


    }

    @Nested
    @DisplayName("Combinazioni di input")
    class inputCombinations{
        @Nested
        @DisplayName("inputNumbers con numeri positivi e costRequested > 0")
        class primaCombinazione{
            @DisplayName("T6: costRequested corrisponde ad almeno un costo delle permutazioni di inputNumbers")
            @Test
            @Order(6)
            void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException, IllegalCostException {
                assertArrayEquals(new int[][]{new int[]{0, 3, 7}, new int[]{3, 0, 7}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{7, 0, 3}, 13).toArray());
            }

            @DisplayName("T7: costRequested non corrisponde a nessun un costo delle permutazioni di inputNumbers")
            @Test
            @Order(6)
            void costRequestedNonUgualeAdNessunUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException, IllegalCostException {
                assertArrayEquals(new int[][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{7, 0, 3}, 11).toArray());
            }
        }

        @Nested
        @DisplayName("inputNumbers con tutti numeri negativi e  costRequested < 0")
        class secondaCombinazione{
            @DisplayName("T8: costRequested corrisponde ad almeno un costo delle permutazioni di inputNumbers")
            @Test
            @Order(8)
            void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException{
                assertArrayEquals(new int[][]{new int[]{-2, -4, -3}, new int[]{-4, -2, -3}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{-3, -2, -4}, -15).toArray());
            }

            @DisplayName("T9: costRequested non corrisponde a nessun un costo delle permutazioni di inputNumbers")
            @Test
            @Order(9)
            void costRequestedNonUgualeANessunUnCostoDellePermutazioni() throws NullNumberException, IllegalNumberException{
                assertArrayEquals(new int[][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{-3, -2, -4}, -13).toArray());
            }
        }
    }

}

