package org.examples.todos.domain.resources.users;

import org.examples.todos.domain.common.errors.DomainException;
import org.examples.todos.domain.common.valueobjects.DomainValueObject;
import org.examples.todos.shared.utils.StringUtils;

public class UserAddress extends DomainValueObject<UserAddress> {
    
    private String street;
    private String house;
    private String room;

    public UserAddress(String street, String house, String room)
    {
        setStreet(street);  
        setHouse(house);
        setRoom(room);
    }

    public String street()
    {
        return street;
    }

    private void setStreet(String newStreet)
    {
        if (!isAddressPartCorrect(newStreet))
        {
            throw new DomainException("User address's street must contain text");
        }

        street = newStreet;
    }

    public String house()
    {
        return house;
    }

    private void setHouse(String newHouse)
    {
        if (!isAddressPartCorrect(newHouse))
        {
            throw new DomainException("User address's house must contain text");
        }

        house = newHouse;
    }

    public String room()
    {
        return room;
    }

    private void setRoom(String newRoom)
    {
        if (!isAddressPartCorrect(newRoom))
        {
            throw new DomainException("User address's room must contain text");
        }

        room = newRoom;
    }

    private boolean isAddressPartCorrect(String addressPart) {
        
        return StringUtils.hasText(addressPart);
    }

    public UserAddress changeStreet(String newStreet)
    {
        return new UserAddress(newStreet, house, room);
    }

    public UserAddress changeHouse(String newHouse)
    {
        return new UserAddress(street, newHouse, room);
    }

    public UserAddress changeRoom(String newRoom)
    {
        return new UserAddress(street, house, newRoom);
    }
}
