import java.util.Scanner;

public class Main
{

    private static final String[][] MENUS = { {
            // Main Menu
            "Add New Contact", "Edit Contact", "Delete Contact", "View Phonebook", "Exit" },
            {
                    // Edit Contact Menu
                    "Student Number", "First Name", "Last Name", "Occupation", "Country Code",
                    "Area Code", "Phone Number", "None - Go back to Main Menu" },
            {
                    // Menu for View Phonebook
                    "View All", "View Contact through ID", "View Contacts through Country Code",
                    "Go back to Main Menu" },
            {
            "Burma", "Cambodia", "Thailand", "Vietnam", "Malaysia", "Philippines", "Indonesia",
            "Timor Leste", "Laos", "Brunei", "Singapore", "ALL"
    } };
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Phonebook pb = new Phonebook();
        boolean exit = false;
        while (true)
        {
            //error handling
            int opt = -1;
            try
            {
                showMenu(1, 1);
                opt = Integer.parseInt(prompt("Select an option: "));
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }

            switch (opt)
            {
                case 1:
                    // Add new contact
                    while(true) {
                        String id = prompt("Enter Contact ID: ");
                        String fname = prompt("Enter First Name: ");
                        String lname = prompt("Enter Last Name: ");
                        String occupation = prompt("Enter Occupation: ");
                        String sex = prompt("Enter gender (M for male, F for female): ");
                        int countryCode = Integer.parseInt(prompt("Enter Country Code: "));
                        int areaCode = Integer.parseInt(prompt("Enter Area Code: "));
                        String contactNum = prompt("Enter Contact Number: ");

                        Person newPerson = new Person(id, fname, lname, sex, occupation, contactNum, countryCode, areaCode);
                        pb.insert(newPerson);

                        String make_more = prompt("Do you want to make more contacts? (y/n): ");
                        if (make_more.equals("y")){
                            continue;
                        }
                        else if (make_more.equals("n")) {
                            break;
                        }
                    }
                    break;

                case 2:
                    // Edit
                    EDIT: // block labeling for easier exiting
                    while (true)
                    {
                        String idtoEdit = prompt("Enter ID of contact to edit(0 to exit): ");

                        Person person = pb.getContact(idtoEdit);
                        if (person != null)
                        {
                            while (true)
                            {
                                System.out.println("Here is the existing information about " + person.getId());
                                System.out.println(person);
                                System.out.println("Which would you like to edit?");
                                showMenu(2, 1);
                                int choice = Integer.parseInt(prompt("Enter Choice: "));
                                switch (choice)
                                {
                                    case 1:
                                        String newID = prompt("Enter new ID: ");
                                        person.setId(newID);
                                        break;

                                    case 2:
                                        String newFname = prompt("Enter new first name: ");
                                        person.setFName(newFname);
                                        break;

                                    case 3:
                                        String newLname = prompt("Enter new last name: ");
                                        person.setLName(newLname);
                                        break;

                                    case 4:
                                        String newOccupation = prompt("Enter new occupation: ");
                                        person.setOccupation(newOccupation);
                                        break;

                                    case 5:
                                        int newCountryCode = Integer.parseInt(prompt("Enter new Country Code: "));
                                        person.setCountryCode(newCountryCode);
                                        break;

                                    case 6:
                                        int newAreaCode = Integer.parseInt(prompt("Enter new Area Code: "));
                                        person.setAreaCode(newAreaCode);
                                        break;

                                    case 7:
                                        String newNum = prompt("Enter new number: ");
                                        person.setContactNum(newNum);
                                        break;

                                    case 8:
                                        break EDIT;

                                    default:
                                        break;
                                }
                            }
                        }
                        else if (idtoEdit.equals("0"))
                        {
                            break EDIT;
                        }
                        else{
                            System.out.println("Contact does not exist");
                        }
                    }
                    break;

                    
                case 3:
                    String id = prompt("Enter contact ID to delete: ");
                    Person p = pb.getContact(id);
                    if (p != null)
                    {
                        Person deletedContact = pb.deleteContact(id);
                        if (deletedContact != null)
                        {
                            System.out.println("Contact has been successfully deleted!");
                        }
                    }
                    else
                    {
                        System.out.println("This contact does not exist!");
                    }
                    break;
                case 4:
                    while (true)
                    {
                        showMenu(3, 1);
                        int showOpt = Integer.parseInt(prompt("Enter option:"));
                        if (showOpt == 1)
                        {
                            System.out.println(pb);
                        }
                        else if (showOpt == 2)
                        {
                            String targetId = prompt("Enter id to search: ");
                            Person target = pb.getContact(targetId);
                            if (target != null)
                            {
                                System.out.println(target);
                            }
                            else
                            {
                                System.out.println("No contact exists with that surname!");
                            }
                        }
                        else if (showOpt == 3)
                        {
                            int ccCount = 0;
                            int[] countryCodes = new int[9];
                            while (true)
                            {
                                int countryCode = Integer.parseInt("Enter Country Code: ");
                                // Print if input is 0
                                if (countryCode == 0)
                                {
                                    pb.printContactsFromCountryCodes(countryCodes);
                                    break;
                                }
                                // Check if area code is already inputted
                                boolean exists = false;
                                for (int a : countryCodes)
                                {
                                    if (a == countryCode)
                                    {
                                        System.out.println(
                                                "This area code has already been inputted!");
                                        exists = true;
                                        break;
                                    }
                                }
                                // Only add if area codes isn't part of the array...
                                if (!exists)
                                {
                                    countryCodes[ccCount] = countryCode;
                                    ccCount++;
                                }

                            }
                        }
                        else if (showOpt == 4)
                        {
                            break;
                        }
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
            if (exit)
                break;
        }
    }

    /**
     * Show menu based on given index. <br>
     * <br>
     * 1 for Main Menu. <br>
     * <br>
     * 2 for Edit Contact Menu. <br>
     * <br>
     * 3 for View Phonebook Menu. <br>
     * <br>
     * 4 for Country Code Menu.
     * 
     * @param menuIdx Index of the menu to be shown.
     * @param inlineTexts Number of menu options to be printed in a single line. Set to 1 if you
     *        want every line to only have one menu option.
     */
    private static void showMenu(int menuIdx, int inlineTexts)
    {
        String[] menu = MENUS[menuIdx - 1];
        int count = 0;
        String space = inlineTexts == 0 ? "" : "%-12s";
        String fmt = "[%d] " + space;
        for (int i = 0; i < menu.length; i++)
        {
            System.out.printf(fmt, i + 1, menu[i]);
            if (inlineTexts != 0)
            {
                count += 1;
            }
            if (count % inlineTexts == 0)
            {
                System.out.print("\n");
            }
        }
    }

    /**
     * Convert choices from the menu into their appropriate country code values.
     * 
     * @return Country code value of the menu choice.
     */
    private int convertChoices(int choice)
    {
        // Complete this method.
        return 0;
    }

    /**
     * Create a new person object using a slightly complicated setup.
     * 
     * @return Newly created person object.
     */
    private static Person createNewPerson()
    {
        String id, fname, lname, sex, occupation, contactNum;
        int countryCode, areaCode;
        id = prompt("Enter Contact ID: ");
        fname = prompt("Enter First Name: ");
        lname = prompt("Enter Last Name: ");
        occupation = prompt("Enter Occupation: ");
        sex = prompt("Enter sex/gender: ");
        countryCode = Integer.parseInt(prompt("Enter Country Code: "));
        areaCode = Integer.parseInt(prompt("Enter Area Code: "));
        contactNum = prompt("Enter Contact Number: ");
        return new Person(id, fname, lname, sex, occupation, contactNum, countryCode, areaCode);
    }

    /**
     * Receive prompt and return the inputted value back to the variable or process that requires
     * it. Data type is String. Do not forget to type cast if possible.
     * 
     * @param phrase Phrase to be given to user when requiring input.
     * @return Returns the data needed.
     */
    private static String prompt(String phrase)
    {
        System.out.print(phrase);
        return input.nextLine();
    }
}
