package pl.equinix.visitor;


import java.util.LinkedList;

/**
 * This is the class for collecting all {@link pl.equinix.visitor.Visitor}
 * objects in {@link java.util.LinkedList} collection
 *
 * @author Tobiasz Piwowarczyk
 * @see pl.equinix.visitor.Visitor
 */
public class VisitorRepository {

    /**
     * Collections of all visitors
     */
    private LinkedList<Visitor> visitors;



    public VisitorRepository() {
        this.visitors = new LinkedList<>();
    }



    /**
     * This method returns all visitors
     *
     * @return collection of visitors
     */
    public LinkedList<Visitor> findAll() {
        return this.visitors;
    }



    /**
     * This method inserts {@code value} to linked list
     *
     * @param value item to insert
     */
    public void save(Visitor value) {
        this.visitors.add(value);
    }



    /**
     * This method returns total amount of visitors
     *
     * @return total amount of visitors
     */
    public int count() {
        return this.visitors.size();
    }



    /**
     * This method return repository as a string
     *
     * @return repository data as string
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");

        for(int i = 0; i < visitors.size(); i++)
            res.append(visitors.get(i).toString()).append((i < visitors.size() - 1) ? ", " : "");

        res.append("]");
        return res.toString();
    }
}
