package Model;

public class Client extends People implements IClient {

	/********************
     * Class Properties *
     ********************/

	private static final long serialVersionUID = 1527791888821076884L;

    private boolean platinum;

    /**********************
     * Class Constructors *
     **********************/

    public Client(final String cpf, final String name, final String telephone, final Address address) {
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
