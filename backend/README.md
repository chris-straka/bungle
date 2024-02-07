# Bungle

1. Install jdk version 21 https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html
2. Install docker desktop and run it https://www.docker.com/products/docker-desktop/

Development

```bash
# Terminal A
./gradlew build --continuous --parallel 

# Terminal B
./gradlew bootRun
```

Testing

```bash
# Run tests
./gradlew test --continuous

# Debug tests
./gradlew test --debug-jvm
# Then go to the debug pannel and attach the debugger to localhost:5005
```

Dependencies

```bash
# Refresh gradle after adding dependencies
./gradlew --refresh-dependencies 
# Then in vscode -> Developer: Reload Window (there must be a better way lol)
```

## Auth

- Sign-in route + OAuth
- Save/forgot password
- Demo User

## Home Screen

- User's should be able to put where they are on a map
- Other Bungler's could show up on the map
- User is shown the weather and time depending on where they chose on the map
- General news should be shown below the map
- Spotify, weather, time and something else should be shown on the right
- Wikipedia option maybe? Depending on what their API says
- I think making it look like the spring docs with a menu on the left, map in the middle, articles below, 

https://developer.spotify.com/documentation/web-playback-sdk

## Bungle Mode

- Users insert their own text blurb or receive a random article from wikipedia or the news API
- The backend will store a list of familiar words the user already understands
- The user can "bungle" an article
- The user can turn on pinyin for that data

## CI with github actions and vclusters

https://www.namecheap.com/domains/registration/results/?domain=bungle
