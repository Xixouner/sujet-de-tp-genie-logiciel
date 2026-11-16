package re.forestier.edu.rpg;

public class ObjectClass {
    private String name;
    private String description;
    private int weight;
    private int price;

    /**
     * Constructeur de la classe ObjectClass.
     *
     * @param name        Le nom de l'objet.
     * @param description La description de l'objet.
     * @param weight      Le poids de l'objet (doit être positif).
     * @param price       La valeur de l'objet (doit être positive).
     * @throws IllegalArgumentException si le nom ou la description est vide, ou si le poids ou la valeur est négatif.
     */
    public ObjectClass(String name, String description, int weight, int price) {
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(description.isEmpty()){
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if(weight < 0){
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if(price < 0){
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}
