package Model;

// import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People implements IPeople {

    /********************
     * Class Properties *
     ********************/

    private final long cpf;

    private String name;
    private long telephone;
    private String email;
    private Address address;

    /**********************
     * Class Constructors *
     **********************/

    public People(final long cpf, final String name, final long telephone, final Address address) {
        this.cpf = cpf;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    @Override
    public long getCpf() {
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
    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(final long telephone) {
        this.telephone = telephone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        try {
            validateEmail(email);
            this.email = email;
        } catch (EmailException e) {
            System.out.println(e);
        }
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
            throw new EmailException("Não sabe o que é email não, trouxa?\n");
        }
    }
}
