# JWT stuff

From Ben Awad 

"""
Putting a JWT token in localstorage makes it obtainable for hackers in XSS attacks -> Object.keys(localStorage)
However, hiding it from JS by putting it in an HTTP Only token doesn't help either -> fetch(url)
What can help, is form validation and reprompting the user for their password before making serious requests
"""

[Ben Awad conversation on Auth](https://www.youtube.com/watch?v=vq861XoZI9k)
