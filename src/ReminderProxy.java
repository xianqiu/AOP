import java.util.Date;

public class ReminderProxy implements Reminder {

    private Reminder reminder;

    public ReminderProxy(Reminder reminder) {
        this.reminder = reminder;
    }

    private void authorize() {
        System.out.println("Authorization: OK.");
    }

    private void log() {
        System.out.println("Log info: Remind at " + new Date().getTime());
    }

    @Override
    public void remind() {
        authorize();
        reminder.remind();
        log();
    }

}
