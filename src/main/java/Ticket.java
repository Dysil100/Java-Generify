import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Ticket<ins extends Institution> {
    private final  String name;
    private final LocalDateTime dateTime;
    private final Institution institut;
    private final String id ;

    public Ticket(Institution e, BigInteger counter, String name) {
        this.name = name;
        this.dateTime = LocalDateTime.now();
        this.institut = e;
        // Pattern of LocalDateTime ist: minutes, seconds, days, hour, year and then month.
        this.id = LocalDateTime.now().format(DateTimeFormatter.ofPattern("msdhyM")) +"0" + counter.toString();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Institution getInstitut() {
        return institut;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return institut.getName() + "\n" +
                "Name : " + name + "\n" +
                "DateTime : " + dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "Ticket's Id : " + id + "\n" +
                "" + institut.getMessage().toUpperCase(Locale.ROOT) + "\n" +
                "Good Day !" + "\n" +
                "          :-D";
    }
}
