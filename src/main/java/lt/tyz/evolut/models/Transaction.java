package lt.tyz.evolut.models;

public class Transaction {

    private String id;
    private String dateTime;
    private final String fromAccountId;
    private final String toAccountId;
    private final double amount;
    private boolean completed;
    private String message;

    // this is for new transactions added via frontend
    public Transaction(String fromAccountId, String toAccountId, double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    // this is for transactions retrieved from DB.
    public Transaction(String id, String dateTime, String fromAccountId, String toAccountId, double amount) {
        this(fromAccountId, toAccountId, amount);
        this.id = id;
        this.dateTime = dateTime;
    }

    public Transaction(String id, String dateTime, String fromAccountId, String toAccountId, double amount, boolean completed, String message) {
        this(id, dateTime, fromAccountId, toAccountId, amount);
        this.completed = completed;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getMessage() {
        return message;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
