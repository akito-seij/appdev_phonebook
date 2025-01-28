public class Person implements Comparable<Person>
{

    private String id, fname, lname, sex, occupation, contactNum;
    private int countryCode, areaCode;

    public Person(String id, String fname, String lname, String sex, String occupation,
            String contactNum, int countryCode, int areaCode)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.sex = sex;
        this.occupation = occupation;
        this.contactNum = contactNum;
        this.countryCode = countryCode;
        this.areaCode = areaCode;
    }

    public String getFName()
    {
        return fname;
    }

    public void setFName(String fname)
    {
        this.fname = fname;
    }

    public String getLName()
    {
        return lname;
    }

    public void setLName(String lname)
    {
        this.lname = lname;
    }

    public String getFullName()
    {
        return this.getFName() + " " + this.getLName();
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    /**
     * Get the appropriate pronoun for this person object.
     * 
     * @return His or Her.
     */
    public String getPronoun()
    {
        return this.getSex().equals("Male") ? "His" : "Her";
    }

    public String getOccupation()
    {
        return occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public String getContactNum()
    {
        return contactNum;
    }

    public void setContactNum(String contactNum)
    {
        this.contactNum = contactNum;
    }

    public int getAreaCode()
    {
        return areaCode;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setAreaCode(int areaCode)
    {
        this.areaCode = areaCode;
    }

    public int getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(int countryCode)
    {
        this.countryCode = countryCode;
    }

    /**
     * @return Full contact number of this person: Country Code + Area Code + Contact Number
     */
    public String getPhoneNumber()
    {
        return this.countryCode + "-" + this.areaCode + "-" + this.getContactNum();
    }

    /**
     * Compare the lexicographic order of this person object and another person object. <br>
     * <br>
     * Must compare by last name first, and then first name if both person objects have the same
     * last name. Assume that no two or more person objects will share the same first name.
     * 
     * @param o The person object to be compared.
     * @return Lexicographic flag if this person object should go first before the other person
     *         object. <br>
     *         <br>
     *         -1 if this person object should go first. <br>
     *         <br>
     *         0 if both person objects share the same last name and first name. <br>
     *         <br>
     *         1 if the other person object should go first.
     */
    @Override
    public int compareTo(Person o)
    {
        // Complete this method
        // DO NOT REMOVE THE @Override TAG
        return 0;
    }

    /**
     * Prints the information of this person.
     */
    public String toString()
    {
        return String.format("%s is a %s. %s number is %s", this.getFullName(),
                this.getOccupation(), this.getPronoun(), this.getPhoneNumber());
    }
}
