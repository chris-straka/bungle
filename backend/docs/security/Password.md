# Password

## Adaptive One Way Functions For Password Hashing

SHA-256 is no longer secure, modern hardware can hash billions per second.
We now use adaptive one-way functions (bcrypt, argon2, etc) that are resource intensive.
"Adaptive" meaning they have a "work factor" that scales with better hardware
Initial logins are slow with one-way funcs, so you need sessions/tokens for things to stay fast.
