/*
 * Course: CS2852
 * Spring 2021
 * Lab 1 - Library Waiting List
 * Name: Derek Richards
 * Created: 3/13/2021
 */
package richardsde;

/**
 * A class representing a request for an item.
 */
public class ItemRequest {
    private final int userId;
    private final int itemId;

    /**
     * Constructor for the ItemRequest class.
     * @param userId - The ID of the user.
     * @param itemId - The ID of the item.
     */
    public ItemRequest(int userId, int itemId){
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getUserId(){
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

}