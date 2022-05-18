/*
 * Course: CS2852
 * Spring 2021
 * Lab 1 - Library Waiting List
 * Name: Derek Richards
 * Created: 3/13/2021
 */
package richardsde;

import java.util.List;

/**
 * This class contains a list of requests.
 */
public class WaitingList {
    private List<ItemRequest> requests;

    /**
     * The constructor for the list of requests.
     * @param requests - An empty list of ItemRequest objects.
     */
    public WaitingList(List<ItemRequest> requests){
        this.requests = requests;
    }

    /**
     * Searches the first request with an in-stock item.
     * @param isFulfillable - Determines if the request is fulfillable or not.
     * @return - If the request is fulfillable, the first item is returned.
     * If the request isn't fulfillable, a null reference is returned.
     */
    public ItemRequest nextFulfillableRequest(boolean isFulfillable){
        ItemRequest request1 = null;
        if (isFulfillable){
            request1 = requests.remove(0);
        } else {
            for (int i = 0; i < requests.size(); i++){

            }
        }
        return request1;
    }

    /**
     * Adds a request to the list of requests.
     * @param request - The request to be added.
     */
    public void requestItem(ItemRequest request){
        requests.add(request);
    }

    /**
     * Searches the list for all requests from the user and adds the requests to the provided list.
     * @param userId - The ID of the user.
     * @param userRequests - The list to add the requests from the user to.
     */
    public void getAllRequestsFromUser(int userId, List<ItemRequest> userRequests){
        for (ItemRequest request : requests) {
            if (request.getUserId() == userId) {
                userRequests.add(request);
            }
        }
    }

    /**
     * Searches the list for a request and removes it.
     * @param request - The request to be removed.
     * @return - true if the item was removed, false otherwise.
     */
    public boolean cancelRequest(ItemRequest request){
        for (int i = 0; i < requests.size(); i++){
            if (requests.get(i).equals(request)){
                requests.remove(request);
                return true;
            }
        }

        return false;
    }

    /**
     * Clears the list.
     */
    public void clear(){
        requests.clear();
    }

    public boolean isEmpty(){
        return requests.isEmpty();
    }
}