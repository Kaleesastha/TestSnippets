public class Doors {
    public static void main(String[] args) {
        boolean[] doors = new boolean[101];
        for (int i = 0; i < doors.length; ++i) doors[i] = false;

        for (int iteration = 1; iteration <= 100; ++iteration)
            for (int i = 1; iteration * i < doors.length; ++i)
                doors[iteration * i] = !doors[iteration * i];

        for (int i = 1; i <= 100; i++)
            System.out.println("door " + i + " is " + (doors[i] ? "open" : "closed"));
    }
}
