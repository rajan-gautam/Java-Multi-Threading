public class Consumer extends Thread {

    private String returnVariable;

    public Consumer() {
        super();
    }

    public String returnMyVariable() {
        return returnVariable;
    }

    public void run() {}
}
