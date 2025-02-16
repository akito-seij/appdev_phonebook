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
                    // Delete
                    String get_id = prompt("Enter contact ID to delete: ");
                    Person person_exist = pb.getContact(get_id);
                    if (person_exist != null){
                        String make_sure = prompt("Are you sure you want to delete it? (y/n): ");
                        if (make_sure.equals("y")){
                            Person deletedContact = pb.deleteContact(get_id);
                            System.out.println(String.format("Contact %s has been deleted.", deletedContact.getId()));
                            break;
                        }
                        else {
                            System.out.println("Deletion did not proceed.");
                        }
                    }
                    else {
                        System.out.println("Contact does not exist");
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
                                System.out.println("No contact exists with this ID!");
                            }
                        }
                        else if (showOpt == 3)
                        {
                            int ccCount = 0;
                            int[] countryCodes = new int[11];
                            while (true)
                            {
                                showMenu(4, 5);
                                System.out.println("[0] No More");

                                int countryCode = Integer.parseInt(prompt("Enter Country Code: "));

                                if (countryCode == 0)
                                {
                                    break;
                                }

                                else if (countryCode == 12) {
                                    int[] allCodes = new int[11];
                                    for (int i = 1; i <= allCodes.length; i++) {
                                        allCodes[i-1] = convertChoices(i);
                                    }
                                    countryCodes = allCodes;
                                    break;
                                }
                                else if (countryCode > 0 && countryCode < 12) {
                                    boolean does_exist = false;
                                    for (int i = 0; i <= ccCount; i++) {
                                        if (countryCodes[i] == convertChoices(countryCode)) {
                                            does_exist = true;
                                            break;
                                        }
                                    }
                                    if (!does_exist) {
                                        countryCodes[ccCount] = convertChoices(countryCode);
                                        ccCount++;
                                    }
                                }
                            }
                            System.out.println("Here are the contacts from the country you chose:");
                            System.out.println(pb.printContactsFromCountryCodes(countryCodes));
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
     * 1 for Main Menu. <br>
     * 2 for Edit Contact Menu. <br>
     * 3 for View Phonebook Menu. <br>
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
    private static int convertChoices(int choice)
    {
        switch(choice){
            case 1: return 865; // Burma
            case 2: return 855; // Cambodia
            case 3: return 66; // Thailand
            case 4: return 84; // Vietnam
            case 5: return 60; // Malaysia
            case 6: return 63; // Philippines
            case 7: return 62; // Indonesia
            case 8: return 670; // Timor Leste
            case 9: return 95; // Laos
            case 10: return 673; // Brunei
            case 11: return 65; // Singapore
            default: return 0; // Default case
        }
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
