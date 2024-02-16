## Give the AWK script that displays the word frequency of a text.
```bash 
BEGIN {
   for(i=1; i<=NF; i++) { 
     word=tolower($i); 
     freq[word]++ 
     } 
}
END { 
  for(word in freq) 
    printf "%s\t%d\n", word, freq[word]
  }

```
![text](screenshots/1.png)

> **What contains the file etc/passwd?**.
>
>This file is a plain text-based database that contains information for all user accounts on the system. It includes details such as username, UID (User Identifier), GID (Group Identifier), full name, home directory, and the login shell. Each user's information is stored on a separate line, with fields separated by colons.
> 
> A line in this file look like:
> username:x:UID:GID:User Name:/path/to/username:/bin/bash

