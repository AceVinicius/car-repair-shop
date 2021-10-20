package Model;

import java.util.HashSet;

public class Catalog {

    /********************
     * Class Properties *
     ********************/

    private HashSet<Category> categories;

    /**********************
     * Class Constructors *
     **********************/

    public Catalog() {
        this.categories = new HashSet<Category>();
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    /**
     * Add new category to Set categories.
     * 
     * @param category
     */
    public void addCategory(Category category) {
        categories.add(category);
    }

    /**
     * Remove category from categories.
     * 
     * @param category
     */
    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
