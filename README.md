# Emoji-Spring-App


Problem : 
Create 2 Spring applications. First get info from an extern Api and the second one get that info from the first app , save it to the DB and make some operations.

Solution : 

First app is EmojiProvider. I made an endpoint which call the ‘getEmojiFromApi’ function from emojiProviderService. This function return the list of all the JSON that exist at “https://emojihub.herokuapp.com/api/all” with WebClient and mapped to EmojiDto.class.
EmojiDto contains all the fields that one Emoji JSON has. This class is called “EmojiDto” and not “Emoji” because the “Emoji” class will have additionally the “id” field.

The second app is similar to the first app except the fact that there are more functions in the service. 
This functions are common (save , save from a specific interval , get by a specific field , add , delete and update). The client will see as a result a EmojiDtoToClient object.
This object has only name, category and group because htmlCode and Unicode are sensitive data and must be hidden from client.