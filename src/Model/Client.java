package Model;

public class Client extends People implements IClient {

    /********************
     * Class Properties *
     ********************/

    private boolean platinum;

    /**********************
     * Class Constructors *
     **********************/

    public Client(final long cpf, final String name, final long telephone, final Address address) {
        super(cpf, name, telephone, address);
        this.platinum = false;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public void setPlatinum(final boolean platinum) {
        this.platinum = platinum;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    @Override
    public boolean isPlatinum() {
        return platinum;
    }
}
