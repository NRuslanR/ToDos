package org.examples.todos.domain.resources.users;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.valueobjects.DomainValueObject;
import org.examples.todos.shared.utils.StringUtils;

public class UserName extends DomainValueObject<UserName> 
{    
    private String firstName;
	private String middleName;
	private String lastName;

	public static UserName of(String firstName, String middleName, String lastName)
	{
		return new UserName(firstName, middleName, lastName);
	}
	
    public UserName(String firstName, String middleName, String lastName)
    {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
    }

    public String firstName()
    {
        return firstName;
    }

    private void setFirstName(String firstName)
    {
        if (!isFullNamePartCorrect(firstName))
        {
            throw new DomainException("User name must contains text");
        }
        
        this.firstName = firstName;
    }

    public String middleName()
    {
        return middleName;
    }

    private void setMiddleName(String middleName)
    {
        if (!isFullNamePartCorrect(middleName))
        {
            throw new DomainException("User surname must contain text");
        }

        this.middleName = middleName;
    }

    public String lastName()
    {
        return lastName;
    }

    private void setLastName(String lastName)
    {
        if (!isFullNamePartCorrect(lastName))
        {
            throw new DomainException("User patronymic must contain text");
        }

        this.lastName = lastName;
    }

    public String fullName()
    {
        return firstName() + " " + middleName() + " " + lastName();
    }

    private boolean isFullNamePartCorrect(String value) 
    {
        return StringUtils.hasText(value);
    }

    public UserName changeFirstName(String newFirstName)
    {
        return new UserName(newFirstName, middleName, lastName);
    }

    public UserName changeMiddleName(String newMiddleName)
    {
        return new UserName(firstName, newMiddleName, lastName);
    }

    public UserName changeLastName(String newLastName)
    {
        return new UserName(firstName, middleName, newLastName);
    }
}
