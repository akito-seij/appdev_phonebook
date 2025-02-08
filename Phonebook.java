public class Phonebook
{
    // Storage of contacts.
    private Person[] contacts;
    // Number of contacts present in the phonebook.
    private int size;

    /**
     * Create a phonebook of size 50.
     */
    public Phonebook()
    {
        contacts = new Person[50];
        size = 0;
    }

    /**
     * @return Number of contacts stored in this phonebook.
     */
    public int getSize()
    {
        // Complete this method
        return this.size;
    }

    /**
     * Get the contact at index.
     * 
     * @param index Index to get contact.
     * @return Person object from index. Null if index is not valid or out of range.
     */
    public Person getContactAtIndex(int index)
    {
        // Complete this method
        return null;
    }

    /**
     * Get the person object based on a given id.
     * 
     * @param id Target id.
     * @return Person object that has this id. Null if it does not exist.
     */
    public Person getContact(String id)
    {
        // Complete this method
        for (int i = 0; i < this.size; i++) {
            return contacts[i];
        }
        return null;
    }

    /**
     * Checks if this phonebook has contacts or not.
     * 
     * @return True or False.
     */
    public boolean isEmpty()
    {
        return this.getSize() == 0;
    }

    /**
     * Increase number of contacts present in this phonebook.
     */
    public void incrSize()
    {
        this.size++;
    }

    /**
     * Decrease number of contacts present in this phonebook.
     */
    public void decrSize()
    {
        this.size--;
    }

    /**
     * Increases the size of the phonebook whenever it is full.
     */
    private void increasePhonebookMaxSize()
    {
        // Complete this method
        Person[] newContacts = new Person[contacts.length * 2];
        System.arraycopy(contacts, 0, newContacts, 0, contacts.length);
        contacts = newContacts;
    }

    /**
     * Inserts a new person object at its appropriate lexicographic location in the phonebook.
     * 
     * @param p Person to be addded to the Phonebook.
     */
    public void insert(Person p)
    {
        // Complete this method
        if (size == contacts.length) {
            increasePhonebookMaxSize();
        }
        int index = findIndexInsertion(p);
        adjustPhonebook(index, size, "b");
        contacts[index] = p;
        size++;
    }

    /**
     * Searches in what index should this person object with the given be inserted.
     * 
     * @param p Person object to be inserted into the phonebook.
     * @return Appropriate index (position).
     */
    private int findIndexInsertion(Person p)
    {
        // Complete this method
        for (int i = 0; i < size; i++) {
            if (contacts[i].getId().compareTo(p.getId()) > 0) {
                return i;
            }
        }
        return size;
    }

    /**
     * Delete a person based on their contact id.
     * 
     * @param id Contact ID of that contact.
     * @return Deleted contact.
     */
    public Person deleteContact(String id)
    {
        // Complete this method...
        for (int i = 0; i < size; i++) {
            if (contacts[i].getId().equals(id)) {
                Person removed = contacts[i];
                adjustPhonebook(i, size - 1, "f");
                contacts[size - 1] = null;
                size--;
                return removed;
            }
        }
        return null;
    }

    /**
     * Adjusts the existing contacts in a phonebook from a given starting index to where it ends,
     * following a particular direction.
     * 
     * @param start Index to start adjustment from.
     * @param end Index to end adjustment into.
     * @param direction Direction in which the adjustment must be made. direction = "f" if element
     *        at index 0 takes the value of the element next to it (e.g. index 1). direction = "b"
     *        if element at index 1 takes the value of the element behind it (e.g. index 0).
     */
    private void adjustPhonebook(int start, int end, String direction)
    {
        // Complete this method...
        if (direction.equals("f")) {
            for (int i = start; i < end; i++) {
                contacts[i] = contacts[i + 1];
            }
        } else if (direction.equals("b")) {
            for (int i = end; i > start; i--) {
                contacts[i] = contacts[i - 1];
            }
        }
    }

    /**
     * Uses ellipsis to ambiguously accept as many country codes as possible. <br>
     * <br>
     * For example: <br>
     * <br>
     * If we have: printContactsFromCountryCodes(1, 2, 3) <br>
     * <br>
     * Then we get: countryCodes = { 1, 2, 3 };
     * 
     * @param countryCodes Area codes to be used as a filter.
     * @return Contacts on this phonebook under a particular area code set by the user.
     */
    public String printContactsFromCountryCodes(int... countryCodes)
    {
        // Complete this method.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int code : countryCodes) {
                if (contacts[i].getCountryCode() == code) {
                    result.append(contacts[i].toString()).append("\n");
                    break;
                }
            }
        }
        return result.toString();
    }

    /**
     * Print the entire phonebook without any filter or so...
     * 
     * @return The entire list of contacts present in this phonebook.
     */
    public String toString()
    {
        // Complete this method.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(contacts[i].toString()).append("\n");
        }
        return result.toString();
    }
}
