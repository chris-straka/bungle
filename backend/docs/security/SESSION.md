## Session Fixation Attack

1. The attacker gives the user a fake sessionID 
2. The user signs in and sends the server the attacker's sessionID 
3. The server uses the sessionID provided by the user without refreshing it

So refresh the sessionID after the user logs in.
