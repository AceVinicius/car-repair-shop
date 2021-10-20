package Model;

public class Employee extends People implements IEmployee {

    /********************
     * Class Properties *
     ********************/

    private static int registrationCounter = 1;

    private final int registration;

    /**********************
     * Class Constructors *
     **********************/

    public Employee(final long cpf, final String name, final long telephone, final Address address) {
        super(cpf, name, telephone, address);
        this.registration = registrationCounter++;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    @Override
    public int getRegistration() {
        return registration;
    }
}
