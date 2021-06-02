package data.bms.sender;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class Logger {

    /**
     * Print message on to console.
     *
     * @param message String
     */
    private void print(String message) {
        System.out.println(message);
    }

    /**
     * Print exception on to console.
     *
     * @param e Exception
     */
    public void printException(Throwable e) {
        print(e.getMessage());
    }

    /**
     * Print message with delay.
     *
     * @param data  String
     * @param delay delay in milli sec
     * @throws InterruptedException if thread is interrupted
     */
    public void printWithDelay(
          String data,
          int delay
    ) throws InterruptedException
    {
        Thread.sleep(delay);
        print(data);
    }
}
