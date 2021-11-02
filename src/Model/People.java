package Model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People implements IPeople, Serializable {


	/********************
     * Class Properties *
     ********************/

	private static final long serialVersionUID = -5783651259768511339L;

    private final String cpf;

    private String name;
    private String telephone;
    private String email;
    private Address address;

    /**********************
     * Class Constructors *
     **********************/

    public People(final String cpf, final String name, final String telephone, final Address address) {
        this.cpf = cpf;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) throws EmailException {
        validateEmail(email);

        this.email = email;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    // public void setAddress(final Address address) {
    // this.address = address;
    // }

    /******************************
     * Additional Private Methods *
     ******************************/

    /**
     * Check if email field meets expected requirements.
     * 
     * @param email
     * @throws EmailException
     */
    private void validateEmail(String email) throws EmailException {
        String regex = "[a-zA-Z][\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher result = pattern.matcher(email);

        if (!result.matches()) {
            throw new EmailException("Email was not set.");
        }
    }
}
