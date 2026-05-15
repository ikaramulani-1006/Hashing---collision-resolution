import java.util.*;
class Node {
    int id;
    String name; 
    Node next;
    Node(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}
public class HashMenu {
    static final int SIZE = 10;
    static final int MAX_PROBING = 20;
    static int[] linearTable = new int[SIZE];
    static int[] quadraticTable = new int[SIZE];
    static Node[] chainTable = new Node[SIZE];
    static boolean[] linearUsed = new boolean[SIZE];
    static boolean[] quadraticUsed = new boolean[SIZE];
    static int[] allIds = new int[100];
    static int idCount = 0;

    static int hashFunction(int id) {
        return id % SIZE;
    }
    // Linear Probing Operations
    static void linearInsert(int id) {
        int index = hashFunction(id);
        System.out.println("\n LINEAR PROBING - Insert ID: " + id);
        System.out.println("Initial index: " + index);
        int attempts = 0;
        while (linearUsed[index] && attempts < MAX_PROBING) {
            index = (index + 1) % SIZE;
            attempts++;
            System.out.println("Probe " + attempts + ": index " + index);
        }
        if (attempts >= MAX_PROBING) {
            System.out.println(" TABLE FULL!");
            return;
        }
        linearTable[index] = id;
        linearUsed[index] = true;
        System.out.println(" Inserted at index: " + index);
    }
    static void linearSearch(int key) {
        int index = hashFunction(key);
        int attempts = 0;
        System.out.println("\n LINEAR SEARCH - ID: " + key);
        while (attempts < MAX_PROBING) {
            if (linearUsed[index] && linearTable[index] == key) {
                System.out.println(" FOUND at index: " + index);
                return;
            }
            if (!linearUsed[index])
                break;
            index = (index + 1) % SIZE;
            attempts++;
        }
        System.out.println(" NOT FOUND");
    }
    // Quadratic Probing Operations 
    static void quadraticInsert(int id) {
        int index = hashFunction(id);
        System.out.println("\n QUADRATIC PROBING - Insert ID: " + id);
        System.out.println("Initial index: " + index);
        int attempts = 0;
        while (quadraticUsed[index] && attempts < MAX_PROBING) {
            index = (index + attempts * attempts) % SIZE;
            attempts++;
            System.out.println("Probe " + attempts + ": index " + index);
        }
        if (attempts >= MAX_PROBING) {
            System.out.println(" TABLE FULL!");
            return;
        }
        quadraticTable[index] = id;
        quadraticUsed[index] = true;
        System.out.println(" Inserted at index: " + index);
    }
    static void quadraticSearch(int key) {
        int index = hashFunction(key);
        int attempts = 0;
        System.out.println("\n QUADRATIC SEARCH - ID: " + key);
        while (attempts < MAX_PROBING) {
            if (quadraticUsed[index] && quadraticTable[index] == key) {
                System.out.println(" FOUND at index: " + index);
                return;
            }
            if (!quadraticUsed[index]) 
                break;
            index = (index + attempts * attempts) % SIZE;
            attempts++;
        }
        System.out.println(" NOT FOUND");
    }
    // Chaining Operations 
    static void chainInsert(int id, String name) {
        int index = hashFunction(id);
        Node newNode = new Node(id, name);
        System.out.println("\n CHAINING - Insert ID: " + id + ", Name: " + name);
        System.out.println("Hash index: " + index);
        if (chainTable[index] == null) {
            chainTable[index] = newNode;
            System.out.println(" Direct insertion");
        } else {
            Node temp = chainTable[index];
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
            System.out.println(" Added to chain");
        }
    }
    static void chainSearch(int key) {
        int index = hashFunction(key);
        Node temp = chainTable[index];
        System.out.println("\n CHAIN SEARCH - ID: " + key);
        while (temp != null) {
            if (temp.id == key) {
                System.out.println(" FOUND: " + temp.id + " - " + temp.name);
                return;
            }
            temp = temp.next;
        }
        System.out.println(" NOT FOUND");
    }
    // Display Functions 
    static void displayLinear() {
        System.out.println("\n LINEAR PROBING TABLE:");
        System.out.println("Index| ID | Status");
        System.out.println("-----|----|-------");
        for (int i = 0; i < SIZE; i++) {
            if (linearUsed[i])
                System.out.println(String.format("%4d | %3d | USED ", i, linearTable[i]));
            else
                System.out.println(String.format("%4d | --- | EMPTY", i));
            }
        }
    static void displayQuadratic() {
        System.out.println("\n QUADRATIC PROBING TABLE:");
        System.out.println("Index| ID | Status");
        System.out.println("-----|----|-------");
        for (int i = 0; i < SIZE; i++) {
            if (quadraticUsed[i])
                System.out.println(String.format("%4d | %3d | USED ", i, quadraticTable[i]));
            else
                System.out.println(String.format("%4d | --- | EMPTY", i));
            }
        }
    static void displayChain() {
        System.out.println("\n CHAINING TABLE :");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("[" + i + "] ");
            if (chainTable[i] == null) {
                System.out.println("EMPTY");
            } else {
                Node temp = chainTable[i];
                while (temp != null) {
                    System.out.print(temp.id + "(" + temp.name.charAt(0) + ")->");
                    temp = temp.next;
                }
                System.out.println("NULL");
            }
        }
    }
    // Divisibility check
    static boolean isDivisible(int num, int divisor) {
        switch (divisor) {
            case 2: 
                return num % 2 == 0;
            case 3:
                int sum = 0, temp = num;
                while (temp > 0) { sum += temp % 10; temp /= 10; }
                return sum % 3 == 0;
            case 4: 
                return (num % 100) % 4 == 0;
            case 5: 
                return num % 10 == 0 || num % 10 == 5;
            case 6: 
                return isDivisible(num, 2) && isDivisible(num, 3);
            case 7: 
                return ((num / 10) - 2 * (num % 10)) % 7 == 0;
            case 8: 
                return (num % 1000) % 8 == 0;
            case 9:
                sum = 0; temp = num;
                while (temp > 0) { sum += temp % 10; temp /= 10; }
                return sum % 9 == 0;
            case 10: 
                return num % 10 == 0;
            default: 
                return false;
        }
    }
    static void analyzeIDs() {
        System.out.println("\n ID ANALYSIS:");
        System.out.print("EVEN IDs: ");
        for (int i = 0; i < idCount; i++) {
            if (allIds[i] % 2 == 0) System.out.print(allIds[i] + " ");
        }
        System.out.print("\nODD IDs: ");
        for (int i = 0; i < idCount; i++) {
            if (allIds[i] % 2 != 0) {
                System.out.print(allIds[i] + " ");
                for (int d = 2; d <= 10; d++) {
                    if (isDivisible(allIds[i], d)) {
                        System.out.print("(÷" + d + ") ");
                        break;
                    }
                }
            }
        }
        System.out.println();
    }

