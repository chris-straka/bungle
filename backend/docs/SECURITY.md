# Security Notes

"""
Putting a JWT token in localstorage will allow the hacker to take it in XSS attacks
However, a hacker doesn't need your JWT token at that point. 
A hacker (using JS) can make a fetch request to your backend and the browser will send the JWT over anyway.
To prevent this, the user should be prompted for their password again before making an important request.
"""


## Resources

[How to handle Auth](https://stackoverflow.com/questions/75571606)

[Another conversation on Auth](https://www.youtube.com/watch?v=vq861XoZI9k)
