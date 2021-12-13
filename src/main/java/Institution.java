public class Institution {
    private final String name;
    private final String message;

    public Institution(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName(){
        return name;
    }
    public String getMessage(){
        return  message;
    }

}
