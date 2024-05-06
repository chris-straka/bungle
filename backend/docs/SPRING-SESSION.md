# Spring Session

When Spring Session talks about "container-neutral" solutions, they're not talking about docker containers.
They're talking about servlet containers (Tomcat, Jetty).

Spring's default session management ties the session to the Tomcat servlet container (server memory), not the servlet.
If you want to store the sessions somwhere else, like in Redis or postgreSQL, you need this dependency.
