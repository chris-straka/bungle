package dev.cstraka.bungle.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import dev.cstraka.bungle.user.User;

/**
 * Auditing is when you track changes made to the DB.
 * https://docs.spring.io/spring-data/jpa/reference/auditing.html
 */
public class ApplicationAuditAware implements AuditorAware<User> {
    /**
     * Not sure how to get rid of the suppressed "mismatching null constraints" error.
     * This method is invoked by the auditing framework when an entity annotated 
     * with @EntityListener(AuditingEntityListener.class) is saved or updated.
     * If none of my entities have that annotation, this is never called.
     */
    @SuppressWarnings("null")
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast);
    }
}