    static void initTables() {
        Arrays.fill(linearTable, -1);
        Arrays.fill(quadraticTable, -1);
        Arrays.fill(linearUsed, false);
        Arrays.fill(quadraticUsed, false);
        Arrays.fill(chainTable, null);
        idCount = 0;
        System.out.println(" All tables cleared!");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initTables();
        while (true) {
            System.out.println("\n...HASHING MENU...");
            System.out.println("1. Insert Data ");
            System.out.println("2. Search ID");
            System.out.println("3. Display Linear Probing");
            System.out.println("4. Display Quadratic Probing");
            System.out.println("5. Display Chaining");
            System.out.println("6. Display All Tables");
            System.out.println("7. Analyze IDs (Even-Odd and Divisibility)");
            System.out.println("8. Clear All Tables");
            System.out.println("0. EXIT");
            System.out.print("\nEnter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: 
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    allIds[idCount++] = id;
                    linearInsert(id);
                    quadraticInsert(id);
                    chainInsert(id, name);
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    linearSearch(searchId);
                    quadraticSearch(searchId);
                    chainSearch(searchId);
                    break;
                case 3:
                    displayLinear();
                    break;
                case 4:
                    displayQuadratic();
                    break;
                case 5:
                    displayChain();
                    break;
                case 6:
                    displayLinear();
                    displayQuadratic();
                    displayChain();
                    break;
                case 7:
                    analyzeIDs();
                    break;
                case 8:
                    initTables();
                    break;
                case 0:
                    System.out.println("\n Thank you! Goodbye!\n Have a Nice Day ^_^");
                    sc.close();
                    return;
                default: 
                    System.out.println(" Invalid choice! Try 0-8");
            }
        }
    }
}