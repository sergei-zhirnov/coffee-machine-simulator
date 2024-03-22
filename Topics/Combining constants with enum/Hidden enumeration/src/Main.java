public class Main {

    public static void main(String[] args) {
        int counter = 0;
        for (Secret s: Secret.values()) {
            if (s.toString().matches("^STAR.*")) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}

/*
enum Secret {
    TEST, START, CRASH, STA
}


 */